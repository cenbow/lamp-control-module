package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamTreeCmd604</p>
 * <p>Description:  设置亮灯模式
 * 命令：0604
 * 数据：模式编号(1 字节)模式数据(同实时控制灯具模式数据)
 * 描述：模式编号范围:1-6
 * 示例:
 * 【场景 1，无痕渐变，W 保持跟随变化,最小 30，最大 255，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒度 5 级】
 * D5 C6 D5 C6(起始符)06 04(命令)00 0E(数据长度 14)12 04 12 0E 09 10 03(时间)
 * -------------------
 * 01(场景 1)
 * 05(W 保持跟随变化)
 * 1E(最小 30)
 * FF(最大 255)
 * 05(渐变粒度 5 级)
 * 00(起始保持时间 0s)
 * 02(结束保持时间 2s)
 * 04(4 种颜色轮播)
 * 01(红)
 * 02(绿)
 * 03(蓝)
 * 07(青)
 * 00 0f(15 秒一次轮播)
 * -------------------
 * XX(校验)13 AB 13 AB(结束符)
 *
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:35
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamTreeCmd604 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd604.class.getSimpleName());

    /**
     * 场景编号
     */
    @ApiParam("场景编号")
    private int mode;

    /**
     * 场景数据
     */
    @ApiParam("场景数据")
    private ParamTreeCmd602 data;

    @Override
    public String toString() {
        return decimalToHex(mode) + data;
    }

    public static ParamTreeCmd604 build(int carouselMode, ParamTreeCmd602 data) {
        ParamTreeCmd604 paramTreeCmd604 = new ParamTreeCmd604();
        paramTreeCmd604.setMode(carouselMode);
        paramTreeCmd604.setData(data);
        return paramTreeCmd604;
    }

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
