package com.owen.lamp_control_module_common.exception;

import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.bean.RespCode;
import org.springframework.util.StringUtils;

/**
 * <p>Title: YcException</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @version 1.0
 */
public class YcException extends RuntimeException {

    /**
     * 错误代码
     */
    private String errorCode;

    /**
     * 错误代码接口
     */
    private IRespCode respCode;


    public YcException() {
        super();
        errorCode = RespCode.FAIL.getCode();
        respCode = RespCode.FAIL;
    }


    /**
     * 添加构造方法
     *
     * @param respCode 错误代码枚举类型
     */
    public YcException(IRespCode respCode) {
        super(respCode.getMessage());
        this.errorCode = respCode.getCode();
        this.respCode = respCode;
    }

    /**
     * 构造方法
     *
     * @param respCode 错误代码
     * @param message  默认消息
     */
    public YcException(IRespCode respCode, String message) {
        super(!StringUtils.isEmpty(message) ? message : respCode.getMessage());
        this.respCode = respCode;
        this.errorCode = respCode.getCode();
    }


    public String getErrorCode() {
        if (respCode != null) {
            return respCode.getCode();
        }
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public IRespCode getRespCode() {
        return respCode;
    }

    public void setRespCode(IRespCode respCode) {
        this.respCode = respCode;
    }

    @Override
    public String toString() {
        StringBuilder messageBuilder = new StringBuilder(getClass().getName());
        messageBuilder.append(":");
        if (respCode == null) {
            messageBuilder.append(this.errorCode);
        } else {
            messageBuilder.append(respCode.getCode()).append(":").append(respCode.name());
        }
        String message = getLocalizedMessage();
        return !StringUtils.isEmpty(message) ? messageBuilder.append(":").append(message).toString() : messageBuilder
                .toString();
    }
}
