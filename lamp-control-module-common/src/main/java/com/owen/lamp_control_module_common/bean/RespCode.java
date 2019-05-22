package com.owen.lamp_control_module_common.bean;

/**
 * @description: com.owen.lamp_control_module_common.bean
 * @version: 1.0
 * @author: Owen
 * @date: 2019/4/28
 */
public enum RespCode implements IRespCode {

    /****** Global *****/
    SUCCESS("200", "SUCCESS."),
    FAIL("201", "FAIL."),
    PARAM_ERROR("202", "参数错误"),
    SQL_ERROR("205", "数据库SQL异常"),
    UNKNOWN_ERROR("206", "未知错误"),
    PERMISSION_ERROR("207", "权限不足"),

    /****** 用户中心 20001 -- 20200 ********/
    USERC_PASSWORD_ERROR("20001", "登录密码错误"),
    USERC_OLDPWD_NEWPWD_SAME("20003", "新旧密码一样"),
    USERC_ACCOUNT_EXIST("20004", "用户已存在"),
    USERC_ACCOUNT_NOT_EXIST("20005", "用户不存在"),
    USERC_CODE_ERROR("20006", "验证码错误"),
    USERC_CODE_EXPIRE("20008", "验证码过期"),
    USERC_LOGIN_EXPIRE("20009", "会话失效，请重新登陆"),

    ORGAN_EDIT_ID_NULL("20010", "编辑组织机构时，组织机构ID为空"),
    ORGAN_DEL_OBJ_NULL("20011", "删除组织机构时，组织机构不存在"),

    PROJECT_EDIT_ID_NULL("20012", "编辑项目时，项目ID为空"),
    PROJECT_DEL_OBJ_NULL("20013", "删除项目时，项目不存在"),

    USERC_ADMIN_ORGANID_ERROR("20014", "添加业主用户时组织ID参数为空"),
    USERC_EDIT_ID_ERROR("20015", "编辑用户参数ID为空"),

    /****** 设备中心 20201 -- 20500 ********/
    DEVICE_EDIT_ID_NULL("20201", "编辑设备，项目ID为空"),
    DEVICE_OFF_LNGLAT("20202", "设备离线，设置经纬失败"),
    DEVICE_NOT_EXIST("20203", "设备不存在"),
    PATTERN_CONTROL_NOT_EXIST("20204", "图案灯控制器不存在"),
    PATTERN_MODEL_NOT_EXIST("20205", "图案灯控制器模式不存在"),
    WALL_GATEWAY_NOT_EXIST("20206", "洗墙灯网关不存在"),
    LAMP_NOT_EXIST("20207", "灯具不存在"),

    TREE_GATA_ID_EXIST("20208", "照树灯ID已存在"),

    /****** 策略管理 20501 -- 20700 ********/
    DEVICE_OFF_LINE("20501", "设备离线，发送指令失败!"),
    STRATEGY_EDIT_ID_NULL("20502", "编辑策略模式时ID为空"),
    STRATEGY_EDIT_STATUS_ERROR("20503", "编辑策略模式状态异常"),
    TIMEPLAN_EDIT_ID_NULL("20505", "编辑时间方案时ID为空"),
    PROJECT_NO_STRATEGY("20506", "当前场景模式未下发，请先下发场景模式"),
    STRATEGY_DATA_NULL("20507", "场景下发策略的同步数据为空，请先下发场景模式"),
    DAILY_STRATEGY_NULL("20508", "没有上一次下发的日常策略"),
    HOLIDAY_STRATEGY_ERROR("20509", "添加节假日模式失败，开始时间节假日模式已存在"),
    PROJECT_STRATEGY_ERROR("20510", "项目当前没有下发策略"),
    SEND_STRATEGY_ERROR("20511", "节假日策略下发时间不正确，下发不了"),

    /****** 告警信息 20701 -- 20800 ********/
    ALARM_DATA_NULL("20701", "告警信息不存在"),


    /****** IOT 20801 -- 20999 ********/
    DECODE_ERROR("20801", ""),
    IOT_HEAD_ERROR("20802", "校验头错误"),
    IOT_TAIL_ERROR("20803", "校验尾错误"),
    IOT_BIT_ERROR("20804", "校验位错误"),
    IOT_CMD_ERROR("20805", "指令错误"),
    IOT_DATA_LENGTH_ERROR("20806", "数据长度错误"),
    IOT_RESPONSE_CODE_ERROR("20807", "响应码错误"),
    //结束
    END("00000", "");


    private String code;

    private String message;

    RespCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 错误码
     *
     * @return 结果
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 错误信息
     *
     * @return 结果
     */
    @Override
    public String getMessage() {
        return this.message;
    }


    @Override
    public String toString() {
        return "[" + code + "]" + message;
    }
}
