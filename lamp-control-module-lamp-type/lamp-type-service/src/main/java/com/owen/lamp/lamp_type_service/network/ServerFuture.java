package com.owen.lamp.lamp_type_service.network;

import com.owen.lamp.lamp_type_api.api.IIotElectricDeviceService;
import com.owen.lamp.lamp_type_api.api.IIotPatternLigthService;
import com.owen.lamp.lamp_type_api.api.IIotTreeLightService;
import com.owen.lamp.lamp_type_api.api.IIotWallLightService;
import com.owen.lamp.lamp_type_service.proerties.ServerProperties;
import com.owen.lamp_control_module_common.entity.MessageFrame;
import com.owen.lamp_control_module_common.network.future.BaseFuture;
import com.owen.lamp_control_module_common.network.session.ChannelSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

import static com.owen.lamp_control_module_common.bean.Constants.*;

/**
 * <p>Title: ServerFuture</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 18:01
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerFuture extends BaseFuture {

    public ServerFuture(@Qualifier("serverProperties") ServerProperties serverProperties) {
        super(serverProperties);
    }
}
