package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * * 25
 * * 设置回路的单灯检测
 * * 0x00f2
 * * 服务器->网关
 * * 数据结构：
 * * 回路总数N（1字节）；
 * * 1#回路检测标识（1字节）；
 * * 2#~N#回路检测标识
 * * 回路检测标识：
 * * 0x01表示开启单灯检测功能
 * * 0x00表示关闭单灯检测功能
 * * 所有回路一起统一设置
 * * <p>
 * * 00 00
 * *
 *
 * @author weilai
 * @version 1.0.0 2018/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoopLightCheck implements Serializable {

    /**
     * 0x01表示开启单灯检测功能
     */
    public static final int OPEN = 1;

    /**
     * 0x00表示关闭单灯检测功能
     */
    public static final int CLOSE = 1;

    private int loopSum;

    private List<Integer> loopCheckFlag;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(loopSum));
        for (Integer flag : loopCheckFlag) {
            stringBuilder.append(decimalToHex(flag));
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static LoopLightCheck build() {
        LoopLightCheck loopLightCheck = new LoopLightCheck();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(OPEN);
        integerList.add(OPEN);
        integerList.add(OPEN);
        integerList.add(OPEN);
        loopLightCheck.setLoopSum(integerList.size());
        loopLightCheck.setLoopCheckFlag(integerList);
        return loopLightCheck;
    }

}
