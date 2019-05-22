package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamTreeCmd607</p>
 * <p>Description: 设置开机自动运行场景
 * 命令数据：0607
 * 数据：使能数据(1 字节)模式编号(1 字节)
 * 备注：使能数据=ff，开使能，使能 数据=00，关使能
 * 示例:
 * 【设置终端开机自动运行场景 3，开使能】
 * D5 C6 D5 C6(起始符)06 07(命令)00 02(数据长度 2)12 04 12 0E 09 10 03(时间)
 * -------------------
 * FF 03(开机自动运行模式 3)
 * -------------------
 * XX(校验)13 AB 13 AB(结束符)
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:38
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamTreeCmd607 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd607.class.getSimpleName());

    public static final int ENABLE = 255;
    public static final int DISABLE = 0;

    /**
     * 使能开关
     */
    @ApiParam("使能开关")
    private int enable;

    /**
     * 开机自动运行模式编号
     */
    @ApiParam("开机自动运行模式编号")
    private int modeNum;

    @Override
    public String toString() {
        return (decimalToHex(enable) + decimalToHex(modeNum)).toUpperCase();
    }

    /**
     * 设置开机自动运行场景
     *
     * @return 结果 设置开机自动运行场景指令消息
     */
    public static ParamTreeCmd607 build() {
        ParamTreeCmd607 paramTreeCmd607 = new ParamTreeCmd607();
        paramTreeCmd607.setEnable(ENABLE);
        paramTreeCmd607.setModeNum(3);
        return paramTreeCmd607;
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
