package com.owen.lamp_control_module_common.param.electric.join;

import com.owen.lamp_control_module_common.param.component.LightMac;
import com.owen.lamp_control_module_common.param.electric.ParamElectricCmd0B0;
import com.owen.lamp_control_module_common.param.electric.ParamElectricCmd0B1;
import com.owen.lamp_control_module_common.param.electric.ParamElectricCmd0B2;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Title: ParamElectricCmd0B0_1_2</p>
 * <p>Description: 设置MAC地址第（1#~255#灯） 融合接口</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamElectricCmd0B0_1_2 implements Serializable {

    public static final int LENGTH = 255;

    @ApiParam(value = "单灯 mac 地址列表")
    private LightMac[] lightMacList = new LightMac[LENGTH];

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LightMac aLightMacList : lightMacList) {
            stringBuilder.append(aLightMacList);
        }
        return stringBuilder.toString().toUpperCase();
    }

    public ParamElectricCmd0B0 getParamElectricCmd0B0() {
        ParamElectricCmd0B0 paramElectricCmd0B0 = new ParamElectricCmd0B0();
        LightMac[] tempLightMacList = new LightMac[ParamElectricCmd0B0.LENGTH];
        System.arraycopy(lightMacList, 0, tempLightMacList, 0, ParamElectricCmd0B0.LENGTH);
        paramElectricCmd0B0.setLightMacList(tempLightMacList);
        return paramElectricCmd0B0;
    }

    public ParamElectricCmd0B1 getParamElectricCmd0B1() {
        ParamElectricCmd0B1 paramElectricCmd0B1 = new ParamElectricCmd0B1();
        LightMac[] tempLightMacList = new LightMac[ParamElectricCmd0B1.LENGTH];
        System.arraycopy(lightMacList, ParamElectricCmd0B0.LENGTH, tempLightMacList, 0, ParamElectricCmd0B1.LENGTH);
        paramElectricCmd0B1.setLightMacList(tempLightMacList);
        return paramElectricCmd0B1;
    }

    public ParamElectricCmd0B2 getParamElectricCmd0B2() {
        ParamElectricCmd0B2 paramElectricCmd0B2 = new ParamElectricCmd0B2();
        LightMac[] tempLightMacList = new LightMac[ParamElectricCmd0B2.LENGTH];
        System.arraycopy(lightMacList, ParamElectricCmd0B0.LENGTH + ParamElectricCmd0B1.LENGTH, tempLightMacList, 0, ParamElectricCmd0B2.LENGTH);
        paramElectricCmd0B2.setLightMacList(tempLightMacList);
        return paramElectricCmd0B2;
    }

    public static ParamElectricCmd0B0_1_2 init() {
        ParamElectricCmd0B0_1_2 param = new ParamElectricCmd0B0_1_2();
        LightMac[] lightMacs = new LightMac[LENGTH];
        for (int i = 0; i < lightMacs.length; i++) {
            lightMacs[i] = new LightMac();
        }
        param.setLightMacList(lightMacs);
        return param;
    }
}
