package com.owen.lamp.lamp_type_service.network;

import com.owen.lamp.lamp_type_service.proerties.ServerProperties;
import com.owen.lamp_control_module_common.network.decode.MessageFrameCodec;
import com.owen.lamp_control_module_common.network.initializer.BaseServerInitializer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.StringtoBytes;

/**
 * <p>Title: ClientInitializer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 18:02
 */
@Component
@ChannelHandler.Sharable
@Order(value = 1)
public class ServerInitializer extends BaseServerInitializer {

    private final ServerProperties serverProperties;
    private final MessageFrameCodec messageFrameCodec;
    private final ServerHandler serverHandler;

    public ServerInitializer(
            ServerProperties serverProperties,
            MessageFrameCodec messageFrameCodec,
            ServerHandler serverHandler) {
        this.serverProperties = serverProperties;
        this.messageFrameCodec = messageFrameCodec;
        this.serverHandler = serverHandler;
    }


    @Override
    protected void initChannel(SocketChannel ch) {
        // 设置允许半关闭 默认是自动关闭channel,改为true
        ch.config().setAllowHalfClosure(true);
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(
                new DelimiterBasedFrameDecoder(
                        serverProperties.getMaxFrameLength(),
                        serverProperties.isStripDelimiter(),
                        Unpooled.copiedBuffer(StringtoBytes(serverProperties.getDelimiter()))));

        channelPipeline.addLast((ChannelHandler) messageFrameCodec);
        channelPipeline.addLast(serverHandler);
    }
}
