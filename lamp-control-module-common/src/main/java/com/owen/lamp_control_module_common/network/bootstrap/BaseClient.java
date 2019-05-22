package com.owen.lamp_control_module_common.network.bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.PreDestroy;

/**
 * <p>Title: BaseClient</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:34
 */
@Slf4j
public abstract class BaseClient implements CommandLineRunner {

    protected Channel channel;
    protected EventLoopGroup group;
    protected Bootstrap bootstrap = new Bootstrap();

    /**
     * 启动服务
     *
     * @throws InterruptedException 中断异常
     */
    protected abstract void createBootstrap();

    @PreDestroy
    public void close() {
        if (log.isInfoEnabled()) {
            log.info("Client closed!");
        }
        group.shutdownGracefully();
    }

    @Override
    public void run(String... args) {
        createBootstrap();
    }

    /**
     * 重连函数
     */
    protected abstract void doConnect();
}
