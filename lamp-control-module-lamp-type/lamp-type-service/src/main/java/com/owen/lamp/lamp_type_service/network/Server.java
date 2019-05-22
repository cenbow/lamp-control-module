package com.owen.lamp.lamp_type_service.network;

import com.owen.lamp.lamp_type_service.config.PropertiesConfig;
import com.owen.lamp.lamp_type_service.proerties.ServerProperties;
import com.owen.lamp_control_module_common.network.bootstrap.BaseServer;
import com.owen.lamp_control_module_common.properties.BaseNettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>Title: Server</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 18:00
 */
@Slf4j
@Component
@Order(value = 1)
public class Server extends BaseServer{

    private final ServerInitializer serverInitializer;
    private final ServerProperties serverProperties;
    private final ServerFuture serverFuture;

    public Server(
            ServerProperties serverProperties,
            ServerInitializer serverInitializer,
            ServerFuture serverFuture) {
        this.serverProperties = serverProperties;
        this.serverInitializer = serverInitializer;
        this.serverFuture = serverFuture;
    }

    @Override
    public void boot() {
        super.createServerBootstrap(
                serverProperties,
                serverFuture,
                serverInitializer);
    }


}
