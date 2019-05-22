package com.owen.lamp_control_module_common.network.future;

import com.owen.lamp_control_module_common.properties.BaseNettyProperties;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>Title: BaseFuture</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:36
 */
@Slf4j
@Component
public abstract class BaseFuture implements ChannelFutureListener {

    private final BaseNettyProperties baseNettyProperties;

    protected BaseFuture(BaseNettyProperties baseNettyProperties) {
        this.baseNettyProperties = baseNettyProperties;
    }

    @Override
    public void operationComplete(ChannelFuture future) {
        // 如果操作成功，打印消息
        if (future.isSuccess()) {
            if (log.isInfoEnabled()) {
                log.info("[{}服务启动成功! 正在监听：{}]", baseNettyProperties.getName(), baseNettyProperties.getPort());
            }
        }
        // 如果发生错误，则打印输出错误原因
        else {
            Throwable cause = future.cause();
            cause.printStackTrace();
        }
    }
}
