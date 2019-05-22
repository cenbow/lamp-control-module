package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述：节假日实体
 *
 * @author weilai create by 2018/11/21:4:55 PM
 * @version 1.0
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Holiday implements Serializable {

    /**
     * 年份
     */
    private int year;

    /**
     * 月份
     */
    private int month;

    /**
     * 号
     */
    private int day;

    @Override
    public String toString() {
        return decimalToHex(year) + decimalToHex(month) + decimalToHex(day);
    }
}
