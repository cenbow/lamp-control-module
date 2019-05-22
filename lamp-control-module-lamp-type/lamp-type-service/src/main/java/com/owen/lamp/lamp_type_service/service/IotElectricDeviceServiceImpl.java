package com.owen.lamp.lamp_type_service.service;

import com.owen.lamp.lamp_type_api.api.IIotElectricDeviceService;
import com.owen.lamp_control_module_common.bean.Constants;
import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.bean.RespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.common.ParamCommonCmd203;
import com.owen.lamp_control_module_common.param.electric.*;
import com.owen.lamp_control_module_common.param.electric.join.ParamElectricCmd0B0_1_2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static com.owen.lamp_control_module_common.bean.Constants.*;
import static com.owen.lamp_control_module_common.entity.MessageFrame.build;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.*;

/**
 * <p>Title: IotElectricDeviceServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 19:01
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = IIotElectricDeviceService.class, timeout = 1000000, version = "${dubbo.service.version}")
public class IotElectricDeviceServiceImpl extends BaseService implements IIotElectricDeviceService {

//    @Reference(version = "2.0.0", check = false, timeout = 20000)
//    IElectricDeviceService electricDeviceService;
//
//    @Reference(version = "2.0.0", check = false, timeout = 20000)
//    IPoleLampService poleLampService;

//    private ElectricDevice electricDevice;

    public IotElectricDeviceServiceImpl() {
        super.head = IOT_ELECTRIC_HEAD;
    }

    /**
     * 单个回路开关设置	0x0080
     *
     * @param deviceId         设备ID         设备ID
     * @param loopSwitchOption 回路策略组 key = 回路 value = 开关
     * @return 结果 设置手动回路开关
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd080(String deviceId, Map<String, String> loopSwitchOption) throws YcException {

        String deviceFlag = redisService.getObject(head + Constants.UNDERLINE + deviceId);
        if (null == deviceFlag) {
            if (log.isDebugEnabled()) {
                log.debug("{} 设备离线，手动开关失败!", deviceId);
            }
            throw new YcException(RespCode.DEVICE_OFF_LINE);
        }
        for (String key : loopSwitchOption.keySet()) {
            String data = changeAddSpace(key) + changeAddSpace(loopSwitchOption.get(key));
            send(deviceId, build(head, ParamElectricCmd080.CMD, (Object) data));
            boolean send = true;
            int i = 0;
            while (send) {
                //重发三次
                if (i < 3) {
                    send(deviceId, build(head, ParamElectricCmd080.CMD, (Object) data));
                    send = handleCallBack(deviceId);
                } else {
                    break;
                }
                i++;
            }
        }
        return RespCode.SUCCESS;
    }

    /**
     * 单个回路开关设置	0x0080
     *
     * @param deviceId 设备ID 设备ID
     * @param param    回路策略组 key = 回路 value = 开关
     * @return 结果 设置手动回路开关
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd080(String deviceId, ParamElectricCmd080 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置经纬度	0f0
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0F0(String deviceId, ParamElectricCmd0F0 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 固件升级接口
     * <p>
     * 0030 4030
     * 0031 1024 D5C8D5C80031
     * 0032
     *
     * @param deviceId 设备ID 设备id
     * @param fileName 文件名称
     * @return 结果 发送结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd300(String deviceId, String fileName) throws YcException {
        File file = new File(fileName);
        StringBuilder data = new StringBuilder(changeAddSpace(decimalToHex(file.length() + "")));
        for (int i = data.length(); i < 8; i++) {
            data.insert(0, "0");
        }
        int flag = 0;
        // 发送升级请求
        IRespCode result = send(deviceId, build(head, ParamElectricCmd300.CMD, data));
        // 等待设备响应
        while (true) {
            if (flag < 15) {
                if (!"1".equals(redisService.getObject(IOT_ELECTRIC_UPDATE + deviceId))) {
                    flag = getFlag(deviceId, flag, RESPONSE_STATUS_CODE_NORMAL + ParamElectricCmd300.CMD);
                } else {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 更新固件指令
     *
     * @param deviceId 设备ID 设备ID号
     * @param fileName 文件名称
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd301(String deviceId, String fileName) throws YcException {

        try {
            File file = new File(fileName);
            String str;
            int frameLength;
            int frameNumber = 0;
            byte[] buf = new byte[1024];
            InputStream input;
            StringBuilder data;
            str = decimalToHex(file.length() + "");
            str = changeAddSpace(str);
            input = new FileInputStream(file);
            data = new StringBuilder(str);
            for (int i = data.length(); i < 8; i++) {
                data.insert(0, "0");
            }
            data.delete(0, data.length());
            String frameNumberHex;
            int flag = 0;
            while ((frameLength = input.read(buf)) != -1) {
                frameNumberHex = decimalToHexFF(frameNumber + "");
                frameNumber++;
                // 帧序号
                data.append(frameNumberHex);
                // 帧长度
                data.append(decimalToHex(frameLength + ""));
                byte[] destBuf = new byte[frameLength];
                System.arraycopy(buf, 0, destBuf, 0, frameLength);
                // bin 文件
                data.append(bytesToHexString(destBuf));
                // 发送BIN
                send(deviceId, build(head, ParamElectricCmd301.CMD, data));
                // 等待设备响应
                while (true) {
                    // 获得响应帧率
                    String result = redisService.getObject(IOT_ELECTRIC_UPDATE + deviceId);
                    // 如果 key 不存在，等待一秒在此查询
                    if (result == null || !frameNumberHex.toUpperCase().equals(redisService.getObject(IOT_ELECTRIC_UPDATE + deviceId))) {
                        // 容忍10秒延迟
                        if (flag < 15) {
                            Thread.sleep(1000L);
                            if (log.isDebugEnabled()) {
                                log.debug("{} 第{}次 查询", deviceId, flag + 1);
                            }
                            flag++;
                            continue;
                        } else {
                            if (log.isWarnEnabled()) {
                                log.warn("{} 升级失败！", deviceId);
                            }
                            return RespCode.END;
                        }
                    }
                    // 返回匹配成功
                    if (frameNumberHex.toUpperCase().equals(redisService.getObject(IOT_ELECTRIC_UPDATE + deviceId))) {
                        if (log.isInfoEnabled()) {
                            log.info("{}:{}", deviceId, frameNumberHex);
                        }
                        flag = 0;
                        break;
                    }
                }
                data.delete(0, data.length());
            }
            // 清除 正在升级的状态
            redisService.del(IOT_ELECTRIC_UPDATE + deviceId);
            input.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return RespCode.SUCCESS;
    }

    /**
     * 更新固件指令
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd302(String deviceId) throws YcException {

        int flag = 0;
        // 发送升级请求
        IRespCode result = send(deviceId, build(head, ParamElectricCmd302.CMD, (Object) "0001"));
        // 等待设备响应
        while (true) {
            if (flag < 15) {
                if (redisService.getObject(IOT_ELECTRIC_UPDATE + deviceId) != null) {
                    flag = getFlag(deviceId, flag, RESPONSE_STATUS_CODE_NORMAL + ParamElectricCmd302.CMD);
                } else {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 登录|网关->服务器|集中器MAC地址
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd001(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd001.CMD));
    }

    /**
     * 心跳机制(GPRS链路测试)|0x0203	|
     * 网关->服务器	|
     * 信号强度1字节：0-31 为GPRS正常信号强度，99为检测不到信号，0xFF为以太网连接，0xAA备用	"每隔一段时间心跳一下（时间暂时定为1分钟）"|
     * 总1字节
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd203(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamCommonCmd203.CMD));
    }

    /**
     * 主动上报灯状态和电流、亮度、功率因素(1#灯控器~50#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|每个灯控器有2个灯通道，每个通道代表1个灯，
     * 包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；亮度1个字节；功率因数1字节；
     * 电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd230(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd230.CMD));
    }

    /**
     * 主动上报灯状态和电流、亮度、功率因素(51#灯控器~100#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd231(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd231.CMD));
    }

    /**
     * 主动上报灯状态和电流、亮度、功率因素(101#灯控器~150#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd232(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd232.CMD));
    }

    /**
     * 主动上报灯状态和电流、亮度、功率因素(201#灯控器~250#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd234(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd234.CMD));
    }

    /**
     * 主动上报灯电量	0x023F	网关->服务器	1#灯控器1通道灯电量，1#灯控器2通道电量；
     * ....N#灯控器2通道灯电量	"灯每个通道电量2个字节；总共数据4N个字节（N表示灯数）默认都是双通道灯控器，单灯第二通道占位"	每天0点自动上传
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd23F(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd23F.CMD));
    }

    /**
     * 主动上报灯状态和电流、亮度、功率因素(151#灯控器~200#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd233(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd233.CMD));
    }

    /**
     * 主动上报数字量及回路状态
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd250(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd250.CMD));
    }

    /**
     * 主动上报集中器端内部电表数据 0x02c0
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd2C0(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd2C0.CMD));
    }

    /**
     * 主动上报集中器端外部电表数据 0x02c1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd2C1(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd2C1.CMD));
    }

    /**
     * 上报重启/上电和GPRS重启	0x02F0
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd2F0(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd2F0.CMD));
    }

    /**
     * 上报网关失电报警	0x02F1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd2F1(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd2F1.CMD));
    }

    /**
     * 对时	0x0002
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd002(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd002.CMD));
    }

    /**
     * 服务器下发光照度值	0x0010
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd010(String deviceId, ParamElectricCmd010 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 服务器下发传感器数据数值	0x0011
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd011(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd011.CMD));
    }

    /**
     * 读取光照度	0x0020
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd020(String deviceId, ParamElectricCmd020 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 读1#~50#灯控器状态、电流、亮度、功率因数	0x0030
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd030_1_2_3_4(String deviceId) throws YcException {

        throw new NotImplementedException();
    }

    /**
     * 读1#~50#灯控器状态、电流、亮度、功率因数	0x0030
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd030(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd030.CMD));
    }

    /**
     * 读51#~100#灯状态、电流、亮度、功率因数	0x0031
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd031(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd031.CMD));
    }

    /**
     * 读101#~150#灯状态、电流、亮度、功率因数	0x0032
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd032(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd032.CMD));
    }

    /**
     * 读151#~200#灯状态、电流、亮度、功率因数	0x0033
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd033(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd033.CMD));
    }

    /**
     * 读201#~255#灯状态、电流、亮度、功率因数	0x0034
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd034(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd034.CMD));
    }

    /**
     * 读指定灯状态和电流、电压、功率、功率因素、电量、温度、亮度	0x0040
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd040(String deviceId, ParamElectricCmd040 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 读取数字量(此项功能扩展用)	0x0050
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd050(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd050.CMD));
    }

    /**
     * 读取回路的单灯检测	0x0051
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd051(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd051.CMD));
    }

    /**
     * 读取网关参数	0x0052
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd052(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd052.CMD));
    }

    /**
     * 设置回路策略	0x0060
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd060(String deviceId, ParamElectricCmd060 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置灯策略	0x0070
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd070(String deviceId, ParamElectricCmd070 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置指定灯控器策略	0x0071
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd071(String deviceId, ParamElectricCmd071 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置灯策略-手动	0x0090
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd090(String deviceId, ParamElectricCmd090 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置MAC地址第（1#~100#灯）	0x00b0
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0B0(String deviceId, ParamElectricCmd0B0 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置MAC地址第（101#~200#灯）	0x00b1
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0B1(String deviceId, ParamElectricCmd0B1 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置MAC地址第（201#~255#灯）	0x00b2
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0B2(String deviceId, ParamElectricCmd0B2 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 设置MAC地址第（1#~255#灯）
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0B0_1_2(String deviceId, ParamElectricCmd0B0_1_2 param) throws YcException {

        String flag = "0";
        while (true) {
            switch (flag) {
                case "0":
                    sendElectricCmd0B0(deviceId, param.getParamElectricCmd0B0());
                    break;
                case "00B0":
                    sendElectricCmd0B1(deviceId, param.getParamElectricCmd0B1());
                    break;
                case "00B1":
                    sendElectricCmd0B2(deviceId, param.getParamElectricCmd0B2());
                    break;
                case "00B2":
                    return RespCode.SUCCESS;
                default:
                    break;
            }
            flag = redisService.getObject("TASK_" + head + deviceId);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取内部电表数据	0x00c0
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0C0(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd0C0.CMD));
    }

    /**
     * 读取外接电表数据	0x00c1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0C1(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd0C1.CMD));
    }

    /**
     * 设置集中器远程重启	0x00f1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0F1(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd0F1.CMD));
    }

    /**
     * 设置回路的单灯检测	0x00f2
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd0F2(String deviceId, ParamElectricCmd0F2 param) throws YcException {
        return send(deviceId, build(head, param));
    }

    /**
     * 远程升级起始	0x0300
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd300(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd300.CMD));
    }

    /**
     * 远程升级BIN文件	0x0301
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd301(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd301.CMD));
    }

    /**
     * 配置电力采集模块参数	0x0800
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd800(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd800.CMD));
    }

    /**
     * 查询电力采集模块三相进线实时数据	0x0810
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd810(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd810.CMD));
    }

    /**
     * 查询回路支路三相出线实时数据	0x0811
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    @Override
    public IRespCode sendElectricCmd811(String deviceId) throws YcException {
        return send(deviceId, build(head, ParamElectricCmd811.CMD));
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
     * 返回 true 需要重发  false 不需要重发
     *
     * @param deviceId 设备ID
     * @return 结果
     */
    private boolean handleCallBack(String deviceId) {
        String key = RESPONSE_STATUS_CODE_SUCCESS + ParamElectricCmd080.CMD + UNDERLINE + deviceId;
        //重发三次
        int numberOfRetries = 3;
        int i = 1;
        while (i <= numberOfRetries) {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String flag = redisService.getObject(key);
            if (flag != null) {
                redisService.del(key);
                return false;
            }
            i++;
        }
        return true;
    }

    private int getFlag(String deviceId, int flag, String cmdSmS2cSetUploadBegin) {
        try {
            Thread.sleep(1000L);
            flag++;
            if (log.isDebugEnabled()) {
                log.debug("{} -> {} 等待第{}次", deviceId, cmdSmS2cSetUploadBegin, flag);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
