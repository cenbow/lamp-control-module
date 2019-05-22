package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 功能描述：灯策略
 * 数据结构：组数量（1字节）；1#组数据；2#组数据---N#组数据（最多可以8个组）
 * 每组数据的格式为：开关策略数量（1字节），调光策略数量（1字节）；1#策略类型；1#策略数据；---N#策略类型；N#策略数据；1#调光数据；2#调光数据---N#调光数据；策略针对的灯控器组和灯通道组
 * 策略类型（1字节） 0xab (a表示开灯策略，b表示关灯策略；0光控 1经纬度 2时控）
 * 具体而言：
 * 0x00表示光照控制、
 * 0x11表示经纬度开灯，经纬度关灯；
 * 0x12表示经纬度开灯，时控关灯
 * 0x21表示时控开灯，经纬度关灯；
 * 0x22表示时控开灯，时控关灯
 * 策略数据（7字节）
 * 策略=0x00时  开始时间（HH:MM) 结束时间（HH:MM) 开阈值(1字节) 关阈值（1字节) 亮度（1字节）
 * 策略=0x11时  提前或延后(1字节) 时间(HH:MM) 提前或延后(1字节) 时间（HH:MM) 亮度（1字节）
 * 策略=0x12时  提前或延后(1字节) 时间(HH:MM) 关时间（HH:MM) 备用数据（1字节）亮度（1字节）
 * 策略=0x21时  开时间（HH:MM) 提前或延后(1字节）时间（HH:MM) 备用数据（1字节）亮度（1字节）
 * 策略=0x22时  开时间（HH:MM) 关时间（HH:MM) 备用数据（2字节）亮度（1字节）
 * 调光数据（3字节）：
 * 调节亮度时间（HH:MM） 亮度 00 00 00
 * 策略针对的灯控器组：（64字节的bitmap，表示256个灯控器每个灯控器两个灯通道共计512个灯通道是否使用该策略）
 * 第一个字节.0=1表示该策略对1#灯控器的第1个灯通道有效，.1=1表示该策略对1#灯控器的第2个灯通道有效...
 * .7=1表示该策略对4#灯控器的2通道有效
 * 第二个字节对应于5#~8#灯；    ........；   第64个字节对应于253#~256#灯 FF 0000 0000
 * <p>
 * 00 组数量（1字节）
 * 00 开关策略数量（1字节）
 * 00 调光策略数量（1字节）
 * 00 策略类型（1字节)
 *
 * @author weilai
 * @version 1.0.0 2018/9/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LightStrategy implements Serializable {

    /**
     * 开关策略数据
     */
    private List<LightStrategyTimer> lightStrategyTimers;

    /**
     * 调光数据
     */
    private List<Dimming> dimmingList;

    /**
     * 策略针对的灯控器组和灯通道组
     * 参考实现
     * char[] loopGroup = new char[512];
     * for (int i = 0; i < loopGroup.length; i++) {
     * loopGroup[i] = '0';
     * }
     * loopGroup[3] = '1';
     * loopGroup[4] = '1';
     * loopGroup[5] = '1';
     * loopGroup[13] = '1';
     * loopGroup[14] = '1';
     * loopGroup[15] = '1';
     * loopGroup[123] = '1';
     * loopGroup[224] = '1';
     * loopGroup[325] = '1';
     * <p>
     * 04 03 02 01 00 00 00 01
     * 08 07 06 05 00 00 00 01
     * 12 11 10 09
     * <p>
     * 00 10 00 10
     * <p>
     * 256
     * <p>
     * 100
     * <p>
     * 100%4+1
     * <p>
     * 25
     */
    private Map<Integer, Integer> loopGroupMap = new HashMap<>();

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append(decimalToHex(lightStrategyTimers.size()))
                .append(decimalToHex(dimmingList.size()));
        for (LightStrategyTimer lightStrategyTimer : lightStrategyTimers) {
            data.append(lightStrategyTimer);
        }
        for (Dimming dimming : dimmingList) {
            data.append(dimming);
        }

        // -------------------------------------
        String[] bitmap = new String[256];
        for (int i = 0; i < bitmap.length; i++) {
            bitmap[i] = "00";
        }

        for (Integer lampDeviceId : loopGroupMap.keySet()) {
            bitmap[lampDeviceId - 1] = loopGroupMap.get(lampDeviceId) == 1 ? "01" : "10";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (String aBitmap : bitmap) {
            stringBuilder.append(aBitmap);
        }
        String tempStr = stringBuilder.toString();

        data.append(toData(tempStr));
        // -------------------------------------

        return data.toString().toUpperCase();
    }

    /**
     * 01010101
     * 010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
     * <p>
     * 10 01 10 01
     * 10 00 00 00
     * <p>
     * 01 10 01 10
     * 00 00 00 10
     *
     * @return 结果
     */
    public static LightStrategy build() {
        LightStrategy lightStrategy = new LightStrategy();
        // -------------------------------------------------------------------- //
        List<LightStrategyTimer> lightStrategyTimerList = new ArrayList<>();
        lightStrategyTimerList.add(new LightStrategyTimer("22", "11", "11", "12", "12", 100));
        lightStrategy.setLightStrategyTimers(lightStrategyTimerList);
        // -------------------------------------------------------------------- //
        List<Dimming> dimmingList = new ArrayList<>();
        dimmingList.add(new Dimming(10, 10, 10));
        dimmingList.add(new Dimming(10, 10, 10));
        dimmingList.add(new Dimming(10, 10, 10));
        lightStrategy.setDimmingList(dimmingList);
        // -------------------------------------------------------------------- //
        Map<Integer, Integer> loopGroup = new HashMap<>(3);
        loopGroup.put(1, 2);
        loopGroup.put(5, 2);
        loopGroup.put(9, 2);
        lightStrategy.setLoopGroupMap(loopGroup);
        // -------------------------------------------------------------------- //
        return lightStrategy;
    }

    /**
     * 01010101 0100000
     * 01010101 0000001
     *
     * @param bitmap
     * @return 结果
     */
    private String toData(String bitmap) {
        StringBuilder tempStr2 = new StringBuilder();
        // 64字节的bitmap，表示256个灯控器每个灯控器两个灯通道共计512个灯通道是否使用该策略）
        int bitNumber = 64;
        for (int i = 0; i < bitNumber; i++) {
            String tempStr = bitmap.substring(i * 8, (i + 1) * 8);
            StringBuilder num = new StringBuilder();
            for (int j = 4; j > 0; j--) {
                num.append(tempStr, (j - 1) * 2, j * 2);
            }
            tempStr2.append(decimalToHex(Integer.valueOf(num.toString(), 2)));
        }
        return tempStr2.toString();
    }
}