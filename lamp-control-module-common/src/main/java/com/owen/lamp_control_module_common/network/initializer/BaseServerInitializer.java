package com.owen.lamp_control_module_common.network.initializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: BaseServerInitializer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:37
 */
@Slf4j
public abstract class BaseServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        try {
            System.out.println("------------------connected");
            Channel channel = ctx.channel();
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent e = (IdleStateEvent) evt;
                if (e.state() == IdleState.READER_IDLE) {
                    channel.close();
                    if (log.isDebugEnabled()) {
                        log.debug(channel.remoteAddress() + "---No data was received for a while ,read time out... ...");
                    }
                } else if (e.state() == IdleState.WRITER_IDLE) {
                    channel.close();
                    if (log.isDebugEnabled()) {
                        log.debug(channel.remoteAddress() + "---No data was sent for a while.write time out... ...");
                    }
                }
            } else if (evt instanceof ChannelInputShutdownEvent) {
                channel.close();
                if (log.isDebugEnabled()) {
                    log.debug(channel.remoteAddress() + "远程主机强制关闭连接");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }
}
