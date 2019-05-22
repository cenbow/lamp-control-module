package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd301</p>
 * <p>Description: 远程升级BIN文件</p>
 * <p>0x0301
 * 服务器->网关
 * "帧序号 2两个字节（高字节在前低字节在后）+
 * 帧的长度（指这条指令的BIN文件长度 2个字节（高字节在前低字节在后)） +
 * BIN文件（每帧1K字节，最后一桢可以小于1K）"
 * 备注:网关回复数据部分是帧序号（2个字节(高字节在前)）和帧长度（2个字节高字节在前低字节在后）
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 19:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd301 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd301.class.getSimpleName());

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
