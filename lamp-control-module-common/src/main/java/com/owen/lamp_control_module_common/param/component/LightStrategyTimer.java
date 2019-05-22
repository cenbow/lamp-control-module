package com.owen.lamp_control_module_common.param.component;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述：LightStrategyTimer
 *
 * @author weilai
 * @version 1.0.0 2018/9/7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LightStrategyTimer extends Timer {

    /**
     * 亮度
     */
    private int lightNess;

    public LightStrategyTimer() {
    }

    public LightStrategyTimer(String model, String startTime, String endTime, String startDelay, String endDelay, int lightNess) {
        super(model, startTime, endTime, startDelay, endDelay);
        this.lightNess = lightNess;
    }

    @Override
    public String toString() {
        return (super.toString() +
                decimalToHex(lightNess)).toUpperCase();
    }

}
