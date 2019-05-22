package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.Holiday;
import com.owen.lamp_control_module_common.param.component.ModeTimingMap;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamTreeCmd605</p>
 * <p>Description: 设置定时策略
 * 命令数据：0605
 * 数据：
 * - 定时模式(01)
 * 定时策略数量(1 字节)
 * 模式编号(1 字节)
 * 开始时间(2 字节)
 * 结束时间(2 字节)
 * --【备注 按天执行，每天都生效】
 * - 定时模式(02)
 * 执行周个数(1 字节)
 * 执行周数据(根据执行周个数 确定)
 * 定时策略数量(1 字节)
 * 模式编号(1 字节)
 * 开始时间(2 字节)
 * 结束时间(2 字节)
 * --【备注 按周执行，对指定周生效 执行周范围:1-7(周一到周日)】
 * - 定时模式(03)
 * 节假日个数(1 字节)
 * 指定日期(3 字节)
 * 定时策略 数量(1 字节)
 * 模式编号(1 字节)
 * 开始时间(2 字节)
 * 结束时间(2 字节)
 * --【备注 按节假日执行，指定日期生 效指定日期(年、月、日)】
 * 备注
 * 【按天执行，2 个定时策略，模式 1，2 点开，3 点关，模式 3，2 点开，3 点关】
 * D5 C6 D5 C6(起始符)06 05(命令)00 03(数据长度 3)12 04 12 0E 09 10 03(时间)
 * -------------------
 * 01(按天执行)
 * 02(2 个定时策略)
 * 01(模式 1)
 * 14 00(2 点开)
 * 15 00 (3 点关)
 * 03(模式 3)
 * 14 00 (2 点开)
 * 15(3 点关)
 * -------------------
 * XX(校验)13 AB 13 AB(结束符)
 * 示例 2:
 * 【按周执行，每周 2 天，周六，周日，2 个定时策略，模式 1，2 点开，3 点关，11 模式，2 点开，3 点关】
 * D5 C6 D5 C6(起始符)06 05(命令)00 03(数据长度 3)12 04 12 0E 09 10 03(时间)
 * -------------------
 * 02(按周执行)
 * 02(每周 2 天)
 * 06 (周六)
 * 07(周日)
 * 02(2 个定时策略)
 * 01 (模式 1)
 * 14 00(2 点开)
 * 15 00(3 点关)
 * 03(模式 3)
 * 14 00(2 点开)
 * 15(3 点关)
 * -------------------
 * XX(校验)13 AB 13 AB(结束符)
 * 示例 3:
 * 【按节假日执行，一个节假日，18 年 10 月 1 日，2 个定时策略，模式 1，2 点 开，3 点关，模式 3，2 点开，3 点关】
 * D5 C6 D5 C6(起始符)06 05(命令)00 0E(数据长度 14)12 04 12 0E 09 10 03(时间)
 * -------------------
 * 03(按节假日执行)
 * 01(1 个节假日)
 * 12(18 年)
 * 0A(10 月)
 * 01(1 日)
 * 02(2 个定时策略)
 * 01(模式 1)
 * 14 00(2 点开)
 * 15 00(3 点关)
 * 03(模式 3)
 * 14 00 (2 点开)
 * 15(3 点关)
 * -------------------
 * XX(校验)13 AB 13 AB(结束符)。
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:36
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamTreeCmd605 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd605.class.getSimpleName());

    /**
     * 按 天、周、节假日 执行模式编号
     * <p>
     * 1. DAY_MODE 按天执行模式
     * 2. WEEK_MODE 按周执行模式
     * 3. HOLIDAY_MODE 节假日模式
     */
    public static final int DAY_MODE = 1;
    public static final int WEEK_MODE = 2;
    public static final int HOLIDAY_MODE = 3;

    /**
     * 按 天、周、节假日 执行模式
     */
    @ApiParam("按 天、周、节假日 执行模式")
    private int mode;

    /**
     * 定时策略组
     */
    @ApiParam("定时策略组")
    private ModeTimingMap[] modeTimingMap;

    /**
     * 天数和定时策略匹配
     */
    @ApiParam("星期几和定时策略匹配")
    private Map<Integer, ModeTimingMap[]> weekDayTime;

    /**
     * 节假日开始日期
     */
    @ApiParam("节假日开始日期")
    private Holiday startHoliday;

    /**
     * 节假日结束日期
     */
    @ApiParam("节假日结束日期")
    private Holiday endHoliday;

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(decimalToHex(mode));
        switch (mode) {
            case DAY_MODE:
                buildParamByDayMode(message);
                break;
            case WEEK_MODE:
                buildParamByWeekMode(message);
                break;
            case HOLIDAY_MODE:
                buildParamByHolidayMode(message);
                break;
            default:
                break;
        }
        return message.toString().toUpperCase();
    }

    /**
     * 构建单日模式
     *
     * @param times
     * @return
     */
    public static ParamTreeCmd605 buildParamByDayMode(ModeTimingMap[] times) {
        return new ParamTreeCmd605(ParamTreeCmd605.DAY_MODE, times, null, null, null);
    }

    /**
     * 星期模式
     *
     * @param weekDays
     * @param weekDays
     * @return
     */
    public static ParamTreeCmd605 buildParamByWeekMode(Map<Integer, ModeTimingMap[]> weekDays) {
        return new ParamTreeCmd605(ParamTreeCmd605.WEEK_MODE, null, weekDays, null, null);
    }

    /**
     * 节假日模式
     *
     * @param times
     * @param startHoliday
     * @param endHoliday
     * @return
     */
    public static ParamTreeCmd605 buildParamByHolidayMode(ModeTimingMap[] times, Holiday startHoliday, Holiday endHoliday) {
        return new ParamTreeCmd605(ParamTreeCmd605.HOLIDAY_MODE, times, null, startHoliday, endHoliday);
    }

    /**
     * 【按节假日执行，一个节假日，18 年 10 月 1 日，2 个定时策略，模式 1，2 点 开，3 点关，模式 3，2 点开，3 点关】
     * D5 C6 D5 C6(起始符)06 05(命令)00 0E(数据长度 14)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 03(按节假日执行)
     * 01(1 个节假日)
     * 12(18 年)
     * 0A(10 月)
     * 01(1 日)
     * 12(18 年)
     * 0A(10 月)
     * 07(7 日)
     * 02(2 个定时策略)
     * 01(模式 1)
     * 14 00(2 点开)
     * 15 00(3 点关)
     * 03(模式 3)
     * 14 00 (2 点开)
     * 15(3 点关)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)。
     */
    private void buildParamByHolidayMode(StringBuilder message) {
        if (message != null) {
            message.append(startHoliday);
            message.append(endHoliday);
            // 加入时间段
            toStringByTimeArray(message, modeTimingMap);
        }
    }

    /**
     * 【按周执行，每周 2 天，周六，周日，2 个定时策略，模式 1，2 点开，3 点关，11 模式，2 点开，3 点关】
     * D5 C6 D5 C6(起始符)06 05(命令)00 03(数据长度 3)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 02(按周执行)
     * 02(每周 2 天)
     * 06 (周六)
     * <p>
     * 02(2 个定时策略)
     * 01 (模式 1)
     * 14 00(2 点开)
     * 15 00(3 点关)
     * 03(模式 3)
     * 14 00(2 点开)
     * 15(3 点关)
     * <p>
     * 07(周日)
     * 02(2 个定时策略)
     * 01 (模式 1)
     * 14 00(2 点开)
     * 15 00(3 点关)
     * 03(模式 3)
     * 14 00(2 点开)
     * 15(3 点关)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     */
    private void buildParamByWeekMode(StringBuilder message) {
        // 加入周天数
        message.append(decimalToHex(weekDayTime.size()));
        for (Integer day : weekDayTime.keySet()) {
            message.append(decimalToHex(day));
            toStringByTimeArray(message, weekDayTime.get(day));
        }
    }

    /**
     * 【按天执行，2 个定时策略，模式 1，2 点开，3 点关，模式 3，2 点开，3 点关】
     * D5 C6 D5 C6(起始符)06 05(命令)00 03(数据长度 3)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 01(按天执行)
     * 02(2 个定时策略)
     * 01(模式 1)
     * 14 00(2 点开)
     * 15 00 (3 点关)
     * 03(模式 3)
     * 14 00 (2 点开)
     * 15(3 点关)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     */
    private void buildParamByDayMode(StringBuilder message) {
        toStringByTimeArray(message, modeTimingMap);
    }

    /**
     * 时间数组转字符串
     *
     * @param message 字符串
     */
    private void toStringByTimeArray(StringBuilder message, ModeTimingMap[] modeTimingMaps) {
        if (modeTimingMaps != null) {
            // 加入时间段
            message.append(decimalToHex(modeTimingMaps.length));
            for (ModeTimingMap modeTimingMap : modeTimingMaps) {
                message.append(modeTimingMap);
            }
        }
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
