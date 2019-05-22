package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamElectricCmd0F2</p>
 * <p>Description: 设置回路单灯检测
 * 数据结构：
 * 回路总数N（1字节）；
 * 1#回路检测标识（1字节）；
 * 2#~N#回路检测标识
 * 回路检测标识：
 * 0x01表示开启单灯检测功能
 * 0x00表示关闭单灯检测功能
 *
 * 010102010301040105010601
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 15:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd0F2 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd0F2.class.getSimpleName());

    /**
     * 0x01表示开启单灯检测功能
     */
    public static final int OPEN = 1;

    /**
     * 0x00表示关闭单灯检测功能
     */
    public static final int CLOSE = 0;

    /**
     * 回路检测列表
     */
    @ApiParam("回路检测列表")
    private List<Integer> loopCheckFlag;

    public ParamElectricCmd0F2() {
    }

    public ParamElectricCmd0F2(List<Integer> loopCheckFlag) {
        this.loopCheckFlag = loopCheckFlag;
    }

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(loopCheckFlag.size()));
        for (Integer flag : loopCheckFlag) {
            stringBuilder.append(decimalToHex(flag));
        }
        return stringBuilder.toString().toUpperCase();
    }
}
