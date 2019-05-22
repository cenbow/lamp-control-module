package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd4A0</p>
 * <p>Description: 终端工作时间(4字节)、固件版本号（2字节）、网络信号强度（1字节）	终端工作时间（分钟）、固件版本号、信号强度</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 14:45
 */
public class ParamCommonCmd4A0 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd4A0.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
