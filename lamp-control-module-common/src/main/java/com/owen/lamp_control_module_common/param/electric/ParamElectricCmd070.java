package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.LightStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamElectricCmd070</p>
 * <p>Description: 设置灯策略
 * 数据结构：组数量（1字节）；1#组数据；2#组数据---N#组数据（最多可以8个组）
 * 每组数据的格式为：开关策略数量（1字节），调光策略数量（1字节）；1#策略类型；1#策略数据；---N#策略类型；N#策略数据；1#调光数据；2#调光数据---N#调光数据；策略针对的灯控器组和灯通道组
 * 策略类型（1字节） 0xab (a表示开灯策略，b表示关灯策略；0光控 1经纬度 2时控）
 * 具体而言：0x00表示光照控制、
 * 0x11表示经纬度开灯，经纬度关灯； 0x12表示经纬度开灯，时控关灯
 * 0x21表示时控开灯，经纬度关灯；   0x22表示时控开灯，时控关灯
 * 策略数据（7字节）
 * 策略=00时    开始时间（HH:MM) 结束时间（HH:MM) 开阈值(1字节) 关阈值（1字节） 亮度（1字节）
 * 策略=0x11时  提前或延后(1字节) 时间(HH:MM) 提前或延后(1字节) 时间（HH:MM)    亮度（1字节）
 * 策略=0x12时  提前或延后(1字节) 时间(HH:MM) 关时间（HH:MM)    备用数据（1字节）亮度（1字节）
 * 策略=0x21时  开时间（HH:MM)   提前或延后(1字节）时间（HH:MM)  备用数据（1字节）亮度（1字节）
 * 策略=0x22时  开时间（HH:MM)   关时间（HH:MM)                 备用数据（2字节）亮度（1字节）
 * 调光数据（3字节）：
 * 调节亮度时间（HH:MM） 亮度
 * 策略针对的灯控器组：（64字节的bitmap，表示256个灯控器每个灯控器两个灯通道共计512个灯通道是否使用该策略）
 * 第一个字节
 * .0=0表示该策略对1#灯控器的第1个灯通道有效，
 * .1=1表示该策略对1#灯控器的第2个灯通道有效...
 * .2=0
 * .3=1
 * .4=0
 * .5=1
 * .6=0
 * .7=1表示该策略对4#灯控器的2通道有效
 * </p>
 * <p>
 * 第二个字节对应于5#~8#灯；    ........；   第64个字节对应于253#~256#灯
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd070 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd070.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    private List<LightStrategy> lightStrategies;

    public ParamElectricCmd070() {
    }

    public ParamElectricCmd070(List<LightStrategy> lightStrategies) {
        this.lightStrategies = lightStrategies;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append(decimalToHex(lightStrategies.size()));
        for (LightStrategy strategy : lightStrategies) {
            data.append(strategy);
        }
        return data.toString().toUpperCase();
    }
}
