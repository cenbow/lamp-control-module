package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * <p>Title: ParamElectricCmd040</p>
 * <p>Description:
 * 0040
 * 读取亮度状态参数
 * 3个字节（第1，2字节为灯控器编号，第3字节为灯通道bitmap）
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd040 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd040.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    /**
     * 单灯控制器编号
     */
    @ApiModelProperty("单灯控制器编号")
    private int lightControlNum;

    /**
     * 灯回路
     */
    @ApiModelProperty("灯回路")
    private int lightLoop;

    public ParamElectricCmd040() {
    }

    public ParamElectricCmd040(int lightControlNum, int lightLoop) {
        this.lightControlNum = lightControlNum;
        this.lightLoop = lightLoop;
    }

    @Override
    public String toString() {
        return (decimalToHexFF(lightControlNum) + decimalToHex(lightLoop)).toUpperCase();
    }

}
