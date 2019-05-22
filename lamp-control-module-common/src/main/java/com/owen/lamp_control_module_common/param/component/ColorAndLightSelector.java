package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * 功能描述 颜色和亮度片选器
 * 02（2个颜色）
 * 00 02(ID)
 * FF (红) 00（绿） 0A(蓝) FF(亮度)
 * 00 (红) 00（绿） FF(蓝) FF(亮度)
 *
 * @author weilai
 * @version 1.0.0 2018/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorAndLightSelector implements Serializable {

    /**
     * 片选器ID
     */
    private int id;

    /**
     * 颜色和亮度
     */
    private List<RGBW> RGBWList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(RGBWList.size()));
        stringBuilder.append(decimalToHexFF(id));
        for (RGBW RGBW : RGBWList) {
            stringBuilder.append(RGBW);
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static ColorAndLightSelector build() {
        List<RGBW> RGBWList = new ArrayList<>();
        RGBWList.add(new RGBW(155, 155, 155, 155));
        RGBWList.add(new RGBW(155, 155, 155, 155));
        RGBWList.add(new RGBW(155, 155, 155, 155));
        return new ColorAndLightSelector(13, RGBWList);
    }

}
