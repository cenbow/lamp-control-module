package com.owen.lamp_control_module_common.bean;

/**
 * <p>Title: IRespCode</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 10:13
 */
public interface IRespCode {

    /**
     * 获取错误编码
     *
     * @return 结果 错误编码
     */
    String getCode();

    /**
     * 获取异常名称
     *
     * @return 结果 异常名称
     */
    String name();

    /**
     * 获取异常消息
     *
     * @return 结果 异常消息
     */
    String getMessage();
}
