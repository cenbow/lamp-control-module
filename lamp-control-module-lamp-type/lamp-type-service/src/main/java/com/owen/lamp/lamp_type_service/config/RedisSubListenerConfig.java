package com.owen.lamp.lamp_type_service.config;

import com.owen.lamp.lamp_type_service.service.RedisReceiverService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * <p>Title: RedisSubListenerConfig</p>
 * <p>Description: Redis订阅监听配置</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 17:29
 */
@Configuration
public class RedisSubListenerConfig {

    @Bean
    public RedisMessageListenerContainer container(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("__key*__:*"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisReceiverService redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }

    @Bean
    public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
