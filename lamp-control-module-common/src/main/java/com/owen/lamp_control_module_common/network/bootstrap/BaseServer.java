package com.owen.lamp_control_module_common.network.bootstrap;

import com.owen.lamp_control_module_common.network.initializer.BaseServerInitializer;
import com.owen.lamp_control_module_common.network.future.BaseFuture;
import com.owen.lamp_control_module_common.properties.BaseNettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.PreDestroy;

/**
 * <p>Title: BaseServer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:34
 */
@Slf4j
public abstract class BaseServer implements CommandLineRunner {

    private EventLoopGroup boss;

    private EventLoopGroup work;

    /**
     * 服务端启动方法
     */
    protected abstract void boot();

    /**
     * 创建服务启动器
     */
    protected void createServerBootstrap(BaseNettyProperties properties, BaseFuture future, BaseServerInitializer initializer) {
        try {
            // 配置服务端的NIO线程组
            boss = new NioEventLoopGroup(properties.getMaxThreads());
            work = new NioEventLoopGroup(properties.getMaxThreads());
            new ServerBootstrap()
                    .group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(initializer)
                    .bind(properties.getPort()).sync()
                    .addListener(future)
                    .channel().closeFuture();
        } catch (InterruptedException e) {
            if (log.isInfoEnabled()) {
                log.info("[出现异常] 释放资源");
            }
            // 优雅退出，释放线程池资源
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    @PreDestroy
    public void close() {
        if (log.isInfoEnabled()) {
            log.info("[关闭服务器]");
        }
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    @Override
    public void run(String... args) {
        boot();
    }
}
