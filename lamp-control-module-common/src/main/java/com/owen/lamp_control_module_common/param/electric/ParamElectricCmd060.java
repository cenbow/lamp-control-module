package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.LoopStrategy;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamElectricCmd060</p>
 * <p>Description: 设置回路策略
 * 数据结构：回路组数量；1#回路组；2#回路组---6号回路组（最多可以6个回路组,每组包含一个回路, 这也是当前默认情况）
 * 回路组数量（1字节）  1~6
 * 每个回路组中的策略数据格式：策略数量(1字节)；1#策略类型,1#策略数据；2#策略类型，2#策略数据；。。。N#策略类型，N#策略数据；策略针对的回路组
 * 策略类型（1字节） 0xab (a表示开灯策略，b表示关灯策略；0光控 1经纬度 2时控）
 * 具体而言：0x00表示光照控制、
 * 0x11表示经纬度开回路，经纬度关回路； 0x12表示经纬度开回路，时控关回路
 * 0x21表示时控开回路，经纬度关回路；   0x22表示时控开回路，时控关回路
 * 策略数据（6字节）
 * 策略=0x00时  开始时间（HH:MM) 结束时间（HH:MM) 开阈值（1字节） 关阈值（1字节）
 * 策略=0x11时  提前或延后(1字节）时间（HH:MM) 提前或延后(1字节） 时间（HH:MM)
 * 策略=0x12时  提前或延后(1字节）时间（HH:MM) 关时间（HH:MM)    备用数据（1字节）
 * 策略=0x21时  开时间（HH:MM)   提前或延后(1字节）时间（HH:MM)  备用数据（1字节）
 * 策略=0x22时  开时间（HH:MM)   关时间（HH:MM)                 备用数据（2字节）
 * 策略针对的回路组：（1字节）
 * 第一个字节
 * .0=1表示该策略对1#回路有效；
 * .1=1表示该策略对2#回路有效；
 * .2=1表示该策略对3#回路有效
 * .3=1表示该策略对4#回路有效；
 * .4=1表示该策略对5#回路有效；
 * .5=1表示该策略对6#回路有效
 * </p>
 * <p>
 * 00 回路组数量（1字节）
 * 00 策略数量(1字节)
 * 00 策略类型（1字节）
 * 0000 0000 0000 策略数据（6字节）
 * 00 策略针对的回路组：（1字节）
 * </p>
 * <p>
 * D5C8D5C8（头码）
 * 0060（指令）
 * 000A（数据长度）
 * 1204120E091003（时间戳）
 * 03 设置回路数量
 * 03 设置单回路策略数量
 * 22 设置失控开时空关
 * 0A0A 开时间 0B0A 关时间 0000
 * 0A0A 开时间 0B0A 关时间 0000
 * 0A0A 开时间 0B0A 关时间 0000
 * 01 回路号
 * 10 （累加低八位教验）
 * 13AB13AB（尾码）
 * </p>
 * <p>
 * 指令:0060
 * 数据:
 * 回路组数量->06
 * 策略数量->01
 * 策略类型->22
 * 策略数据->0900 1200 0000
 * 策略针对的回路组->01
 * 01
 * 22
 * 0900 1200 0000 02
 * 01
 * 22
 * 0900 1200 0000 04
 * 01
 * 22
 * 0900 1200 0000 08
 * 01
 * 22
 * 0900 1200 0000 10
 * 01
 * 22
 * 0900 1200 0000 20
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd060 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd060.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    /**
     * 回路策略集合
     */
    @ApiParam("回路策略集合")
    private List<LoopStrategy> loopStrategyList;

    public ParamElectricCmd060() {
    }

    public ParamElectricCmd060(List<LoopStrategy> loopStrategyList) {
        this.loopStrategyList = loopStrategyList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(loopStrategyList.size()));
        for (LoopStrategy strategy : loopStrategyList) {
            stringBuilder.append(strategy);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
