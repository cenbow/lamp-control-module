package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.LightMac;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd0B1</p>
 * <p>Description: 设置MAC地址第（101#~200#灯）
 * 0x00b1
 * 服务器->网关
 * 101#灯MAC,102#灯MAC…200#灯MAC地址（7字节,包括6字节的mac地址和1字节的通道数量(同上)）
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd0B1 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd0B1.class.getSimpleName());

    public static final int LENGTH = 100;

    public ParamElectricCmd0B1() {
    }

    public ParamElectricCmd0B1(LightMac[] lightMacList) {
        this.lightMacList = lightMacList;
    }

    /**
     * 单灯网卡集合
     */
    @ApiParam("单灯网卡集合")
    private LightMac[] lightMacList = new LightMac[LENGTH];

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LightMac aLightMacList : lightMacList) {
            stringBuilder.append(aLightMacList);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
