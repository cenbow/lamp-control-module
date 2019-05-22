package com.owen.lamp.lamp_type_service.proerties;

import com.owen.lamp_control_module_common.properties.BaseNettyProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title: ServerProperties</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 17:34
 */
@Data
@Component
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "smartcity.lamp")
public class ServerProperties extends BaseNettyProperties {
}
