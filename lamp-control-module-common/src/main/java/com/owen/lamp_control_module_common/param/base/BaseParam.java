package com.owen.lamp_control_module_common.param.base;

/**
 * <p>Title: BaseParam</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 10:57
 */
public abstract class BaseParam {

    /**
     * 参数命令
     */
    protected String cmd;

    /**
     * 构造方法
     */
    public BaseParam() {
        // 给cmd赋值
        setCmd();
    }

    /**
     * 获取参数命令
     *
     * @return 参数命令
     */
    public String getCmd() {
        return this.cmd;
    }

    /**
     * 给参数赋值
     */
    protected abstract void setCmd();

    public static String getCmd(String className) {
        return className.substring(className.length() - 3);
    }
}
