package com.owen.lamp_control_module_common.param.component;

import lombok.Data;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述：颜色和亮度
 *
 * @author weilai
 * @version 1.0.0 2018/8/17
 */
@Data
public class RGBW implements Serializable {

    /**
     * 红
     */
    private int red;

    /**
     * 绿
     */
    private int green;

    /**
     * 蓝
     */
    private int blue;

    /**
     * 白光
     */
    private int write;

    public RGBW() {
    }

    public RGBW(int red, int green, int blue, int write) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.write = write;
    }

    public RGBW(String data) {
        String[] colors = data.split(",");
        this.red = Integer.valueOf(colors[0]);
        this.green = Integer.valueOf(colors[1]);
        this.blue = Integer.valueOf(colors[2]);
        this.write = Integer.valueOf(colors[3]);
    }

    /**
     * 返回十六进制结果
     *
     * @return 结果
     */
    @Override
    public String toString() {
        return (decimalToHex(red) + decimalToHex(green) + decimalToHex(blue) + decimalToHex(write)).toUpperCase();
    }

}
