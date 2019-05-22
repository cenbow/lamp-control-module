package com.owen.lamp_control_module_common.param.wall;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamWallCmd606</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/8 9:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamWallCmd606 extends BaseParam {

    public static final String CMD = getCmd(ParamWallCmd606.class.getSimpleName());

    /**
     * 节目序号
     */
    @ApiParam("节目序号")
    private int programNum;

    @Override
    public String toString() {
        return decimalToHex(programNum).toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
