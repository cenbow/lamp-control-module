package com.owen.lamp_control_module_common.enums;

/**
 * @description: com.owen.lamp_control_module_common.enums
 * @version: 1.0
 * @author: Owen
 * @date: 2019/4/28
 */
public enum LightStatusEnum {

    /**
     * 手动开
     */
    MANUALLY_OPEN(1),

    /**
     * 手动关
     */
    MANUALLY_CLOSE(2),

    /**
     * 自动开
     */
    AUTO_OPEN(3),

    /**
     * 自动关
     */
    AUTO_CLOSE(4),

    /**
     * 故障
     */
    FAILURE(5),

    /**
     * 错误
     */
    ERROR(0);

    private Integer code;

    LightStatusEnum(int code) {
        this.code = code;
    }
}
