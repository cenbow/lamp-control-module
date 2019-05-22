package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述：调光实体
 * <p>
 * 三个字节
 * HH:MM 亮度
 *
 * @author weilai
 * @version 1.0.0 2018/9/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dimming implements Serializable {

    /**
     * 小时
     */
    private int hour;

    /**
     * 分钟
     */
    private int minute;

    /**
     * 亮度
     */
    private int lightNess;

    @Override
    public String toString() {
        return (decimalToHex(hour) + decimalToHex(minute) + decimalToHex(lightNess)).toUpperCase();
    }

}
