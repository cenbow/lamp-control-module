package com.owen.lamp_control_module_common.param.pattern;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamPatternCmd503</p>
 * <p>Description: 设置终端带的节点ID
 * 命令:0x0503
 * 数据内容: 旧节点ID（1字节） 新节点ID（1字节）
 * 示例:1:把ID是05改成ID是01
 * D5 C6 D5 C6（头码）
 * 05 03（命令）
 * 00 02（数据长度2）
 * 12 04 12 0E 09 10 03（时间戳）
 * 05 01 （数据）
 * XX（累加低八位校验）
 * 13 AB 13 AB(尾码)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamPatternCmd503 extends BaseParam {

    public static final String CMD = getCmd(ParamPatternCmd503.class.getSimpleName());

    /**
     * 新节点ID
     */
    @ApiParam("新节点ID")
    private int newId;
    /**
     * 旧节点ID
     */
    @ApiParam("旧节点ID")
    private int oldId;

    @Override
    public String toString() {
        return (decimalToHex(oldId) + decimalToHex(newId)).toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
