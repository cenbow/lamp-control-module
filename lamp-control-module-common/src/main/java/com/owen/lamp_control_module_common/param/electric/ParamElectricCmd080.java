package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamElectricCmd080</p>
 * <p>Description: 设置回路策略手动
 * 回路号；手动开/关
 * 总共2个字节
 * 回路号1个字节1-6
 * 手动开/关（开为1，关为0）
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd080 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd080.class.getSimpleName());

    /**
     * 回路号
     * 1-6
     */
    @ApiParam("回路号")
    private int loopNum;

    /**
     * 手动 true 开 、false 关
     */
    @ApiParam("手动")
    private boolean option;

    @Override
    public String toString() {
        return decimalToHex(loopNum) + decimalToHex(option ? 1 : 0);
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
