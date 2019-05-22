package com.owen.lamp_control_module_common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * <p>Title: Base64Util</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/18 14:45
 */
public class Base64Util {

    /**
     * 解码
     *
     * @param value 值
     * @return 结果 返回值
     */
    public static String decode(String value, String charsetName) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(value), charsetName);
    }

    /**
     * 编码
     *
     * @param value 值
     * @return 结果 返回值
     */
    public static String encode(String value, String charsetName) {
        try {
            return Base64.getEncoder().encodeToString(value.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
