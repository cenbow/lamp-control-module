package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * <p>Title: ParamElectricCmd0F0</p>
 * <p>Description: 设置经纬度
 * 经度（4字节） 维度（4字节）
 * 注:前两字节代表整数位，后两字节代表小数位
 * 0000 0000 0000 0000
 * 65535.65535 经度 Longitude
 * 65535.65535 维度 Dimension
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd0F0 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd0F0.class.getSimpleName());

    /**
     * 经度整数位
     */
    @ApiParam("经度整数位")
    private int longitudeInteger;

    /**
     * 经度小数位
     */
    @ApiParam("经度小数位")
    private int longitudeFloat;

    /**
     * 维度整数位
     */
    @ApiParam("维度整数位")
    private int dimensionInteger;

    /**
     * 维度小数位
     */
    @ApiParam("维度小数位")
    private int dimensionFloat;

    public ParamElectricCmd0F0() {
    }

    public ParamElectricCmd0F0(int longitudeInteger, int longitudeFloat, int dimensionInteger, int dimensionFloat) {
        this.longitudeInteger = longitudeInteger;
        this.longitudeFloat = longitudeFloat;
        this.dimensionInteger = dimensionInteger;
        this.dimensionFloat = dimensionFloat;
    }

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    @Override
    public String toString() {
        String stringBuilder = decimalToHexFF(longitudeInteger) +
                decimalToHexFF(longitudeFloat) +
                decimalToHexFF(dimensionInteger) +
                decimalToHexFF(dimensionFloat);
        return stringBuilder.toUpperCase();
    }
}
