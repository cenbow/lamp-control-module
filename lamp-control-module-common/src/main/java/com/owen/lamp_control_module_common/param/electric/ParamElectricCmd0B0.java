package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.LightMac;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd0B0</p>
 * <p>Description: 设置MAC地址第（1#~100#灯）
 * 0x00b0
 * 服务器->网关
 * 1#灯MAC,
 * 2#灯MAC …
 * 100#灯MAC地址（7字节,
 * 包括6字节的mac地址和
 * 1字节的通道数量(
 * 0X01表示启用通道1，
 * 0x02表示启用通道2，
 * 0x03表示启用两通道)）
 * 需要对每个灯进行设置。
 * 如果MAC地址+相位为 FF FF FF FF FF FF，表示删除该序号的灯，无论配置多少单灯，三条命令都要下发（集中器使用过MAC未清除）防止
 * <p>
 * 0 - 100
 * <p>
 * 000000 000000 00 0
 * 000000 000000 00 1
 * FFFFFF FFFFFF 00 2
 * 000000 000000 00 3
 * ...
 * 000000 000000 00 99
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd0B0 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd0B0.class.getSimpleName());

    public static final int LENGTH = 100;

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    public ParamElectricCmd0B0() {
    }

    public ParamElectricCmd0B0(LightMac[] lightMacList) {
        this.lightMacList = lightMacList;
    }

    @ApiParam(value = "单灯MAC地址")
    private LightMac[] lightMacList = new LightMac[LENGTH];

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LightMac aLightMacList : lightMacList) {
            stringBuilder.append(aLightMacList);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
