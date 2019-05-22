package com.owen.lamp.lamp_type_service.config;

import com.owen.lamp.lamp_type_service.proerties.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: PropertiesConfig</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 17:28
 */
@Configuration
@EnableConfigurationProperties(ServerProperties.class)
public class PropertiesConfig {
}
