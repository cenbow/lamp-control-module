package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;


/**
 * 功能描述：时间节点
 *
 * @author weilai create by 2018/11/21:4:55 PM
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timing implements Serializable {

    /**
     * 开始时间小时位
     */
    private int startHour;

    /**
     * 开始时间分钟位
     */
    private int startMinute;

    /**
     * 结束时间小时位
     */
    private int endHour;

    /**
     * 结束时间分钟位
     */
    private int endMinute;

    @Override
    public String toString() {
        return (decimalToHex(startHour) +
                decimalToHex(startMinute) +
                decimalToHex(endHour) +
                decimalToHex(endMinute)).toUpperCase();
    }

}
