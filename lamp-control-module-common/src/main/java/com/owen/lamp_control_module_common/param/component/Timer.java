package com.owen.lamp_control_module_common.param.component;

import com.owen.lamp_control_module_common.bean.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.changeAddSpace;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;


/**
 * 功能描述：定时器
 *
 * @author weilai
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timer implements Serializable {

    /**
     * 模式
     * 00 全光控
     * 11 全经纬度控制
     * 22 全时控
     * <p>
     * time 定时
     * delay 延时
     * threshold 阈值
     * <p>
     * <p>
     * 策略=00时    开始时间（HH:MM) 结束时间（HH:MM) 开阈值（1字节）关阈值（1字节）
     * 策略=0x11时  提前或延后(1字节）时间（HH:MM) 提前或延后(1字节）时间（HH:MM)
     * 策略=0x12时  提前或延后(1字节）时间（HH:MM) 关时间（HH:MM) 备用数据（1字节）
     * 策略=0x21时  开时间（HH:MM) 提前或延后(1字节）时间（HH:MM) 备用数据（1字节）
     * 策略=0x22时  开时间（HH:MM) 关时间（HH:MM) 备用数据（2字节）
     */
    protected String model;

    /**
     * 2 字节 定时模式时 代表时刻 经纬模式时 代表时间 00 00 10 30 00 03
     */
    protected String startTime;

    /**
     * 2 字节 定时模式时 代表时刻 经纬模式时 代表时间 00 00
     */
    protected String endTime;

    /**
     * 开始延时 1字节
     */
    protected String startDelay;

    /**
     * 结束延时 1字节
     */
    protected String endDelay;

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append(model);
        switch (model) {
            case Constants.LAL_MODE:
                lalMode(data);
                break;
            case Constants.TIME_LAL:
                timeLal(data);
                break;
            case Constants.LAL_TIME:
                lalTime(data);
                break;
            case Constants.TIMER_MODE:
                timerMode(data);
                break;
            default:
                break;
        }
        return data.toString().toUpperCase();
    }

    protected void timerMode(StringBuilder data) {
        data.append(decimalToHex(startTime.substring(0, 2)))
                .append(decimalToHex(startTime.substring(2, 4)))
                .append(decimalToHex(endTime.substring(0, 2)))
                .append(decimalToHex(endTime.substring(2, 4)))
                .append("0000");
    }

    /**
     * 策略=0x12时  提前或延后(1字节）时间（HH:MM) 关时间（HH:MM) 备用数据（1字节）
     *
     * @param data
     */
    protected void lalTime(StringBuilder data) {
        data.append(decimalToHex(startDelay))
                .append("00")
                .append(decimalToHex(startTime))
                .append(decimalToHex(endTime.substring(0, 2)))
                .append(decimalToHex(endTime.substring(2, 4)))
                .append("00");
    }

    /**
     * 策略=0x21时  开时间（HH:MM) 提前或延后(1字节）时间（HH:MM) 备用数据（1字节）
     *
     * @param data
     */
    protected void timeLal(StringBuilder data) {
        data.append(decimalToHex(startTime.substring(0, 2)))
                .append(decimalToHex(startTime.substring(2, 4)))
                .append(decimalToHex(endDelay))
                .append("00")
                .append(decimalToHex(endTime))
                .append("00");
    }

    /**
     * 经纬度偏移量分钟转时间
     *
     * @param data
     */
    protected void lalMode(StringBuilder data) {
        startTime = minuteToTime(startTime);
        endTime = minuteToTime(endTime);
        data.append(decimalToHex(startDelay))
                .append("00")
                .append(decimalToHex(startTime))
                .append(decimalToHex(endDelay))
                .append("00")
                .append(decimalToHex(endTime));
    }

    /**
     * 分钟转时间
     * <p>
     * 例如    100 分钟 -> 0140
     *
     * @param minute
     * @return 结果
     */
    protected static String minuteToTime(String minute) {
        return changeAddSpace(Integer.parseInt(minute) / 60 + "") +
                changeAddSpace(Integer.parseInt(minute) % 60 + "");
    }

}