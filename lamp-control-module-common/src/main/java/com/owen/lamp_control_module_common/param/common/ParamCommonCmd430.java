package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd430</p>
 * <p>Description: 设置终端 ID</p>
 * <p>
 * 命令数据:0x0430
 * 数据:终端 ID(2 字节)
 * 备注:范围:10000-65535
 * </p>
 * <p>
 * 示例:【设置终端 ID 为 10001】
 * D5 C6 D5 C6(起始符)04 30(命令)00 02(数据长度 2)12 04 12 0E 09 10 03(时间)27 11(ID:10001)XX(校验)13 AB 13 AB(结束符)
 *</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 14:48
 */
public class ParamCommonCmd430 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd430.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
