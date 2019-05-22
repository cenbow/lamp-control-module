package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.LightMac;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd0B2</p>
 * <p>Description: 设置MAC地址第（201#~255#灯）
 * 0x00b2
 * 服务器->网关
 * 201#灯MAC,202#灯MAC…255#灯MAC地址（7字节,包括6字节的mac地址和1字节的通道数量(同上)）
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd0B2 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd0B2.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    public static final int LENGTH = 55;

    public ParamElectricCmd0B2() {
    }

    public ParamElectricCmd0B2(LightMac[] lightMacList) {
        this.lightMacList = lightMacList;
    }

    /**
     * 网卡地址列表
     */
    @ApiParam("回路检测列表")
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
