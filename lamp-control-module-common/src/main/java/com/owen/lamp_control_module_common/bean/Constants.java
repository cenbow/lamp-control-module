package com.owen.lamp_control_module_common.bean;

/**
 * <p>Title: Constants</p>
 * <p>Description: 常量类</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 10:09
 */
public class Constants {

    /**
     * redis默认失效时间
     */
    public static final Long REDIS_DEFAULT_EXPIRE = 3600 * 1L;

    /**
     * session过期时间默认 30 分钟
     */
    public static final int SESSION_EXPIRE = 30 * 60;

    /**
     * session key值
     */
    public static final String USERC_NAME = "USERC_NAME";

    // ------------------ 分隔符 --------------------

    public static final String SEPARATOR = ",";
    public static final String AND = "&";
    public static final String UNDERLINE = "_";
    // ------------------ 分隔符 --------------------

    //==================================================================================
    //                          用户中心 模块
    //==================================================================================

    public static final String USER_LOGIN = "LOGIN_";

    /**
     * 用户信息缓存KEY的前缀
     */
    public static final String USERC_PREFIX = "USER_";

    /**
     * 项目信息缓存KEY的前缀
     */
    public static final String PROJECT_PREFIX = "PROJECT_";

    /**
     * 组织信息缓存KEY的前缀
     */
    public static final String ORGAN_PREFIX = "ORGAN_";

    public static final String USERS = "USERS";
    public static final String PROJECTS = "PROJECTS";
    public static final String ORGANS = "ORGANS";
    public static final String ALARMS = "ALARMS";
    public static final String DEVICES = "DEVICES";
    public static final String DEVICE_STRATEGY = "STRATEGY";

    public static final String LOOP_1 = "1";
    public static final String LOOP_2 = "2";
    public static final String LOOP_3 = "3";
    public static final String LOOP_4 = "4";
    public static final String LOOP_5 = "5";
    public static final String LOOP_6 = "6";

    /**
     * 默认的电流上下限
     */
    public static final String CURRENT = "0,0";

    /**
     * 告警
     */
    public static final String ALARM_0 = "ALARM_0";

    /**
     * 已解决
     */
    public static final String ALARM_1 = "ALARM_1";

    /**
     * 已删除
     */
    public static final String ALARM_2 = "ALARM_2";

    //==================================================================================
    //                          设备信息 模块
    //==================================================================================

    /**
     * 集中器设备KEY的前缀
     */
    public static final String HUB_DEVICE_PREFIX = "HUB_DEVICE_";

    /**
     * 图案灯具类型KEY的前缀
     */
    public static final String LAMP_TYPE_PREFIX = "LAMP_TYPE_";

    /**
     * 图案灯控制器KEY的前缀
     */
    public static final String PATTERN_CONTROL_PREFIX = "PATTERN_CONTROL_";

    /**
     * 图案灯控制器模式KEY的前缀
     */
    public static final String PATTERN_MODEL_PREFIX = "PATTERN_MODEL_";

    /**
     * 图案灯控制器模式KEY的前缀
     */
    public static final String PATTERN_SCENE_PREFIX = "PATTERN_SCENE_";

    /**
     * 图案灯节目KEY的前缀
     */
    public static final String PATTERN_ITEM_PREFIX = "PATTERN_ITEM_";

    /**
     * 图案灯场景时刻 KEY的前缀
     */
    public static final String SCENE_TIME_PREFIX = "SCENE_TIME_";


    /**
     * 片选器KEY的前缀
     */
    public static final String WALL_SELECTOR_PREFIX = "WALL_SELECTOR_";

    /**
     * 洗墙灯网关 key的前缀
     */
    public static final String WALL_GATEWAY_PREFIX = "WALL_GATEWAY_";

    /**
     * 洗墙灯网关 key的前缀
     */
    public static final String WALL_TIME_PREFIX = "WALL_TIME_";

    public static final String WALL_ITEM_PREFIX = "WALL_ITEM_";
    /**
     * 设备最大的ID号
     */
    public static final String MAX_DEVICE_ID = "MAX_DEVICE_ID";

    /**
     * 项目下的设备列表
     */
    public static final String DEVICE_ALARM = "ALARM";

    /**
     * 不需要告警设备
     */
    public static final String DEVICE_NO_ALARM = "NO_ALARM";

    /**
     * 节目
     */
    public static final String ITEM = "ITEM";

    /**
     * 图案灯网关
     */
    public static final String PATTERN_GATEWAY = "PATTERN_GATEWAY";

    /**
     * 图案灯模式
     */
    public static final String PATTERN_MODEL = "PATTERN_MODEL";

    /**
     * 道路灯具缓存key
     */
    public static final String POLE_LAMP_KEY = "POLE_LAMP_";


    //==================================================================================
    //                          告警 模块
    //==================================================================================

    /**
     * 告警类别KEY的前缀
     */
    public static final String ALARM_DEFAULT_PREFIX = "ALARM_DEFAULT_";

    /**
     * 告警消息KEY的前缀
     */
    public static final String ALARM_MESSAGE_PREFIX = "ALARM_MESSAGE_";

    /**
     * 告警设置KEY的前缀
     */
    public static final String ALARM_SETTING_PREFIX = "ALARM_SETTING_";


    /**
     * 告警信息发送次数
     * 同一个告警只发送三次
     */
    public static final Integer SMS_SEND = 3;

    //==================================================================================
    //                          策略 模块
    //==================================================================================

    /**
     * 策略模式缓存信息KEY的前缀
     */
    public static final String STRATEGY_PREFIX = "STRATEGY_";

    /**
     * 分组缓存信息KEY的前缀
     */
    public static final String GROUP_PREFIX = "GROUP_";

    /**
     * 时间方案缓存信息KEY的前缀
     */
    public static final String TIME_PREFIX = "TIME_PLAN_";

    /**
     * 项目是否有洗墙灯网关key
     */
    public static final String PROJECT_WALL_FLAG = "PROJECT_WALL_FLAG";

    /**
     * 项目是否有洗墙灯网关VALUE
     */
    public static final String PROJECT_WALL_YES = "1";

    /**
     * 项目是否有洗墙灯网关VALUE
     */
    public static final String PROJECT_WALL_NO = "0";

    /**
     * 项目是否有图案灯网关key
     */
    public static final String PROJECT_PATTERN_FLAG = "PROJECT_PATTERN_FLAG";

    /**
     * 项目是否有图案灯网关VALUE
     */
    public static final String PROJECT_PATTERN_YES = "1";

    /**
     * 项目是否有图案灯网关VALUE
     */
    public static final String PROJECT_PATTERN_NO = "0";

    /**
     * 道路灯具分组缓存key
     */
    public static final String LAMP_GROUP_KEY = "LAMP_GROUP_";

    /**
     * 道路灯具场景缓存key
     */
    public static final String LAMP_SCENE_KEY = "LAMP_SCENE_";

    /**
     * 道路灯具时间方案缓存key
     */
    public static final String LAMP_TIME_KEY = "LAMP_TIME_";

    //==================================================================================
    //                          IOT 模块
    //==================================================================================

    public static final String TAIL = "A1B2C3D4";

    public static final String IOT_ELECTRIC_HEAD = "B3C4D5E6";
    public static final String IOT_ELECTRIC_HEAD_KEY = IOT_ELECTRIC_HEAD + "_";
    public static final String IOT_ELECTRIC_UPDATE = "IOT_ELECTRIC_UPDATE_";

    public static final String IOT_PATTERN_HEAD = "B3C4D5E7";
    public static final String IOT_PATTERN_KEY = IOT_PATTERN_HEAD + "_";
    public static final String IOT_PATTERN_UPDATE = "IOT_JG10_UPDATE_";

    public static final String IOT_TREE_HEAD = "B3C4D5E8";
    public static final String IOT_TREE_KEY = IOT_TREE_HEAD + "_";
    public static final String IOT_TREE_UPDATE = "IOT_TREE_UPDATE_";

    public static final String IOT_WALL_HEAD = "B3C4D5E9";
    public static final String IOT_WALL_HEAD_KEY = IOT_WALL_HEAD + "_";
    public static final String IOT_WALL_UPDATE = "IOT_WALL_UPDATE_";

    /**
     * 数据有变更 web key
     */
    public static final String WEB_PREFIX = "W_";

    public static final String MOBILE_PREFIX = "M_";

    /**
     * 数据有变更 value
     */
    public static final String DATA_VALUE = "DATA_VALUE";

    /**
     * 数据有变更 失效时间
     */
    public static final Long DATA_CHANGE_EXPIRED = 50L;


    /**
     * 设备在线值
     */
    public static final String ONLINE_VALUE = "1";

    /**
     * 设备在线值缓存过期时间
     */
    public static final Long EXPIRED_VALUE = 5L;

    /**
     * 给设备发送消息超时时间
     */
    public static final Long SEND_EXPIRED_TIME = 10000L;


    /**
     * 符号 +  延后执行
     */
    public static final String SEPARATOR_JIA = "+";

    /**
     * 符号 -  提前执行
     */
    public static final String SEPARATOR_JIAN = "-";

    /**
     * 定时模式
     */
    public static final String TIMER_MODE = "22";

    /**
     * 经纬模式
     */
    public static final String LAL_MODE = "11";

    /**
     * 经纬开 定时关
     */
    public static final String LAL_TIME = "12";

    /**
     * 定时关 经纬开
     */
    public static final String TIME_LAL = "21";

    /**
     * 经纬模式 提前
     */
    public static final String ADVANCE = "00";

    /**
     * 经纬模式 延后
     */
    public static final String POSTPONED = "01";

    /**
     * 校验头长度 8
     */
    public static final int HEAD_LENGTH = 8;

    /**
     * 校验尾长度 8
     */
    public static final int TAIL_LENGTH = 8;

    /**
     * 校验位长度 2
     */
    public static final int CHECK_BIT_LENGTH = 2;

    /**
     * 响应状态长度 1
     */
    public static final int RESPONSE_STATUS_LENGTH = 1;

    /**
     * 指令长度 3
     */
    public static final int CMD_LENGTH = 3;

    /**
     * 数据长度长度 4
     */
    public static final int DATA_LENGTH = 4;

    /**
     * 数据长度进制 16
     */
    public static final int DATA_LENGTH_DECIMAL = 16;

    /**
     * 时间戳长度 14
     */
    public static final int DATE_LENGTH = 14;

    /**
     * ID 长度 4
     */
    public static final int ID_LENGTH = 4;

    /**
     * 16进制FF 字节转换 2
     */
    public static final int DATA_BIT = 2;

    // ----------------- 回路状态 ------------------ //

    /**
     * 回路状态手动闭合
     */
    public static final int LOOP_STATUS_CLOSE = 1;

    /**
     * 回路状态自动闭合
     */
    public static final int LOOP_STATUS_AUTO_CLOSE = 2;

    /**
     * 回路状态手动断开
     */
    public static final int LOOP_STATUS_OPEN = 3;

    /**
     * 回路状态手动断开
     */
    public static final int LOOP_STATUS_AUTO_OPEN = 4;

    /**
     * 回路状态异常
     */
    public static final int LOOP_STATUS_FAULT = 5;

    // ----------------- 响应状态 ------------------ //

    /**
     * 普通响应状态 0
     */
    public static final String RESPONSE_STATUS_CODE_NORMAL = "0";

    /**
     * 成功响应状态 4
     */
    public static final String RESPONSE_STATUS_CODE_SUCCESS = "4";

    /**
     * 失败响应状态 校验错误 6
     */
    public static final String RESPONSE_STATUS_CODE_CHECK_FAIL = "6";

    /**
     * 失败响应状态 执行失败 8
     */
    public static final String RESPONSE_STATUS_CODE_FAIL = "8";

    // ----------------- 开关命令 ------------------ //

    /**
     * 闭合
     */
    public static final String OPTION_CMD_CLOSE = "1";

    /**
     * 断开
     */
    public static final String OPTION_CMD_OVER = "0";

    // ----------------- SMS ------------------ //

    public static final String OK = "OK";
}
