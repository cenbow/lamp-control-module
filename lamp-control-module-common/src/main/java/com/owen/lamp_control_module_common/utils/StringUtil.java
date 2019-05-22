package com.owen.lamp_control_module_common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Title: StringUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/18 15:04
 */
public class StringUtil {
    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

    /**
     * 判断 字符串 是否为空
     *
     * @param string 待判断字符串
     * @return 结果 判断结果
     */
    public static boolean isEmpty(String string) {
        if (string != null) {
            string = string.trim();
        }
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断 字符串 是否非空
     *
     * @param string 待检测字符串
     * @return 结果 检测结果
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 分割字符串
     *
     * @param body           字符串体
     * @param separatorChars 分割字符
     * @return 结果 分割结果
     */
    public static String[] splitString(String body, String separatorChars) {
        return StringUtils.split(body, separatorChars);
    }

}
