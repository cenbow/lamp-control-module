package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * 功能描述 定时抽象类
 * 14 开始时间小时位
 * 00 开始时间分钟位
 * 15 结束时间小时位
 * 00 结束时间分钟位
 * 000A 时间间隔
 *
 * @author weilai
 * @version 1.0.0 2018/8/17
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TimingWithInterval extends Timing {

    /**
     * 时间间隔
     */
    private int interval;

    public TimingWithInterval() {
    }

    public TimingWithInterval(int startHour, int startMinute, int endHour, int endMinute, int interval) {
        super(startHour, startMinute, endHour, endMinute);
        this.interval = interval;
    }

    @Override
    public String toString() {
        return super.toString() + decimalToHexFF(interval);
    }

}
