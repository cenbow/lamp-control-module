package com.owen.lamp_control_module_application;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo(scanBasePackages = "com.owen.lamp.lamp_type_service.service")
@SpringBootApplication(scanBasePackages = {"com.owen"})
public class LampControlModuleApplication  {

    public static void main(String[] args) {
        SpringApplication.run(LampControlModuleApplication.class, args);
    }

}
