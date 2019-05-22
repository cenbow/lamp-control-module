package com.owen.lamp_control_module_common.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * <p>Title: BaseNettyProperties</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 17:35
 */
@Data
@Component
public class BaseNettyProperties {

    /**
     * Netty 监听端口号
     */
    protected int port;

    /**
     * 服务名称
     */
    protected String name;

    /**
     * 最大线程数
     */
    protected int maxThreads = 4;

    /**
     * 最大数据包帧长度
     */
    protected int maxFrameLength = 2048;

    /**
     * 剥离分隔符
     */
    protected boolean stripDelimiter = false;

    /**
     * 分隔符
     */
    protected String delimiter = "13AB13AB";
}
