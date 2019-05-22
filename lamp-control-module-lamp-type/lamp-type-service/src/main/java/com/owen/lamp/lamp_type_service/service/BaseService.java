package com.owen.lamp.lamp_type_service.service;

import com.owen.lamp.lamp_type_service.proerties.ServerProperties;
import com.owen.lamp_control_module_common.api.IBaseDeviceService;
import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.bean.RespCode;
import com.owen.lamp_control_module_common.cache.RedisService;
import com.owen.lamp_control_module_common.entity.MessageFrame;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.network.session.ChannelSession;
import com.owen.lamp_control_module_common.param.common.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.owen.lamp_control_module_common.bean.Constants.*;
import static com.owen.lamp_control_module_common.entity.MessageFrame.build;

/**
 * <p>Title: BaseService</p>
 * <p>Description: 本地处理服务接口实现基类</p>
 * 所有的  服务器 -> 客户端 处理器继承此类
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 17:28
 */
@Slf4j
@Service
public abstract class BaseService implements IBaseDeviceService {

    /**
     * 响应设备头
     */
    protected String head;

    /**
     * 响应设备ID
     */
    protected String deviceId;

    /**
     * 响应消息对象
     */
    protected MessageFrame messageFrame;

    @Autowired
    protected ChannelSession channelSession;

    @Autowired
    protected ServerProperties serverProperties;

    @Autowired
    protected RedisService redisService;

    /**
     * 指令处理器
     *
     * @param messageFrame 消息帧对象
     * @throws IllegalAccessException    执行权限异常
     * @throws InvocationTargetException 执行目标异常
     */
    @Override
    public void cmdProcess(MessageFrame messageFrame) throws InvocationTargetException, IllegalAccessException {
        // ---------- 给参数赋值 ---------
        this.messageFrame = messageFrame;
        this.head = messageFrame.getHead();
        this.deviceId = messageFrame.getId();
        // ---------- 处理流量统计 --------
//        doDataFlow();
        // ---------- 处理响应指令 --------
        doDelCommandFlag();
        // ---------- 处理上报指令 --------
        doRequestCommand();
    }

    /**
     * 处理上报消息 消息上行 设备 -> 服务器
     *
     * @throws IllegalAccessException    非法访问异常
     * @throws InvocationTargetException 调用目标异常
     */
    private void doRequestCommand() throws IllegalAccessException, InvocationTargetException {
        Class<? extends BaseService> clazz = this.getClass();
        Method method;
        try {
            method = clazz.getMethod("process" + messageFrame.getCmd());
            method.invoke(this);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getDeclaredMethod("process" + messageFrame.getCmd());
                method.invoke(this);
            } catch (NoSuchMethodException e1) {
                log.warn("[异常消息：{} 暂无处理逻辑] deviceId:{} command:{} data:{}", this.getClass(), deviceId, messageFrame.getCmd(), messageFrame.getData());
            }
        }
    }

    /**
     * 撤销消息标记 表示消息已被设备处理
     */
    private void doDelCommandFlag() {
        String responseStatus = messageFrame.getResponseStatus();
        if (RESPONSE_STATUS_CODE_SUCCESS.equals(responseStatus) || RESPONSE_STATUS_CODE_FAIL.equals(responseStatus)) {
            redisService.del("TASK_" + head + deviceId);
        }
    }

    // ------------------------- 响应方法区 ------------------------- //

    /**
     * 查询设备是否已入库
     *
     * @param deviceId 设备ID 设备ID
     * @return 结果 查询结果
     */
    protected abstract boolean findById(String deviceId);

    /**
     * 设置项目ID
     *
     * @return 项目ID
     */
    protected abstract String getProjectId();

    /**
     * 更新设备状态
     */
    protected abstract void update();

    // ------------------------- 响应方法区 ------------------------- //

    /**
     * 通用处理程序
     *
     * @return 结果
     */
    protected boolean process() {
        String cmd = messageFrame.getCmd();
        String responseStatus = messageFrame.getResponseStatus();
        switch (messageFrame.getResponseStatus()) {
            case RESPONSE_STATUS_CODE_NORMAL:
                responseSuccess();
                return true;
            case RESPONSE_STATUS_CODE_SUCCESS:
                if (log.isInfoEnabled()) {
                    log.info("[设备:{} 处理:{} 成功！]", deviceId, cmd);
                }
                return true;
            case RESPONSE_STATUS_CODE_CHECK_FAIL:
                if (log.isInfoEnabled()) {
                    log.info("[设备:{} 处理:{} 校验异常！]", deviceId, cmd);
                }
                throw new YcException();
            case RESPONSE_STATUS_CODE_FAIL:
                if (log.isInfoEnabled()) {
                    log.info("[设备:{} 处理:{} 失败！]", deviceId, cmd);
                }
                throw new YcException();
            default:
                if (log.isInfoEnabled()) {
                    log.info("[设备:{} 处理:{} 响应状态:{}异常！]", deviceId, cmd, responseStatus);
                }
                throw new YcException();
        }
    }

    /**
     * 功能：处理 203 指令
     * 效果：响应心跳包
     */
    public void process203() {
        if (process()) {
            // 1. 判断设备是否在线
            String onLineFlag = redisService.getObject(head + UNDERLINE + deviceId);
            // 3. 如果设备不在线,更新设备状态
            if (null == onLineFlag) {
                // 4. 处理心跳响应
                doHeartbeat();
            }
            // 2. 设置心跳过期时间为 3 分钟
            redisService.setObject(head + UNDERLINE + deviceId, ONLINE_VALUE, EXPIRED_VALUE);
        }
    }

    /**
     * 处理心跳
     *
     * @throws YcException 自定义异常
     */
    private void doHeartbeat() throws YcException {
        // 2. 如果设备不存在
        if (findById(deviceId)) {
            if (log.isInfoEnabled()) {
                log.info("[状态更新 {}_{}:未分配新设备上线!]", head, deviceId);
            }
        } else {
            // 4. 持久化入数据库
            update();
            // 5. 设置设备所属项目ID
            String projectId = getProjectId();
            // 9. 设备上线 推送到前端
            redisService.setObjectExpiredSeconds(WEB_PREFIX + projectId, DATA_VALUE, DATA_CHANGE_EXPIRED);
            // 9. 设备上线 推送到前端
            redisService.setObjectExpiredSeconds(MOBILE_PREFIX + projectId, DATA_VALUE, DATA_CHANGE_EXPIRED);
        }
    }

    /**
     * 功能：处理 2F0 指令
     * 效果：上报重启/上电和GPRS重启
     */
    public void process2F0() {
        process();
    }

    /**
     * 终端ID（2字节） 	范围：10000-65535（5位数）
     */
    public void process430() {
        process();
    }

    // ------------------------- 请求方法区 ------------------------- //

    /**
     * 重启终端 0x00F1
     *
     * @param deviceId 设备ID 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    public IRespCode sendCommonCmd0F1(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamCommonCmd0F1.CMD));
    }

    /**
     * 设置恢复出厂设置 0x04D0
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    public IRespCode sendCommonCmd4D0(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamCommonCmd4D0.CMD));
    }

    /**
     * 0x04A0: 查询系统参数
     * 终端工作时间(4字节)、固件版本号（2字节）、网络信号强度（1字节）
     *
     * @param deviceId 设备ID
     * @param data     数据
     * @return 结果
     * @throws YcException 自定义异常
     */
    public IRespCode sendCommonCmd4A0(String deviceId, String data) throws YcException {
        return send(deviceId, build(head, ParamCommonCmd4A0.CMD, (Object) data));

    }

    /**
     * 0x0430 设置终端ID
     * 终端ID（2字节）
     *
     * @param deviceId 设备ID
     * @param data     数据
     * @return 结果
     * @throws YcException 自定义异常
     */
    public IRespCode sendCommonCmd430(String deviceId, String data) throws YcException {
        send(deviceId, build(head, ParamCommonCmd430.CMD, (Object) data));
        channelSession.removeChannel(head + UNDERLINE + deviceId);
        return RespCode.SUCCESS;
    }

    /**
     * 设置终端备用服务器IP及端口号 0x0470:
     * IP（4字节）和端口（2字节）
     *
     * @param deviceId 设备ID
     * @param data     数据
     * @return 结果
     * @throws YcException 自定义异常
     */
    public IRespCode sendCommonCmd470(String deviceId, String data) throws YcException {
        return send(deviceId, build(head, ParamCommonCmd470.CMD, (Object) data));
    }

    /**
     * 设置终端主服务器IP及端口号 0x0460:
     * <p>
     * IP（4字节）和端口（2字节）
     *
     * @param deviceId 设备ID
     * @param data     数据
     * @return 结果
     * @throws YcException 自定义异常
     */
    public IRespCode sendCommonCmd460(String deviceId, String data) throws YcException {
        return send(deviceId, build(head, ParamCommonCmd460.CMD, (Object) data));
    }

    /**
     * 0x0410: 设置终端心跳时间
     * <p>
     * 心跳时间（2字节）
     * 单位：s,范围：60-1200
     * D5C6D5C6（头码）  4410（返回值）0000（数据长度）1204120E091003（时间戳）2711(终端ID)  XX（累加低八位校验）13AB13AB(尾码)
     * <p>
     * "示例:1:设置心跳时间是300秒
     * D5C6D5C6（头码）  0410（命令）0002（数据长度）1204120E091003（时间戳） 012C（300S/5分钟） XX（累加低八位校验）   13AB13AB(尾码)"
     *
     * @param deviceId 设备ID
     * @param data     数据
     * @return 结果
     * @throws YcException 自定义异常
     */
    public IRespCode sendCommonCmd410(String deviceId, String data) throws YcException {
        return send(deviceId, build(head, ParamCommonCmd410.CMD, (Object) data));
    }

    // ------------------------- 通用方法区 ------------------------- //

    /**
     * 快速成功响应
     */
    public void responseSuccess() {
        String cmd = messageFrame.getCmd();
        send(deviceId, new MessageFrame(head, RESPONSE_STATUS_CODE_SUCCESS, cmd));
    }

    /**
     * 快速失败响应
     */
    public void responseFailed() {
        String cmd = messageFrame.getCmd();
        send(deviceId, new MessageFrame(head, RESPONSE_STATUS_CODE_FAIL, cmd));
    }

    /**
     * 发送消息（有数据）
     *
     * @param deviceId 设备ID 设备id
     * @param cmd      命令
     * @param data     数据内容
     */
    @Override
    public void sendMessage(String deviceId, String cmd, Object... data) {
        send(deviceId, build(head, cmd, data));
    }

    /**
     * 发送消息（有数据）
     *
     * @param deviceId 设备ID 设备id
     * @param cmd      命令
     * @param data     数据内容
     */
    @Override
    public void sendMessage(String deviceId, String cmd, String data) {
        send(deviceId, build(head, cmd, data));
    }

    /**
     * 给设备发送消息(需等待回应)
     *
     * @param deviceId 设备ID 设备ID
     * @param message  消息内容
     * @return 结果 响应结果
     */
    protected IRespCode send(String deviceId, String message) throws YcException {
        int flag = 0;
        while (true) {
            if (flag == 60) {
                return RespCode.SUCCESS;
            }
            if (!redisService.hasKey("TASK_" + head + deviceId)) {
                return send(deviceId, new MessageFrame(message));
            }
            try {
                Thread.sleep(100);
                flag++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给设备发送消息(无需回应)
     *
     * @param deviceId     设备ID     设备ID
     * @param messageFrame 消息帧
     * @return 结果 响应结果
     */
    private IRespCode send(String deviceId, MessageFrame messageFrame) throws YcException {
        Channel channel = channelSession.get(messageFrame.getHead() + UNDERLINE + deviceId);
        if (null != channel) {
            ChannelFuture future = channel.writeAndFlush(messageFrame);
            future.addListener((ChannelFutureListener) future1 -> {
                // 处理出站异常
                if (!future1.isSuccess()) {
                    future1.cause().printStackTrace();
                    future1.channel().close();
                }
            });
            // 发送成功
            if (log.isInfoEnabled()) {
                log.info(">>---------------------------->>");
                log.info("[服务器 -> {}] ID:{} ", head, deviceId);
                log.info("[服务器 -> {}] 响应:{} ", head, messageFrame.getResponseStatus());
                log.info("[服务器 -> {}] 指令:{} ", head, messageFrame.getCmd());
                log.info("[服务器 -> {}] 数据:{} ", head, messageFrame.getData());
                log.info("[服务器 -> {}] 消息:{} ", head, messageFrame);
                log.info(">>---------------------------->>");
            }
            if (RESPONSE_STATUS_CODE_NORMAL.equals(messageFrame.getResponseStatus())) {
                redisService.setObjectExpiredSeconds("TASK_" + head + deviceId, messageFrame.getCmd(), 6L);
            }
            // 发送成功
            return RespCode.SUCCESS;
        } else {
            if (log.isInfoEnabled()) {
                log.info("[服务器 -> {}_{}] 发送 {} 失败！--> 设备离线", messageFrame.getHead(), deviceId, messageFrame);
            }
            return RespCode.FAIL;
        }
    }
}
