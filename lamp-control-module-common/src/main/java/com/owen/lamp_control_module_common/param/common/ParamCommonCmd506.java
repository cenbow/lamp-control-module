package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd506</p>
 * <p>Description: 设置启用和禁用定时策略</p>
 * <p>
 * 命令: 0x0506
 * 数据内容: 策略使能位（1字节），FF表示启用定时，00表示禁用定时
 * </p>
 * <p>
 * 备注: 用来切换手动和自动模式，收到服务器实时控制灯具模式，启动禁用定时策略
 * 示例:1:禁用定时策略
 * D5 C6 D5 C6（头码）
 * 05 06（命令）
 * 00 01（数据长度1）
 * 12 04 12 0E 09 10 03（时间戳）
 * 00
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
public class ParamCommonCmd506 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd506.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
