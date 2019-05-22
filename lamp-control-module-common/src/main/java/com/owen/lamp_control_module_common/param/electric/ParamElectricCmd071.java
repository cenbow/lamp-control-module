package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.LightStrategy;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.owen.lamp_control_module_common.entity.MessageFrame.bitmapLoopNumber;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamElectricCmd071</p>
 * <p>Description: 设置指定灯控器策略
 * 15
 * 设置指定灯控器策略
 * 0x0071
 * "指定灯控器编号(2字节)，
 * 所作用的灯通道位图(1字节，当前可取值1，2，3，3表示同时作用于两个通道),
 * 开关策略数量（1字节），
 * 调光策略数量（1字节）；1#策略类型；1#策略数据；---N#策略类型；N#策略数据；1#调光数据；2#调光数据---N#调光数据；策略针对的灯组
 * 策略类型（1字节） 0xab (a表示开灯策略，b表示关灯策略；0光控 1经纬度 2时控）
 * 具体而言：0x00表示光照控制、
 * 0x11表示经纬度开灯，经纬度关灯；
 * 0x12表示经纬度开灯，时控关灯
 * 0x21表示时控开灯，经纬度关灯；
 * 0x22表示时控开灯，时控关灯
 * 策略数据（7字节）
 * 策略=00时    开始时间（HH:MM) 结束时间（HH:MM) 开阈值（1字节） 关阈值（1字节） 亮度（1字节）
 * 策略=0x11时  提前或延后(1字节）时间（HH:MM) 提前或延后(1字节） 时间（HH:MM)    亮度（1字节）
 * 策略=0x12时  提前或延后(1字节）时间（HH:MM) 关时间（HH:MM)    备用数据（1字节）亮度（1字节）
 * 策略=0x21时  开时间（HH:MM)   提前或延后(1字节）时间（HH:MM)  备用数据（1字节）亮度（1字节）
 * 策略=0x22时  开时间（HH:MM)   关时间（HH:MM)                 备用数据（2字节）亮度（1字节）
 * 调光数据（3字节）：
 * 调节亮度时间（HH:MM） 亮度"
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd071 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd071.class.getSimpleName());

    public static final int CHANNEL_1 = 1;
    public static final int CHANNEL_2 = 2;
    public static final int CHANNEL_3 = 3;

    /**
     * 指定灯控器编号
     */
    @ApiParam("指定灯控器编号")
    private int lightControlNum;

    /**
     * 所作用的灯通道位图
     */
    @ApiParam("所作用的灯通道位图")
    private int lightChannelBitMap;

    /**
     * 灯策略
     */
    @ApiParam("灯策略")
    private LightStrategy lightStrategy;

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    @Override
    public String toString() {
        return (decimalToHex(lightControlNum) + bitmapLoopNumber(lightChannelBitMap) + lightStrategy).toUpperCase();
    }
}
