package com.owen.lamp_control_module_common.cache;

import com.owen.lamp_control_module_common.bean.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: RedisService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 18:50
 */
@Repository
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return 结果
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @param time 过期时间，单位分钟
     * @return 结果 缓存的对象
     */
    public <T> ValueOperations<String, T> setObject(String key, T value, Long time) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, time, TimeUnit.MINUTES);
        return operation;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 结果 缓存的对象
     */
    public <T> ValueOperations<String, T> setObjectExpiredSeconds(String key, T value, Long time) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, time, TimeUnit.SECONDS);
        return operation;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 结果 缓存的对象
     */
    public <T> ValueOperations<String, T> setObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        this.setDefaultExpire(key);
        return operation;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 结果 缓存键值对应的数据
     */
    public <T> T getObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 结果 缓存的对象
     */
    public <T> ListOperations<String, T> setList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.rightPush(key, dataList.get(i));
            }
        }
        this.setDefaultExpire(key);
        return listOperation;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 结果 缓存键值对应的数据
     */
    public <T> List<T> getList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add((T) listOperation.leftPop(key));
        }

        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 结果 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        this.setDefaultExpire(key);
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key 缓存键值
     * @return 结果 缓存数据的对象
     */
    public Set getSet(String key) {
        Set dataSet = new HashSet();
        BoundSetOperations operation = redisTemplate.boundSetOps(key);

        Long size = operation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key     缓存键值
     * @param dataMap 缓存的数据
     * @return 结果
     */
    public <T> HashOperations<String, String, T> setMap(String key, Map<String, T> dataMap) {

        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        this.setDefaultExpire(key);
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key 缓存键值
     * @return 结果
     */
    public <T> Map<String, T> getMap(String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }


    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key 缓存的键值
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 设置默认失效时间
     *
     * @param key key
     */
    public void setDefaultExpire(String key) {
        this.redisTemplate.expire(key, Constants.REDIS_DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间
     * @param key key
     * @param time 时间，单位秒
     */
    public void setExpire(String key, Long time) {
        this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
}
