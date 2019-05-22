package com.owen.lamp_control_module_common.entity;

import com.owen.lamp_control_module_common.bean.RespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.utils.ClassUtil;
import com.owen.lamp_control_module_common.utils.Constants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Set;

import static com.owen.lamp_control_module_common.bean.Constants.*;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.*;

/**
 * <p>Title: MessageFrame</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/28 9:32
 */
@Data
@Slf4j
public class MessageFrame implements Serializable {

    /**
     * 校验头
     */
    private String head;

    /**
     * 相应状态
     * 0 普通
     * 4 成功
     * 8 失败
     */
    private String responseStatus;

    /**
     * 命令
     * 三位
     * 0 203 <- 命令
     */
    private String cmd;

    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 十六进制FF数据长度位
     */
    private String dataLengthFF;

    /**
     * 时间戳
     */
    private String timestamps;

    /**
     * 设备ID 十进制
     * 例如：id = "100"
     */
    private String id = "";

    /**
     * 数据
     */
    private String data = "";

    /**
     * 校验位
     */
    private String checkByte;

    /**
     * 结束符
     */
    private String tail;

    public MessageFrame() {

    }

    public MessageFrame(String message) {

        // 检测是否是一个完整消息
        message = checkHead(message);
        // 1. 检测 头
        String head = message.substring(0, HEAD_LENGTH);
        if (head != null) {
            this.head = head;
        }
        // 获得头名称 例如 D5C8D5C8 -> electric
        String headName = getHeadName(head);
        // 2. 检测 尾
        String tail = message.substring(message.length() - TAIL_LENGTH);
        if (TAIL.equals(tail)) {
            this.tail = tail;
        } else {
            if (log.isErrorEnabled()) {
                log.error("【解码错误】非法校验尾 -> {}", tail);
            }
            throw new YcException(RespCode.IOT_TAIL_ERROR);
        }
        // 3. 检测 校验位
        String checkByte = message.substring(
                message.length() - TAIL_LENGTH - CHECK_BIT_LENGTH,
                message.length() - TAIL_LENGTH);
        if (checkByte(message, checkByte)) {
            this.checkByte = checkByte;
        }
        String responseStatus = message.substring(
                HEAD_LENGTH,
                HEAD_LENGTH + RESPONSE_STATUS_LENGTH);
        if (checkResponseStatus(responseStatus)) {
            this.responseStatus = responseStatus;
        }
        // 4. 检测 合法指令
        String cmd = message.substring(
                HEAD_LENGTH + RESPONSE_STATUS_LENGTH,
                HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH);
        if (checkCmdByHead(headName, cmd)) {
            this.cmd = cmd;
        }
        // 5. 通过数据长度获得数据
        String dataLengthFF = message.substring(
                HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH,
                HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH + DATA_LENGTH);
        Integer dataLength = Integer.valueOf(dataLengthFF, DATA_LENGTH_DECIMAL);
        String data = message.substring(
                (message.length() - (TAIL_LENGTH + CHECK_BIT_LENGTH + (dataLength * DATA_BIT))),
                (message.length() - (TAIL_LENGTH + CHECK_BIT_LENGTH)));
        this.dataLengthFF = dataLengthFF;
        this.dataLength = data.length();
        this.data = data;
        // 6. 获得时间戳
        this.timestamps = message.substring(
                HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH + DATA_LENGTH,
                HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH + DATA_LENGTH + DATE_LENGTH
        );
        // 7. 获得 ID
        if ((message.length() - (HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH + DATA_LENGTH + DATE_LENGTH + CHECK_BIT_LENGTH + TAIL_LENGTH + data.length())) == ID_LENGTH) {
            this.id = String.valueOf(Integer.parseInt(message.substring(
                    HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH + DATA_LENGTH + DATE_LENGTH,
                    HEAD_LENGTH + RESPONSE_STATUS_LENGTH + CMD_LENGTH + DATA_LENGTH + DATE_LENGTH + ID_LENGTH
            ), DATA_LENGTH_DECIMAL));
        }
    }

    /**
     * 获得接收数据帧
     *
     * @param head           校验头
     * @param responseStatus 响应状态
     * @param cmd            发送命令
     */
    public MessageFrame(String head, String responseStatus, String cmd) {
        this.head = head;
        this.responseStatus = responseStatus;
        this.cmd = cmd;
        this.timestamps = timeHex();
        this.checkByte = checkHexSum(head, responseStatus, cmd, this.timestamps, "", data);
    }

    @Override
    public String toString() {
        StringBuilder messageFrame = new StringBuilder();
        messageFrame.append(head);
        messageFrame.append(responseStatus);
        messageFrame.append(cmd);
        messageFrame.append(decimalToHexFF(data.length() / 2));
        messageFrame.append(timestamps);
        if (id != null) {
            messageFrame.append(decimalToHexFF(id));
        }
        messageFrame.append(data);
        messageFrame.append(checkByte);
        messageFrame.append(TAIL);
        return messageFrame.toString().toUpperCase();
    }

    // ---------------------- 构建发送消息帧 --------------------------

    /**
     * 构建发送消息帧
     *
     * @param checkHead 校验头
     * @param cmd       发送命令 0F0
     * @return 结果 消息帧
     */
    public static String build(String checkHead, String cmd) {
        return build(checkHead, RESPONSE_STATUS_CODE_NORMAL, cmd, null);
    }

    /**
     * 构建发送消息帧
     *
     * @param checkHead 校验头
     * @param param     发送参数
     * @return 结果 消息帧
     */
    public static String build(String checkHead, BaseParam param) {
        return build(checkHead, RESPONSE_STATUS_CODE_NORMAL, param.getCmd(), null, param.toString());
    }

    /**
     * 获得发送数据帧 3位指令
     *
     * @param command 发送命令
     * @param data    数据
     * @return 结果 数据帧
     */
    public static String build(String checkHead, String command, Object data) {
        return build(checkHead, RESPONSE_STATUS_CODE_NORMAL, command, null, data.toString());
    }

    /**
     * 获得发送数据帧 3位指令
     *
     * @param command 发送命令
     * @param data    数据
     * @return 结果 数据帧
     */
    public static String build(String checkHead, String command, String data) {
        return build(checkHead, RESPONSE_STATUS_CODE_NORMAL, command, null, data);
    }

    /**
     * 构建消息帧
     *
     * @param checkHead      校验头
     * @param responseStatus 响应状态
     * @param cmd            发送命令
     * @param id             设备ID
     * @return 结果 消息帧
     */
    public static String build(String checkHead, String responseStatus, String cmd, String id) {
        return build(checkHead, responseStatus, cmd, id, "");
    }

    /**
     * 获得接收数据帧
     *
     * @param checkHead      校验头
     * @param responseStatus 响应状态
     * @param cmd            发送命令
     * @param id             设备ID
     * @param data           数据
     * @return 结果 数据帧
     */
    public static String build(String checkHead, String responseStatus, String cmd, String id, String data) {
        MessageFrame messageFrame = new MessageFrame();
        messageFrame.setHead(checkHead);
        messageFrame.setResponseStatus(responseStatus);
        messageFrame.setCmd(cmd);
        messageFrame.setId(id);
        messageFrame.setTimestamps(timeHex());
        messageFrame.setData(data);
        messageFrame.setCheckByte(checkHexSum(checkHead, responseStatus, cmd, messageFrame.getTimestamps(), id, data));
        return messageFrame.toString().toUpperCase();
    }

    // ---------------------- 检测消息帧 --------------------------

    /**
     * 获取头名称
     *
     * @param head 校验头
     * @return 结果 校验结果
     */
    private static String getHeadName(String head) {
        Class c = Constants.class;
        Field[] fields = c.getFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            if (fieldName.startsWith("IOT") && fieldName.endsWith("HEAD")) {
                try {
                    if (head.startsWith(String.valueOf(f.get("")))) {
                        return fieldName.
                                replace("IOT_", "").
                                replace("_HEAD", "").
                                toLowerCase();
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if (log.isErrorEnabled()) {
            log.error("【解码错误】非法校验头 -> {}", head);
        }
        throw new YcException(RespCode.IOT_HEAD_ERROR);
    }

    /**
     * 检测校验头是否合法
     * 并且返回校验处理后的消息内容
     *
     * @param message 校验头
     * @return 结果 校验结果
     */
    private static String checkHead(String message) {
        Class c = Constants.class;
        Field[] fields = c.getFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            if (fieldName.startsWith("IOT") && fieldName.endsWith("HEAD")) {
                try {
                    // 头对应的值
                    String value = String.valueOf(f.get(""));
                    int headIndex = message.indexOf(value);
                    if ((headIndex != -1)) {
                        return message.substring(headIndex);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if (log.isErrorEnabled()) {
            log.error("[解码错误] 非法校验头消息 -> {}", message);
        }
        throw new YcException(RespCode.IOT_HEAD_ERROR);
    }

    /**
     * 检测响应状态是否合法
     *
     * @param responseStatus 响应状态
     * @return 结果 检测结果
     */
    private static boolean checkResponseStatus(String responseStatus) {
        Class<Constants> clazz = Constants.class;
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (f.getName().startsWith("RESPONSE_STATUS_CODE_")) {
                try {
                    if (responseStatus.equals(f.get(new Constants()))) {
                        return true;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if (log.isErrorEnabled()) {
            log.error("【解码错误】非法响应状态码 -> {}", responseStatus);
        }
        throw new YcException(RespCode.IOT_RESPONSE_CODE_ERROR);
    }

    /**
     * 检测校验位
     *
     * @param message   检测消息
     * @param checkByte 校验位
     * @return 结果 校验结果
     */
    private static boolean checkByte(String message, String checkByte) {
        if (checkByte.equalsIgnoreCase(checkHexSum(message.substring(0, message.length() - TAIL_LENGTH - CHECK_BIT_LENGTH)))) {
            return true;
        }
        if (log.isErrorEnabled()) {
            log.error("【解码错误】非法校验位 -> {}", checkByte);
        }
        throw new YcException(RespCode.IOT_BIT_ERROR);
    }

    /**
     * 通过校验头检测指令是否合法
     *
     * @param headName 消息校验头名称
     * @param cmd      指令
     */
    private static boolean checkCmdByHead(String headName, String cmd) {
        Set<Class<?>> classSet = ClassUtil.getClassSet("com.advsun.iot.param." + headName);
        classSet.addAll(ClassUtil.getClassSet("com.advsun.iot.param.common"));
        for (Class<?> clazz : classSet) {
            if (clazz.getSimpleName().endsWith(cmd)) {
                return true;
            }
        }
        if (log.isErrorEnabled()) {
            log.error("【解码错误】非法指令 -> {}:{}", headName, cmd);
        }
        throw new YcException(RespCode.IOT_CMD_ERROR);
    }

    // ---------------------- 其他方法 --------------------------

    /**
     * 传入 1     2   3
     * 返回 01    02  04
     *
     * @param loopNumber 回路编号
     * @return 结果 bitmap 后的回路编号
     */
    public static String bitmapLoopNumber(Integer loopNumber) {
        return decimalToHex(String.valueOf((int) Math.pow(2, loopNumber - 1)));
    }

//    public static void main(String[] args) {
//        System.out.println(new MessageFrame("D5C8D5C802030000120C1D0D0F090600690913AB13AB"));
//    }
}
