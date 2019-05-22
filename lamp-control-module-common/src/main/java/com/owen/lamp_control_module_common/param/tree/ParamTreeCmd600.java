package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamTreeCmd600</p>
 * <p>Description: 设置节点信息</p>
 * <p>
 * 协议版本：v1.20
 * 协议制定时间：2019.1.17.9:20
 * </p>
 * <p>
 * 命令数据: 0x0600
 * 数据:
 * - 信道(1 字节) 信道范围:0-255
 * - 地址(2 字节) 地址范围:1-65535
 * - 速 率(1 字节) 速率范围:1-8
 * - 空中数率（1 字节）：空中数率范围：1-6
 * </p>
 * <p>
 * 示例:【信道 2，地址 4660，速率 7，空中数率 6】
 * </p>
 * <p>
 * D5 C6 D5 C6
 * 06 00（命令）
 * 00 05（数据长度）
 * 12 04 12 0E 09 10 03 （时间戳）
 * 02 （信道）
 * 12 34 （地址）
 * 07 （速率）
 * 06 （空中速率）
 * XX （校验位）
 * 13 AB 13 AB（校验尾）
 * </p>
 * <p>
 * 起始符: D5 C6 D5 C6
 * 命令: 06 00
 * 数据长度 4: 00 04
 * 时间: 12 04 12 0E 09 10 03
 * 信道 2->02
 * 地址 4660-> 12 34
 * 速率 7-> 07
 * 空中速率 6 -> 06
 * 校验: XX
 * 结束符: 13 AB 13 AB
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamTreeCmd600 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd600.class.getSimpleName());

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }

    /**
     * 信道(1 字节) 信道范围:0-255
     */
    @ApiParam("信道(1 字节) 信道范围:0-255")
    private int channel;

    /**
     * 地址(2 字节) 地址范围:1-65535
     */
    @ApiParam("地址(2 字节) 地址范围:1-65535")
    private int address;

    /**
     * 速 率(1 字节) 速率范围:1-8
     */
    @ApiParam("速 率(1 字节) 速率范围:1-8")
    private int speed;

    /**
     * 空中数率（1 字节）：空中数率范围：1-6
     */
    @ApiParam("空中数率（1 字节）：空中数率范围：1-6")
    private int skySpeed;

    @Override
    public String toString() {
        return decimalToHex(channel) +
                decimalToHex(address) +
                decimalToHex(speed) +
                decimalToHex(skySpeed);
    }

    public static ParamTreeCmd600 build() {
        ParamTreeCmd600 paramTreeCmd600 = new ParamTreeCmd600();
        paramTreeCmd600.setChannel(2);
        paramTreeCmd600.setAddress(4660);
        paramTreeCmd600.setSpeed(7);
        paramTreeCmd600.setSkySpeed(6);
        return paramTreeCmd600;
    }

    /**
     * 测试
     *
     * @param args
     */
//    public static void main(String[] args) {
//        System.out.println(ParamTreeCmd600.build());
//    }
}
