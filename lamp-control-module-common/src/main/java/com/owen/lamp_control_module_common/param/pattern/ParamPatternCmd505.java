package com.owen.lamp_control_module_common.param.pattern;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.TerminalTimingStrategy;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamPatternCmd505</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamPatternCmd505 extends BaseParam {

    public static final String CMD = getCmd(ParamPatternCmd505.class.getSimpleName());

    /**
     * 定时策略组
     */
    @ApiParam("定时策略组")
    private List<TerminalTimingStrategy> timingStrategies;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(timingStrategies.size()));
        for (TerminalTimingStrategy timingStrategy : timingStrategies) {
            stringBuilder.append(timingStrategy.toString());
        }
        return stringBuilder.toString().replace(" ", "").toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
