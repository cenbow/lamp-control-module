package com.owen.lamp_control_module_common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Title: BaseFrame</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseFrame implements Serializable {

    /**
     * 消息发送者者
     */
    private String sender;

    /**
     * 消息接收者
     */
    private String receiver;

    /**
     * 发送的命令
     */
    private String command;

    /**
     * 负载对象
     */
    private String obj;
}
