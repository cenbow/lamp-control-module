package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamTreeCmd609</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/8 9:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamTreeCmd609 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd609.class.getSimpleName());

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
