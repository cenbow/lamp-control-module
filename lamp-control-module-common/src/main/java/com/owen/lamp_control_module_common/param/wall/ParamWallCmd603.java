package com.owen.lamp_control_module_common.param.wall;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.RGBW;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * <p>Title: ParamWallCmd603</p>
 * <p>Description: 实时调整颜色和亮度模式</p>
 * <p>命令：0x0603
 * 数据内容：
 * - 片选器个数（1字节）
 * - 片选器ID（2字节）
 * - RGBW(颜色和亮度一共4字节)
 * 注释：
 * 案例：
 * 示例:1:实时调整2个片选器，片选器01绿色，亮度10，片选器02红色，亮度255
 * D5 C7 D5 C7（头码）  06 03（命令） 00 04（数据长度4）
 * 12 04 12 0E 09 10 03（时间戳）
 * 02
 * 00 01
 * 00 FF 00 0A
 * 00 02
 * FF 00 00 FF（数据）
 * XX（累加低八位校验）  13 AB 13 AB(尾码)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamWallCmd603 extends BaseParam {

    public static final String CMD = getCmd(ParamWallCmd603.class.getSimpleName());

    /**
     * 字符串选择器颜色和光照贴图
     */
    @ApiParam("字符串选择器颜色和光照贴图")
    private Map<Integer, RGBW> stringSelectorColorAndLightMap;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(stringSelectorColorAndLightMap.size()));
        Set<Integer> selectorIdSet = stringSelectorColorAndLightMap.keySet();
        for (Integer selectorId : selectorIdSet) {
            stringBuilder.append(decimalToHexFF(selectorId));
            stringBuilder.append(stringSelectorColorAndLightMap.get(selectorId));
        }
        return stringBuilder.toString().toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
