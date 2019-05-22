package com.owen.lamp_control_module_common.param.component;

import com.owen.lamp_control_module_common.bean.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述 定时策略
 *
 * @author weilai
 * @version 1.0.0 2018/8/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalTimingStrategy implements Serializable {

    /**
     * 模式编号
     */
    private int strategyNum;

    /**
     * 通道号
     */
    private int loopNum;

    /**
     * 数据长度
     */
    private int dataLength;

    /**
     * 策略定时
     */
    protected TimingWithInterval timing;

    /**
     * 策略内容
     */
    protected String data;

    @Override
    public String toString() {
        String[] dataArray = data.split(Constants.SEPARATOR);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = decimalToHex(dataArray[i]);
            stringBuilder.append(dataArray[i]);
        }
        return (decimalToHex(strategyNum) +
                timing +
                decimalToHex(dataLength) +
                decimalToHex(loopNum) +
                stringBuilder.toString()).toUpperCase();
    }

}
