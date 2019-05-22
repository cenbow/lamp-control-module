package com.owen.lamp_control_module_common.param.wall;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.TerminalTimePaletteAndLightStrategy;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamWallCmd604</p>
 * <p>Description: 设置终端定时调色和亮度策略</p>
 * 命令：0x0604
 * 数据内容：
 * - 定时策略数量（1字节）
 * - 开始时间（2字节）
 * - 结束时间（2字节）
 * - 时间间隔（2字节）
 * - 轮播的调色个数（1字节）
 * - 片选器个数（1字节）
 * - 片选器ID(2字节)
 * - 轮播调色数据（根据节目个数确定）
 * 注释：时间间隔单位：min（时间是FF表示不切换），定时策略个数范围：1-8，轮播颜色个数范围：1-16
 * 案例：
 * 示例1:设置2个定时策略，
 * 第1个策略是20:00开始，21:00结束 ，间隔10分钟，轮播2个颜色，2个片选器，片选器01是...，片选器02是...；
 * 第2个策略是21:00开始，22:00结束 ，间隔5分钟，轮播2个颜色，1个片选器，片选器01是红色亮度10，绿色亮度255
 * D5 C7 D5 C7（头码）  06 04（命令） 00 2B（数据长度43）
 * 12 04 12 0E 09 10 03（时间戳）
 * 02
 * 14 00 15 00 00 0A
 * 02(片选器个数)
 * 02（2个颜色）
 * 00 01(ID)
 * FF（红）00(绿) 0A(蓝) FF(亮度)
 * FF (红)00（绿）00（蓝）FF（亮度）
 * 00 02(ID)
 * FF (红) 00（绿） 0A(蓝) FF(亮度)
 * 00 (红) 00（绿） FF(蓝) FF(亮度)
 * 15 00 16 00 00 05
 * 02
 * 01
 * 00 01
 * FF 00 0A FF（数据）
 * XX（累加低八位校验）  13 AB 13 AB(尾码)
 * TimeStrategy 定时策略
 * - 定时 + 策略（颜色+（ID + 颜色）
 * --
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamWallCmd604 extends BaseParam {

    public static final String CMD = getCmd(ParamWallCmd604.class.getSimpleName());

    /**
     * 终端时间调色板和光策略列表
     */
    @ApiParam("终端时间调色板和光策略列表")
    private List<TerminalTimePaletteAndLightStrategy> terminalTimePaletteAndLightStrategyList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(terminalTimePaletteAndLightStrategyList.size()));
        for (TerminalTimePaletteAndLightStrategy strategy :
                terminalTimePaletteAndLightStrategyList) {
            stringBuilder.append(strategy);
        }
        return stringBuilder.toString().toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
