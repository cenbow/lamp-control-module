package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd051</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd051 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd051.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
