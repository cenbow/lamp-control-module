package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd4D0</p>
 * <p>Description: 恢复出厂设置</p>
 * <p>
 * 命令数据:0x04D0
 * 数据
 * 备注
 * </p>
 * <p>
 * 示例:【恢复出厂设置】
 * D5 C6 D5 C6(起始符)04 D0(命令)00 00(数据长度 0)12 04 12 0E 09 10 03(时间)XX(校验)13 AB 13 AB(结束符)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 14:46
 */
public class ParamCommonCmd4D0 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd4D0.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
