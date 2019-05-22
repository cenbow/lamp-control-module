package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * 功能描述：
 * N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)
 * 每个灯控器有2个灯通道，每个通道代表1个灯，包括
 * 灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；
 * 电流2字节（单位0.1A）；
 * 亮度1个字节；
 * 功率因数1字节；
 * 电压2字节，
 * 功率2字节
 * 总共数据2*9*50个字节
 * 01 000E 64 5C  00EA 001F
 * 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在
 * 00030100070100E9000D4D0000001E
 *
 * @author weilai
 * @version 1.0.0 2018/9/13
 */
@Data
@Slf4j
@AllArgsConstructor
public class LightStatus implements Serializable {

    /**
     * 状态
     */
    private int lightStatus;

    /**
     * 电流
     */
    private int current;

    /**
     * 亮度
     */
    private int lightNess;

    /**
     * 功率因数
     */
    private int powerFactor;

    /**
     * 电压
     */
    private int voltage;

    /**
     * 功率
     */
    private int power;

    public LightStatus() {
    }

    /**
     * 040 上报消息解码
     * 0003 灯编号(2字节)
     * 01 通道bitmap(1字节,0X01表示通道1，0x02表示通道2，0x03表示两通道)
     * 0007 灯电流(2个字节) 7
     * 01 灯状态 1
     * 00E9 灯电压(2个字节) 233
     * 000D 功率(2个字节) 13
     * 4D 功率因素 77
     * 0000 、灯电量(2字节) 0
     * 00 灯温度 0
     * 1E 灯亮度 30
     * <p>
     * 03 灯状态
     * 00 0E 电流
     * 64 亮度
     * 5B 功率因素
     * 00 EC 电压
     * 00 1F 功率
     *
     * @param message 消息帧
     * @return 结果
     */
    public LightStatus(String message) {
        this.lightStatus = (Integer.valueOf(message.substring(0, 2), 16));
        this.current = (Integer.valueOf(message.substring(2, 6), 16));
        this.lightNess = (Integer.valueOf(message.substring(6, 8), 16));
        this.powerFactor = (Integer.valueOf(message.substring(8, 10), 16));
        this.voltage = (Integer.valueOf(message.substring(10, 14), 16));
        this.power = (Integer.valueOf(message.substring(14, 18), 16));
    }

    @Override
    public String toString() {
        return (decimalToHex(lightStatus)
                + decimalToHexFF(current)
                + decimalToHex(lightNess)
                + decimalToHex(powerFactor)
                + decimalToHexFF(voltage)
                + decimalToHexFF(power)).toUpperCase();
    }

}
