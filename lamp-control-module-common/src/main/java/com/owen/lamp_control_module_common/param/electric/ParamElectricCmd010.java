package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * <p>Title: ParamElectricCmd010</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd010 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd010.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    public ParamElectricCmd010() {
    }

    public ParamElectricCmd010(int lightValue) {
        this.lightValue = lightValue;
    }

    /**
     * 光照值
     */
    @ApiModelProperty(value = "光照值")
    private int lightValue;

    @Override
    public String toString() {
        return decimalToHexFF(lightValue).toUpperCase();
    }
}
