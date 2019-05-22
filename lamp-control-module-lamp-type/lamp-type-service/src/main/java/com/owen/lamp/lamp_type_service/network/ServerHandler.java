package com.owen.lamp.lamp_type_service.network;

import com.owen.lamp.lamp_type_api.api.IIotElectricDeviceService;
import com.owen.lamp.lamp_type_api.api.IIotPatternLigthService;
import com.owen.lamp.lamp_type_api.api.IIotTreeLightService;
import com.owen.lamp.lamp_type_api.api.IIotWallLightService;
import com.owen.lamp_control_module_common.entity.MessageFrame;
import com.owen.lamp_control_module_common.network.session.ChannelSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

import static com.owen.lamp_control_module_common.bean.Constants.*;

/**
 * <p>Title: ServerHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 18:02
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<MessageFrame> {

    private final IIotElectricDeviceService iIotElectricDeviceService;
    private final IIotPatternLigthService iIotPatternLigthService;
    private final IIotTreeLightService iIotTreeLightService;
    private final IIotWallLightService iIotWallLightService;
    private final ChannelSession channelSession;

    @Autowired
    public ServerHandler(
            IIotElectricDeviceService iIotElectricDeviceService,
            IIotPatternLigthService iIotPatternLigthService,
            IIotTreeLightService iIotTreeLightService,
            IIotWallLightService iIotWallLightService,
            ChannelSession channelSession
    ) {
        this.iIotElectricDeviceService = iIotElectricDeviceService;
        this.iIotPatternLigthService = iIotPatternLigthService;
        this.iIotTreeLightService = iIotTreeLightService;
        this.iIotWallLightService = iIotWallLightService;
        this.channelSession = channelSession;
    }

    // ---------------- Handler 处理区 ------------------

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageFrame messageFrame) {
        String head = messageFrame.getHead();
        String id = messageFrame.getId();
        channelSession.put(head, id, ctx.channel());
        if (log.isInfoEnabled()) {
            log.info("<<----------------------------<<");
            log.info("[服务器 <- {}] ID:{}", messageFrame.getHead(), messageFrame.getId());
            log.info("[服务器 <- {}] 响应:{}", messageFrame.getHead(), messageFrame.getResponseStatus());
            log.info("[服务器 <- {}] 指令:{}", messageFrame.getHead(), messageFrame.getCmd());
            log.info("[服务器 <- {}] 数据:{}", messageFrame.getHead(), messageFrame.getData());
            log.info("[服务器 <- {}] 消息:{}", messageFrame.getHead(), messageFrame);
            log.info("<<----------------------------<<");
        }
        // 根据 校验头区分不同的设备处理逻辑入口
        try {
            switch (head) {
                // 电力设备处理器
                case IOT_ELECTRIC_HEAD:
                    synchronized (iIotElectricDeviceService) {
                        iIotElectricDeviceService.cmdProcess(messageFrame);
                    }
                    break;
                // 图案灯处理器
                case IOT_PATTERN_HEAD:
                    synchronized (iIotPatternLigthService) {
                        iIotPatternLigthService.cmdProcess(messageFrame);
                    }
                    break;
                // 照树灯处理器
                case IOT_TREE_HEAD:
                    synchronized (iIotTreeLightService) {
                        iIotTreeLightService.cmdProcess(messageFrame);
                    }
                    break;
                // 洗墙灯处理器
                case IOT_WALL_HEAD:
                    synchronized (iIotWallLightService) {
                        iIotWallLightService.cmdProcess(messageFrame);
                    }
                    break;
                default:
                    break;
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
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
