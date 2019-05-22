package com.owen.lamp_control_module_common.param.wall;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.TerminalTimeProgramStrategy;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamWallCmd605</p>
 * <p>Description: 设置终端定时节目策略</p>
 * <p>命令：0x0605
 * 数据内容：
 * - 定时策略数量（1字节）
 * - 开始时间（2字节）
 * - 结束时间（2字节）
 * - 片选器个数（1字节）
 * - 片选器ID(2字节)
 * - 轮播节目序号（根据节目个数确定）
 * - 时间间隔单位：min（时间是FF表示不切换），定时策略个数范围：1-8，轮播节目个数范围：1-16
 * 注释：
 * 案例：
 * 示例:1:设置2个定时策略，第1个是20:00开始，21:00结束，2个片选器，片选器01播节目序号01，片选器02播节目序号是03；
 * 第2个是21:00开始，22:00结束，1个片选器，片选器01播节目序号05；
 * D5 C7 D5 C7（头码）
 * 06 05（命令）
 * 00 0F（数据长度15）
 * 12 04 12 0E 09 10 03（时间戳）
 * 02
 * 14 00
 * 15 00
 * 02
 * 00 01
 * 01
 * 00 02
 * 03
 * 15 00
 * 16 00
 * 01
 * 00 01
 * 05
 * <p>
 * 02
 * 1414 1414
 * 02
 * 000D
 * 04
 * 000E
 * 04
 * 1414 1414
 * 02
 * 000D
 * 04
 * 000E
 * 04
 * （数据）
 * XX（累加低八位校验）  13 AB 13 AB(尾码)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamWallCmd605 extends BaseParam {

    public static final String CMD = getCmd(ParamWallCmd605.class.getSimpleName());

    /**
     * 终端时间调色板和光策略列表
     */
    @ApiParam("终端时间调色板和光策略列表")
    private List<TerminalTimeProgramStrategy> terminalTimeProgramStrategyList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(terminalTimeProgramStrategyList.size()));
        for (TerminalTimeProgramStrategy strategy : terminalTimeProgramStrategyList) {
            stringBuilder.append(strategy);
        }
        return stringBuilder.toString().toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
