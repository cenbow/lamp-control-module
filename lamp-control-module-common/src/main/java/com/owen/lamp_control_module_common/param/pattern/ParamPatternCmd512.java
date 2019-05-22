package com.owen.lamp_control_module_common.param.pattern;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamPatternCmd512</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/8 9:20
 */
@Data
@EqualsAndHashCode
public class ParamPatternCmd512 extends BaseParam {

    public static final String CMD = getCmd(ParamPatternCmd512.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
