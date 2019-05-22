package com.owen.lamp_control_module_common.network.session;

import com.owen.lamp_control_module_common.exception.YcException;
import io.netty.channel.Channel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.owen.lamp_control_module_common.bean.Constants.UNDERLINE;

/**
 * <p>Title: ChannelSession</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 10:00
 */
@Data
@Component
public class ChannelSession {

    private Map<String, Channel> channelMap = new HashMap<>();

    /**
     * 添加 Channel
     *
     * @param head
     * @param deviceId 设备ID
     * @param channel
     */
    public void put(String head, String deviceId, Channel channel) {
        channelMap.put(head + UNDERLINE + deviceId, channel);
    }

    /**
     * 移除 Channel
     *
     * @param key
     */
    public void removeChannel(String key) {
        channelMap.remove(key);
    }

    /**
     * Session是否在线
     *
     * @param head
     * @param deviceId 设备ID
     * @return 结果
     */
    public boolean isOnline(String head, String deviceId) {
        return channelMap.get(head + UNDERLINE + deviceId) != null;
    }

    /**
     * 获取 Channel
     *
     * @param key
     * @return 结果
     * @throws YcException 自定义异常
     */
    public Channel get(String key) throws YcException {
        return channelMap.get(key);
    }
}
