package com.owen.lamp.lamp_type_service;

import com.owen.lamp.lamp_type_service.proerties.ServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ServerProperties.class)
@SpringBootApplication(scanBasePackages = "com.owen")
public class LampTypeServiceApplication {

}
