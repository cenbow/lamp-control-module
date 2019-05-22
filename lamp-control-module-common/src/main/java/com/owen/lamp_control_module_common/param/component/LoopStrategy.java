package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * 回路策略
 * 00 策略数量(1字节)
 * 00 策略类型（1字节）
 * 0000 0000 0000 策略数据（6字节）
 * 00 策略针对的回路组：（1字节）
 * <p>
 * <p>
 * 策略=0x00时 开始时间（HH:MM) 结束时间（HH:MM) 开阈值（1字节）关阈值（1字节）
 * 策略=0x11时 提前或延后(1字节）时间（HH:MM) 提前或延后(1字节）时间（HH:MM)
 * 策略=0x12时 提前或延后(1字节）时间（HH:MM) 关时间（HH:MM) 备用数据（1字节）
 * 策略=0x21时 开时间（HH:MM)提前或延后(1字节）时间（HH:MM) 备用数据（1字节）
 * 策略=0x22时 开时间（HH:MM)关时间（HH:MM) 备用数据（2字节）
 * 03 设置单回路策略数量
 * 22 设置失控开时空关
 * 0A0A 开时间 0B0A 关时间 0000
 * 0A0A 开时间 0B0A 关时间 0000
 * 0A0A 开时间 0B0A 关时间 0000
 * 01 回路号
 * <p>
 * 01
 * 22
 * 0B0B
 * 0C0C
 * 0000
 * 01
 *
 * @author weilai
 * @version 1.0
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class LoopStrategy implements Serializable {

    private int loopNum;

    private List<Timer> timerList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decimalToHex(timerList.size()));
        for (Timer timer : timerList) {
            stringBuilder.append(timer);
        }
        stringBuilder.append(decimalToHex(loopNum));
        return stringBuilder.toString().toUpperCase();
    }

    public static LoopStrategy build() {
        LoopStrategy strategy = new LoopStrategy();
        List<Timer> timers = new ArrayList<>();
        timers.add(new Timer("22", "1111", "1111", null, null));
        strategy.setTimerList(timers);
        strategy.setLoopNum(1);
        return strategy;
    }

}
