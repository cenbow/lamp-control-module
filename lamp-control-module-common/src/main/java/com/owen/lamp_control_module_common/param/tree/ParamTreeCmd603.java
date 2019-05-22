package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamTreeCmd603</p>
 * <p>Description: 查询系统信息</p>
 * <p>
 * 命令数据：0x0611
 * 数据
 * 备注：节点数(1 字节)灯具通道(1 字节)灯具类型(1 字节)
 * </p>
 * <p>
 * 【查询系统信息】
 * D5 C6 D5 C6(起始符)06 11(命令)00 00(数据长度 0)12 04 12 0E 09 10 03(时间)XX(校验)13 AB 13 AB(结束符)
 * 【终端回复】
 * D5 C6 D5 C6(起始符)46 11(返回值)00 03(数据长度 3)12 04 12 0E 09 10 03(时间)27 11(终端 ID)
 * 1E 04 02(节点数量 30，灯具通道数 4，照树灯 01)
 * XX(校验)13 AB 13 AB(结束符)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:34
 */

public class ParamTreeCmd603 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd603.class.getSimpleName());

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
