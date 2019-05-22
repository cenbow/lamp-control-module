package com.owen.lamp_control_module_common.param.pattern;

import com.owen.lamp_control_module_common.enums.Operate;
import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamPatternCmd507</p>
 * <p>Description: 设置开机自动运行场景
 * 命令:0x0507
 * 数据内容:
 * - 使能数据（1字节）
 * - 场景编号（1字节）
 * 备注: 使能数据=ff，开使能，使能数据=00，关使能；
 * 示例:1:设置终端开机自动运行场景3，开使能
 * D5 C6 D5 C6（头码）
 * 05 07（命令）
 * 00 02（数据长度2）
 * 12 04 12 0E 09 10 03（时间戳）
 * FF 03 （数据）
 * XX（累加低八位校验）
 * 13 AB 13 AB(尾码)
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamPatternCmd507 extends BaseParam {

    public static final String CMD = getCmd(ParamPatternCmd507.class.getSimpleName());

    /**
     * 开关枚举
     */
    @ApiParam("开关枚举")
    private Operate operate;

    /**
     * 场景编号
     */
    @ApiParam("场景编号")
    private int sceneNum;

    @Override
    public String toString() {
        return (operate.getOperate() + decimalToHex(sceneNum)).toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
