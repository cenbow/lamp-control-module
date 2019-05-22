package com.owen.lamp_control_module_common.utils;

import java.util.Random;

/**
 * <p>Title: KeyUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/19 14:23
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     *
     * @return 结果
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
