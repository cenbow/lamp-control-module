package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd410</p>
 * <p>Description: 设置心跳时间
 * 命令数据：0x0410
 * 数据：心跳时间(2 字节)
 * 备注：单位:S，范围:60-1200
 * 示例:【设置心跳时间是 300 秒】
 * D5 C6 D5 C6(起始符)04 10(命令)00 02(数据长度 2)12 04 12 0E 09 10 03(时间)01 2C(300S)XX(校验)13 AB 13 AB(结束符)
 * 【终端正确返回】
 * D5 C6 D5 C6(起始符)44 10(返回值)00 00(数据长度 0)12 04 12 0E 09 10 03(时间)27 11(ID) XX(校验)13 AB 13 AB(结束符)
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 14:48
 */
public class ParamCommonCmd410 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd410.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
