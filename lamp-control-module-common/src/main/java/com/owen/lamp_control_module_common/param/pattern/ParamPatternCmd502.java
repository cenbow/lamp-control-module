package com.owen.lamp_control_module_common.param.pattern;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>Title: ParamPatternCmd502</p>
 * <p>Description: 实时控制灯具模式
 * 命令: 0x0502
 * 数据内容:
 * - DMX512数据（字节数=所有节点灯具数的和*通道数）
 * 示例:1:实时控制5个灯具
 * D5 C6 D5 C6（头码）
 * 05 02（命令）
 * 00 19（数据长度25）
 * 12 04 12 0E 09 10 03（时间戳）
 * </p>
 * <p>
 * ff 01 00 00 00
 * ff 00 01 00 00
 * ff 01 00 00 00
 * ff 01 00 00 00
 * ff 00 01 00 00 （数据数量=（3+2）*5 =25个）
 * XX（累加低八位校验）
 * 13 AB 13 AB(尾码)
 * </p>
 * <p>
 * 备注: 数据包长度 < 1024
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamPatternCmd502 extends BaseParam {

    public static final String CMD = getCmd(ParamPatternCmd502.class.getSimpleName());

    /**
     * DMX512 直接读取的数据，无需处理
     */
    @ApiParam("DMX512 直接读取的数据，无需处理")
    private String data;

    @Override
    public String toString() {
        return data.toUpperCase();
    }


    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
