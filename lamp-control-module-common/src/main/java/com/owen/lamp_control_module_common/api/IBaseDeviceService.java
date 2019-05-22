package com.owen.lamp_control_module_common.api;

import com.owen.lamp_control_module_common.entity.MessageFrame;

import java.lang.reflect.InvocationTargetException;

/**
 * @description: com.owen.lamp_control_module_common.api
 * @version: 1.0
 * @author: Owen
 * @date: 2019/4/28
 */
public interface IBaseDeviceService {

    /**
     * 指令处理器
     *
     * @param messageFrame 消息帧
     * @throws InvocationTargetException 目标不能执行异常
     * @throws IllegalAccessException    目标没有权限异常
     */
    void cmdProcess(MessageFrame messageFrame) throws InvocationTargetException, IllegalAccessException;


    /**
     * 发送数据 3位命令（附带数据）0F0
     *
     * @param deviceId 设备ID 设备ID
     * @param cmd      命令
     * @param data     数据对象
     * @return 结果 结果
     */
    void sendMessage(String deviceId, String cmd, Object... data);

    /**
     * 发送数据 3位命令（附带数据）0F0
     *
     * @param deviceId 设备ID 设备ID
     * @param cmd      命令
     * @param message  数据信息
     * @return 结果 结果
     */
    void sendMessage(String deviceId, String cmd, String message);

}
