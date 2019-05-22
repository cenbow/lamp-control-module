package com.owen.lamp_control_module_common.param.wall;

import com.owen.lamp_control_module_common.param.base.BaseParam;
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
 * <p>Title: ParamWallCmd602</p>
 * <p>Description: 实时调整节目模式</p>
 * <p>命令：0x0602
 * 数据内容：
 * - 片选器个数（1字节）
 * - 片选器ID（2字节）
 * - 模式编号（字节数根据片选器个数确定）
 * 注释：
 * 案例：
 * 示例:1:实时调整2个片选器节目，片选器01播放节目01，片选器02播放节目04
 * D5 C7 D5 C7（头码）  06 02（命令）  00 07（数据长度7）
 * 12 04 12 0E 09 10 03（时间戳）
 * 02 00 01 01 00 02 04（数据）
 * XX（累加低八位校验）  13 AB 13 AB(尾码)
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamWallCmd602 extends BaseParam {

    public static final String CMD = getCmd(ParamWallCmd602.class.getSimpleName());

    /**
     * 片选器个数（1字节）
     * 片选器ID（2字节）
     * 02
     * 0001 片选器ID 01 模式编号
     * 0002 片选其ID 04 模式编号
     */
    @ApiParam("片选器个数（1字节）")
    private Map<String, String> selectorIdAndProgram;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(selectorIdAndProgram.size()));
        Set<String> keySet = selectorIdAndProgram.keySet();
        for (String key : keySet) {
            // 片选器ID
            stringBuilder.append(decimalToHexFF(key));
            // 模式编号
            stringBuilder.append(decimalToHex(selectorIdAndProgram.get(key)));
        }
        return stringBuilder.toString().toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
