package com.owen.lamp_control_module_common.param.wall;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * <p>Title: ParamWallCmd601</p>
 * <p>Description: 创建终端所带的片选器参数</p>
 * <p>命令：0x0601
 * 数据内容：
 * - 片选器个数（1字节）
 * - 片选器ID（2字节）
 * 注释： "片选器个数范围：1-8，默认00001  片选器ID范围：00001-65535，默认00001"
 * 案例：
 * 示例1：2个片选器，片选器ID分别为 01 02
 * D5 C7 D5 C7（头码）
 * 0601（指令）
 * 00 05 （数据长度5）
 * 1204120E091003（时间戳）
 * 02 00 01 00 02（数据）
 * XX （累加低八位教验）
 * 13AB13AB（尾码）
 *</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamWallCmd601 extends BaseParam {

    public static final String CMD = getCmd(ParamWallCmd601.class.getSimpleName());

    /**
     * 片选区ID列表
     * 片选器ID范围：00001-65535，默认00001"
     */
    @ApiParam("片选区ID列表")
    private List<String> selectorIdList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(selectorIdList.size()));
        for (String selectorId : selectorIdList) {
            stringBuilder.append(decimalToHexFF(selectorId));
        }
        return stringBuilder.toString().replace(" ", "").toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
