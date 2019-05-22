package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * <p>Title: ParamElectricCmd090</p>
 * <p>Description: 设置指定灯控器策略
 * 17
 * 设置灯策略-手动
 * 0x0090
 * 服务器->网关
 * "灯控器号；
 * 灯通道号位图；
 * 手动亮度（0~100）
 * 注：灯号为00时为全部操作（此时忽略通道号）,
 * 亮度为0为全关，
 * 1-100为全开全调光"	"
 * 总共4个字节
 * 灯号2个字节； 0000
 * 灯通道bitmap1个字节（bitmap, 每一位代表是否对该通道进行操作
 * 亮度1个字节" 0-100
 * 000A
 * 10
 * 64
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:05
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd090 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd090.class.getSimpleName());

    /**
     * 灯号
     */
    @ApiParam("灯号")
    private int lightNum;

    /**
     * 灯通道位图
     * <p>
     * 1 1号通道
     * 2 2号通道
     * 3 两个通道都亮
     */
    @ApiParam("灯通道位图")
    private int lightChannelBitMap;

    /**
     * 亮度
     */
    @ApiParam("亮度")
    private int lightNess;

    @Override
    public String toString() {
        return (decimalToHexFF(lightNum) + decimalToHex(lightChannelBitMap) + decimalToHex(lightNess)).toUpperCase();
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
