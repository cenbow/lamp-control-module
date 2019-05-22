package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd302</p>
 * <p>Description: 远程升级启动</p>
 * <p>0x0302	服务器->网关	"数据结构：BIN文件字节数 4个字节
 * 两个字节0x0001 开始升级  0x0000 暂时不升级"	备注:网关回复数据部分是否升级（0x0001或者0x0000）
 *</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 19:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd302 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd302.class.getSimpleName());

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
