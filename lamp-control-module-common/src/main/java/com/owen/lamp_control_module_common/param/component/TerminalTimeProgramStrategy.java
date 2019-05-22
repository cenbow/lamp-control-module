package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述：TerminalTimeProgramStrategy
 *
 * @author weilai
 * @version 1.0.0 2018/8/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalTimeProgramStrategy implements Serializable {

    /**
     * 策略定时
     */
    protected Timing timing;

    /**
     * 策略内容
     */
    protected List<ProgramSelector> programSelectors;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timing);
        // 片选器数量
        stringBuilder.append(decimalToHex(programSelectors.size()));
        for (ProgramSelector ps : programSelectors) {
            stringBuilder.append(ps);
        }
        // 获得节目大小
        return stringBuilder.toString().toUpperCase();
    }

}
