package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd020</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd020 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd020.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
