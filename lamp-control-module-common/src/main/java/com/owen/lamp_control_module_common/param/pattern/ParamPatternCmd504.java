package com.owen.lamp_control_module_common.param.pattern;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamPatternCmd504</p>
 * <p>Description: 设置终端场景参数
 * s功能描述: 设置终端场景
 * 命s令数据: 0x0504
 * 数据s内容:
 * - 场景编号（1字节）
 * - 场景数据（字节数=所有节点灯具数的和*通道数） 范围：1-16
 * 示例:设置5个灯具参数，场景2
 * D5 C6 D5 C6（头码）
 * 05 04（命令）
 * 00 1A（数据长度26）
 * 12 04 12 0E 09 10 03（时间戳）
 * 02
 * ff 01 00 00 00 ff 00 01 00 00 ff 01 00 00 00 ff 01 00 00 00 ff 00 01 00 00  （数据）
 * XX（累加低八位校验）
 * 13 AB 13 AB(尾码)
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamPatternCmd504 extends BaseParam {

    public static final String CMD = getCmd(ParamPatternCmd504.class.getSimpleName());

    /**
     * 场景编号
     */
    @ApiParam("场景编号")
    private int sceneNum;

    /**
     * 场景数据
     */
    @ApiParam("场景数据")
    private String sceneData;

    @Override
    public String toString() {
        return (decimalToHex(sceneNum) + sceneData).replace(" ", "").toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
