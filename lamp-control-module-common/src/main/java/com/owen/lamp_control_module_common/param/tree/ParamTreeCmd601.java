package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamTreeCmd601</p>
 * <p>Description: 设置系统信息</p>
 * <p>
 * 命令数据: 0x0601
 * 数据:
 * - 节点数(1 字节) 节点数范围:1-255
 * - 灯具通道(1 字节) 通道范围:1-255
 * - 灯具类型(1 字节) 灯具类型:1 是照树灯
 * </p>
 * <p>
 * 示例:【节点数量 30，灯具通道数 4，照树灯 01】
 * 起始符: D5 C6 D5 C6
 * 命令: 06 01
 * 数据长度 3: 00 03
 * 时间: 12 04 12 0E 09 10 03
 * 节点数量  30->1E
 * 灯具通道数 4-> 04
 * 照树灯 1-> 01
 * 校验: XX
 * 结束符: 13 AB 13 AB
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamTreeCmd601 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd601.class.getSimpleName());

    /**
     * 节点数(1 字节) 节点数范围:1-255
     */
    @ApiParam("节点数(1 字节) 节点数范围:1-255")
    private int nodeNum;

    /**
     * 灯具通道(1 字节) 通道范围:1-255
     */
    @ApiParam("灯具通道(1 字节) 通道范围:1-255")
    private int channelNum;

    /**
     * 灯具类型(1 字节) 灯具类型:1 是照树灯
     */
    @ApiParam("灯具类型(1 字节) 灯具类型:1 是照树灯")
    private int lightType;

    @Override
    public String toString() {
        return (decimalToHex(nodeNum) + decimalToHex(channelNum) + decimalToHex(lightType)).toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
