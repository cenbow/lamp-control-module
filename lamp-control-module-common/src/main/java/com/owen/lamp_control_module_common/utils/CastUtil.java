package com.owen.lamp_control_module_common.utils;

/**
 * <p>Title: CastUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/18 14:48
 */
public class CastUtil {

    /**
     * 转为 String 类型
     *
     * @param object
     * @return 结果
     */
    public static String castString(Object object) {
        return CastUtil.castString(object, "");
    }

    /**
     * 转为 String 类型（提供默认值）
     *
     * @param object
     * @param defaultValue
     * @return 结果
     */
    private static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转为 Double 型
     *
     * @param object
     * @return 结果
     */
    public static Double castDouble(Object object) {
        return CastUtil.castDouble(object, 0.0d);
    }

    /**
     * 转为 Double 型（提供默认值）
     *
     * @param object
     * @param defaultValue
     * @return 结果
     */
    private static Double castDouble(Object object, Double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为 Long 型
     *
     * @param object
     * @return 结果
     */
    public static Long castLong(Object object) {
        return CastUtil.castLong(object, 0L);
    }

    /**
     * 转为 Long 型（提供默认值）
     *
     * @param object
     * @param defaultValue
     * @return 结果
     */
    private static Long castLong(Object object, Long defaultValue) {
        long longValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为 Integer 型
     *
     * @param object
     * @return 结果
     */
    public static Integer castInt(Object object) {
        return CastUtil.castInt(object, 0);
    }

    /**
     * 转为 Integer 型（提供默认值）
     *
     * @param object
     * @param defaultValue
     * @return 结果
     */
    private static Integer castInt(Object object, Integer defaultValue) {
        int integerValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    integerValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    integerValue = defaultValue;
                }
            }
        }
        return integerValue;
    }


    /**
     * 转为 Boolean 型
     *
     * @param object
     * @return 结果
     */
    public static Boolean castBoolean(Object object) {
        return CastUtil.castBoolean(object, false);
    }

    /**
     * 转为 Boolean 型（提供默认值）
     *
     * @param object
     * @param defaultValue
     * @return 结果
     */
    private static Boolean castBoolean(Object object, Boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }
}
