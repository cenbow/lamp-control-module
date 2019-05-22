package com.owen.lamp_control_module_common.param.common;

import com.owen.lamp_control_module_common.param.base.BaseParam;

/**
 * <p>Title: ParamCommonCmd470</p>
 * <p>Description: 设置备用服务器 IP 及 PORT</p>
 * <p>
 * 命令数据：0x0470
 * 数据：IP(4 字节)端口(2 字节)
 * 备注：若备用服务器连接 3 次未成 功，再次连接主服务器
 * </p>
 * <p>
 * 示例:【设置 IP:0.0.0.0 PORT:8001】
 * D5 C6 D5 C6(起始符)04 70(命令)00 06(数据长度 6)12 04 12 0E 09 10 03(时间)
 * 6A 0F B9 F6(IP)20 5A(PORT)
 * XX(校验)13 AB 13 AB(结束符)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 14:49
 */
public class ParamCommonCmd470 extends BaseParam {

    public static final String CMD = getCmd(ParamCommonCmd470.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
