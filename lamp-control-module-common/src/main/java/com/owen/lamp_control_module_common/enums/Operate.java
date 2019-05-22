package com.owen.lamp_control_module_common.enums;

/**
 * <p>Title: Operate</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:33
 */
public enum Operate {

    /**
     * 开
     */
    OPEN("FF"),

    /**
     * 关
     */
    CLOSE("00");

    private String operate;

    Operate(String operate) {
        this.operate = operate;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}
