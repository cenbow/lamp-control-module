package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述
 * 14 00 15 00 00 0A
 * 02(片选器个数)
 * 02（2个颜色）
 * 00 01(ID)
 * FF（红）00(绿) 0A(蓝) FF(亮度)
 * FF (红)00（绿）00（蓝）FF（亮度）
 * 00 02(ID)
 * FF (红) 00（绿） 0A(蓝) FF(亮度)
 * 00 (红) 00（绿） FF(蓝) FF(亮度)
 * 0014 0014 0014
 * 001414030D9B9B9B9B9B9B9B9B9B9B9B9B030D9B9B9B9B9B9B9B9B9B9B9B9B
 *
 * @author weilai
 * @version 1.0.0 2018/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalTimePaletteAndLightStrategy implements Serializable {

    /**
     * 策略定时
     */
    protected Timing timing;

    /**
     * 策略内容
     */
    protected List<ColorAndLightSelector> colorAndLightSelectors;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timing);
        for (ColorAndLightSelector selector : colorAndLightSelectors) {
            stringBuilder.append(selector);
        }
        return stringBuilder.toString().toUpperCase();
    }

}
