package com.owen.lamp.lamp_type_service.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.owen.lamp.lamp_type_api.api.IIotWallLightService;
import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.wall.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.owen.lamp_control_module_common.bean.Constants.IOT_WALL_HEAD;
import static com.owen.lamp_control_module_common.entity.MessageFrame.build;

/**
 * <p>Title: IotWallLightServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/8 10:33
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = IIotWallLightService.class, timeout = 1000000, version = "${dubbo.service.version}")
public class IotWallLightServiceImpl extends BaseService implements IIotWallLightService {

    public IotWallLightServiceImpl() {
        super.head = IOT_WALL_HEAD;
    }

    /**
     * 功能描述：创建终端所带的片选器参数
     * 命令：0x0601
     * 数据内容：
     * - 片选器个数（1字节）
     * - 片选器ID（2字节）
     * 注释： "片选器个数范围：1-8，默认00001  片选器ID范围：00001-65535，默认00001"
     * 案例：
     * 示例1：2个片选器，片选器ID分别为 01 02
     * D5 C7 D5 C7（头码）
     * 0601（指令）
     * 00 05 （数据长度5）
     * 1204120E091003（时间戳）
     * 02 00 01 00 02（数据）
     * XX （累加低八位教验）
     * 13AB13AB（尾码）
     *
     * @param deviceId 设备ID 设备ID
     * @param param    数据参数
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd601(String deviceId, ParamWallCmd601 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 功能描述：实时调整节目模式
     * 命令：0x0602
     * 数据内容：
     * - 片选器个数（1字节）
     * - 片选器ID（2字节）
     * - 模式编号（字节数根据片选器个数确定）
     * 注释：
     * 案例：
     * 示例:1:实时调整2个片选器节目，片选器01播放节目01，片选器02播放节目04
     * D5 C7 D5 C7（头码）  06 02（命令）  00 07（数据长度7）
     * 12 04 12 0E 09 10 03（时间戳）
     * 02 00 01 01 00 02 04（数据）
     * XX（累加低八位校验）  13 AB 13 AB(尾码)
     *
     * @param deviceId 设备ID 设备ID
     * @param param    数据参数
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd602(String deviceId, ParamWallCmd602 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 功能描述：实时调整颜色和亮度模式
     * 命令：0x0603
     * 数据内容：
     * - 片选器个数（1字节）
     * - 片选器ID（2字节）
     * - RGBW(颜色和亮度一共4字节)
     * 注释：
     * 案例：
     * 示例:1:实时调整2个片选器，片选器01绿色，亮度10，片选器02红色，亮度255
     * D5 C7 D5 C7（头码）  06 03（命令） 00 04（数据长度4）
     * 12 04 12 0E 09 10 03（时间戳）
     * 02 00 01 00 FF 00 0A 00 02 FF 00 00 FF（数据）
     * XX（累加低八位校验）  13 AB 13 AB(尾码)
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd603(String deviceId, ParamWallCmd603 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 功能描述：设置终端定时调色和亮度策略
     * 命令：0x0604
     * 数据内容：
     * - 定时策略数量（1字节）
     * - 开始时间（2字节）
     * - 结束时间（2字节）
     * - 时间间隔（2字节）
     * - 轮播的调色个数（1字节）
     * - 片选器个数（1字节）
     * - 片选器ID(2字节)
     * - 轮播调色数据（根据节目个数确定）
     * 注释：时间间隔单位：min（时间是FF表示不切换），定时策略个数范围：1-8，轮播颜色个数范围：1-16
     * 案例：
     * 示例1:设置2个定时策略，
     * 第1个策略是20:00开始，21:00结束 ，间隔10分钟，轮播2个颜色，2个片选器，片选器01是...，片选器02是...；
     * 第2个策略是21:00开始，22:00结束 ，间隔5分钟，轮播2个颜色，1个片选器，片选器01是红色亮度10，绿色亮度255
     * D5 C7 D5 C7（头码）  06 04（命令） 00 2B（数据长度43）
     * 12 04 12 0E 09 10 03（时间戳）
     * 02 14 00 15 00 00 0A 02（2个颜色）
     * 02(片选器个数)
     * 00 01(ID)
     * FF（红） 00(绿) 0A(蓝) FF(亮度)
     * FF (红)00（绿） 00（蓝）FF（亮度）
     * 00 02(ID)
     * FF (红) 00（绿） 0A(蓝) FF(亮度)
     * 00 (红) 00（绿） FF(蓝) FF(亮度)
     * 15 00 16 00 00 05 02 01
     * 00 01
     * FF 00 0A FF（数据）
     * XX（累加低八位校验）  13 AB 13 AB(尾码)
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd604(String deviceId, ParamWallCmd604 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 功能描述：设置终端定时节目策略
     * 命令：0x0605
     * 数据内容：
     * - 定时策略数量（1字节）
     * - 开始时间（2字节）
     * - 结束时间（2字节）
     * - 时间间隔（2字节）
     * - 轮播的节目个数（1字节）
     * - 片选器个数（1字节）
     * - 片选器ID(2字节)
     * - 轮播节目序号（根据节目个数确定）
     * - 时间间隔单位：min（时间是FF表示不切换），定时策略个数范围：1-8，轮播节目个数范围：1-16
     * 注释：
     * 案例：
     * 示例:1:设置2个定时策略，
     * 第1个是20:00开始，21:00结束 ，间隔10分钟，轮播5个节目，2个片选器，片选器01轮播节目序号01 02 03 04 05，片选器02轮播节目序号是01 03 04 05 06；
     * 第2个是21:00开始，22:00结束，间隔10分钟，轮播3个节目，1个片选器，片选器01轮播节目序号01 02 03 04 05；
     * D5 C7 D5 C7（头码） 06 05（命令） 00 0F（数据长度15）
     * 12 04 12 0E 09 10 03（时间戳）
     * 02
     * <p>
     * 14 00 15 00 00 0A
     * 05
     * 02
     * 00 01
     * 01 02 03 04 05
     * 00 02
     * 01 03 04 05 06
     * <p>
     * 15 00 16 00 00 0A
     * 03
     * 01
     * 00 01
     * 01 04 05（数据）
     * XX（累加低八位校验）  13 AB 13 AB(尾码)
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd605(String deviceId, ParamWallCmd605 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 开启网关执行策略动作
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd606(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamWallCmd606.CMD, (Object) "FF"));
    }

    /**
     * 功能描述：查询终端所带的片选器参数
     * 命令：0x0610
     * 数据内容：
     * 注释：
     * 案例：
     * D5 C7 D5 C7（头码）
     * 06 10（命令）
     * 00 00（数据长度）
     * 12 04 12 0E 09 10 03（时间戳）
     * XX（累加低八位校验）
     * 13 AB 13 AB(尾码)
     *
     * @param deviceId 设备ID 设备ID
     * @return 结果 执行结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd610(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamWallCmd610.CMD));
    }

    /**
     * 查询终端场景数据
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd611(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamWallCmd611.CMD));
    }

    /**
     * 查询存储定时策略
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendWallCmd612(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamWallCmd612.CMD));
    }

    /**
     * 查询设备是否已入库
     *
     * @param deviceId 设备ID 设备ID
     * @return 结果 查询结果
     */
    @Override
    protected boolean findById(String deviceId) {
        throw new NotImplementedException();
    }

    /**
     * 设置项目ID
     *
     * @return 项目ID
     */
    @Override
    protected String getProjectId() {
        throw new NotImplementedException();
    }

    /**
     * 更新设备状态
     */
    @Override
    protected void update() {
        throw new NotImplementedException();
    }

    /**
     * 处理创建终端所带的片选器参数指令
     */
    public void process601() {
        process();
    }

    /**
     * 处理实时调整节目模式指令
     */
    public void process602() {
        process();
    }

    /**
     * 处理实时调整颜色和亮度模式指令
     */
    public void process603() {
        process();
    }

    /**
     * 处理设置终端定时调色和亮度策略指令
     */
    public void process604() {
        process();
    }

    /**
     * 处理设置终端定时节目策略指令
     */
    public void process605() {
        process();
    }

    /**
     * 处理设置开机自动运行节目模式指令
     */
    public void process606() {
        process();
    }

    /**
     * 处理查询终端所带的片选器参数指令
     */
    public void process610() {
        process();
    }

    /**
     * 处理查询终端场景数据
     */
    public void process611() {
        process();
    }

}
