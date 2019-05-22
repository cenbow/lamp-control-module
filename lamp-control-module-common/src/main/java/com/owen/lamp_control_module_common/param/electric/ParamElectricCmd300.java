package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd300</p>
 * <p>Description: 远程升级起始</p>
 * <p>0x0300
 * 服务器->网关
 * 数据结构：
 * BIN文件字节数 4个字节
 * 备注：网关回复数据部分是BIN文件的字节数4个字节
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 19:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd300 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd300.class.getSimpleName());

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
