package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd508</p>
 * <p>Description: 实时开关灯
 * 命令:0x0508
 * 数据格式:
 * - 开关灯数据（1字节）
 * 备注: 开灯模式默认是开机自动运行的模式，开关灯数据=FF,开灯，开关灯数据=00，关灯
 * 示例:1:开灯
 * D5 C6 D5 C6（头码）
 * 05 08（命令）
 * 00 02（数据长度2）
 * 12 04 12 0E 09 10 03（时间戳）
 * FF （数据）
 * XX（累加低八位校验）
 * 13 AB 13 AB(尾码)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 14:50
 */
public class ParamCommonCmd508 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd508.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
