package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述：定时策略组
 *
 * @author weilai create by 2018-12-24:12:27
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeTimingMap implements Serializable {

    /**
     * 定时模式
     */
    private Integer model;

    /**
     * 定时时间
     */
    private Timing timing;

    @Override
    public String toString() {
        return decimalToHex(model) + timing;
    }
}
