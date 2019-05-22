package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd203</p>
 * <p>Description: 心跳发送
 * 示例:【心跳发送】
 * D5 C6 D5 C6(起始符)02 03(心跳命令)00 00(数据长度 0)12 04 12 0E 09 10 03(时间)2711(ID 是 10001) XX(校验)13 AB 13 AB(结束符) 【服务器心跳返回】
 * D5 C6 D5 C6(起始符)02 03(心跳命令)00 00(数据长度 0)12 04 12 0E 09 10 03(时间)XX(校验)13 AB 13 AB(结束符)
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 14:47
 */
public class ParamCommonCmd203 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd203.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
