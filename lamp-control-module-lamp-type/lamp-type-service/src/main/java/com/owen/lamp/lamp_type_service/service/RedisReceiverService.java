package com.owen.lamp.lamp_type_service.service;

import com.owen.lamp_control_module_common.cache.RedisService;
import com.owen.lamp_control_module_common.network.session.ChannelSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.owen.lamp_control_module_common.bean.Constants.*;

/**
 * <p>Title: RedisReceiverService</p>
 * <p>Description: redis监听服务</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 17:59
 */
@Slf4j
@Service
public class RedisReceiverService {

    @Autowired
    private ChannelSession channelSession;

    private final RedisService redisService;

    @Autowired
    public RedisReceiverService(RedisService redisService) {
        this.redisService = redisService;
    }

    public void receiveMessage(String message) {
        String[] tempStr = message.split(UNDERLINE);
        channelSession.removeChannel(tempStr[1]);
        switch (tempStr[0]) {
            case IOT_ELECTRIC_HEAD:
                if (log.isInfoEnabled()) {
                    log.info("电力设备:ID_{} 离线", tempStr[1]);
                }
                electric(tempStr[1]);
                break;
            case IOT_PATTERN_HEAD:
                if (log.isInfoEnabled()) {
                    log.info("图案灯设备:ID_{} 离线", tempStr[1]);
                }
                patternGateWayOffLine(tempStr[1]);
                break;
            case IOT_TREE_HEAD:
                if (log.isInfoEnabled()) {
                    log.info("照树灯设备:ID_{} 离线", tempStr[1]);
                }
                treeGateWayOffLine(tempStr[1]);
                break;
            case IOT_WALL_HEAD:
                if (log.isInfoEnabled()) {
                    log.info("洗墙灯设备:ID_{} 离线", tempStr[1]);
                }
                wallGateWayOffLine(tempStr[1]);
                break;
            default:
                break;
        }
    }

    /**
     * 电力设备离线处理逻辑
     *
     * @param deviceId 设备ID
     */
    private void electric(String deviceId) {

        throw new NotImplementedException();
    }

    /**
     * 洗墙灯网关掉线
     *
     * @param gateWayId
     */
    private void wallGateWayOffLine(String gateWayId) {

        throw new NotImplementedException();
    }

    /**
     * 图案灯网关掉线
     *
     * @param gateWayId
     */
    private void patternGateWayOffLine(String gateWayId) {

        throw new NotImplementedException();
    }

    /**
     * 照树灯网关掉线
     *
     * @param gateWayId
     */
    private void treeGateWayOffLine(String gateWayId) {

        throw new NotImplementedException();
    }
}
