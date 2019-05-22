package com.owen.lamp_control_module_common.network.decode;

import com.owen.lamp_control_module_common.entity.MessageFrame;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.bytesToHexString;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.hexStringToBytes;

/**
 * <p>Title: MessageFrameCodec</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:35
 */
@Component
@ChannelHandler.Sharable
public class MessageFrameCodec extends MessageToMessageCodec<ByteBuf, MessageFrame> {

    /**
     * 将{@link MessageFrame}对象转化为{@link ByteBuf}对象
     * @see MessageToMessageEncoder#encode(ChannelHandlerContext, Object, List)
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageFrame msg, List<Object> out) throws Exception {
        out.add(Unpooled.copiedBuffer(hexStringToBytes(msg.toString())));
    }

    /**
     * 将{@link ByteBuf}对象转化为{@link MessageFrame}对象
     * @see MessageToMessageDecoder#decode(ChannelHandlerContext, Object, List)
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.out.println(msg.toString());
        byte[] array = new byte[msg.readableBytes()];
        msg.getBytes(0, array);
        out.add(new MessageFrame(bytesToHexString(array)));
    }
}
