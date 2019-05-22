package com.owen.lamp.lamp_type_api.api;

import com.owen.lamp_control_module_common.api.IBaseDeviceService;
import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.common.ParamCommonCmd506;
import com.owen.lamp_control_module_common.param.common.ParamCommonCmd508;
import com.owen.lamp_control_module_common.param.pattern.*;

/**
 * @description: 图案灯指令下发接口
 * <p>
 * 0x0501: 创建终端所带的灯具参数
 * 0x0502: 实时控制灯具模式
 * 0x0503: 设置终端带的节点ID
 * 0x0504: 设置终端场景
 * 0x0505: 设置终端定时策略
 * 0x0506: 设置开机自动运行场景
 * 0x0507: 设置打开、关闭定时策略
 * 0x0510: 查询终端所带的灯具参数
 * 功能描述：JG10 指令服务
 * </p>
 * <p>
 * 01. 0600 设置节点信息
 * 02. 0601 设置系统信息
 * 03. 0602 实时控制灯具模式
 * 04. 0604 设置亮灯模式
 * 05. 0605 设置定时策略
 * 06. 0607 设置开机自动运行场景
 * 07. 0610 查询节点信息
 * 08. 0611 查询系统信息
 * 09. 0614 查询模式数据
 * 10. 0615 查询定时策略
 * </p>
 * @version: 1.0
 * @author: Owen
 * @date: 2019/4/28
 */
public interface IIotPatternLigthService extends IBaseDeviceService {

    /**
     * 功能描述: 设置启用和禁用定时策略
     * 命令: 0x0506
     * 数据内容:
     * - 使能数据（1字节）
     * - 定时策略编号（1字节）
     * <p>
     * 备注: 用来切换手动和自动模式，收到服务器实时控制灯具模式，启动禁用定时策略
     *
     * @param deviceId 设备ID
     * @param param    数据信息
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd506(String deviceId, ParamCommonCmd506 param) throws YcException;

    /**
     * 功能描述: 创建终端所带的灯具参数
     * 命令数据: 0x0501
     * 数据内容:
     * - 节点数（1字节）
     * - 节点ID（根据节点数确定字节数）
     * - 节点带的灯具数量（根据节点数确定字节数）
     * - 灯具通道数（1字节）
     * 备注:
     * - 一个节点ID 后跟一个节点带的灯数
     * - 节点数范围:1-170 01-AA
     * - 节点带灯具数范围:1-170 01-AA
     * - 灯具通道数范围:1-255 01-FF
     * 示例:
     * 2个节点,节点ID分别为 01 02,节点01带了3个灯,节点02带了2个灯,灯具通道数 5
     * D5 C6 D5 C6（头码）
     * 0501（指令）
     * 00 06 （数据长度6）
     * 12 04 12 0E 09 10 03（时间戳）
     * 02 01 03 02 02 05（数据）
     * XX （累加低八位教验）
     * 13AB13AB（尾码）
     *
     * @param deviceId 设备ID 设备ID
     * @param param    参数实体
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd501(String deviceId, ParamPatternCmd501 param) throws YcException;

    /**
     * 功能描述: 设置终端场景
     * 命令数据: 0x0504
     * 数据内容:
     * - 场景编号（1字节）
     * - 场景数据（字节数=所有节点灯具数的和*通道数） 范围：1-16
     * 示例:设置5个灯具参数，场景2
     * D5 C6 D5 C6（头码）
     * 05 04（命令）
     * 00 1A（数据长度26）
     * 12 04 12 0E 09 10 03（时间戳）
     * 02 ff 01 00 00 00 ff 00 01 00 00 ff 01 00 00 00 ff 01 00 00 00 ff 00 01 00 00  （数据）
     * XX（累加低八位校验）
     * 13 AB 13 AB(尾码)
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd504(String deviceId, ParamPatternCmd504 param) throws YcException;

    /**
     * 功能描述: 设置终端定时策略
     * 命令: 0x0505
     * 数据内容:
     * - 定时策略编号（1字节）
     * - 模式编号（1字节）
     * - 开始时间（2字节）
     * - 结束时间（2字节）
     * - 时间间隔（2字节）
     * - 需要变化的数据个数（1字节）
     * - 数据位（1字节）
     * - 数据（根据数据个数确定字节数）
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd505(String deviceId, ParamPatternCmd505 param) throws YcException;

    /**
     * 功能描述: 实时控制灯具模式
     * 命令: 0x0502
     * 数据内容:
     * - DMX512数据（字节数=所有节点灯具数的和*通道数）
     * <p>
     * 备注: 数据包长度 < 1024
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd502(String deviceId, ParamPatternCmd502 param) throws YcException;

    /**
     * 功能描述: 设置开机自动运行场景
     * 命令:0x0507
     * 数据内容:
     * - 使能数据（1字节）
     * - 场景编号（1字节）
     * 备注: 使能数据=ff，开使能，使能数据=00，关使能；
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd507(String deviceId, ParamPatternCmd507 param) throws YcException;

    /**
     * 功能描述: 设置终端带的节点ID
     * 命令:0x0503
     * 数据内容: 旧节点ID（1字节） 新节点ID（1字节）
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd503(String deviceId, ParamPatternCmd503 param) throws YcException;

    /**
     * 功能描述: 实时开关灯
     * 命令:0x0508
     * 数据格式:
     * - 开关灯数据（1字节）
     * 备注: 开灯模式默认是开机自动运行的模式，开关灯数据=FF,开灯，开关灯数据=00，关灯
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd508(String deviceId, ParamCommonCmd508 param) throws YcException;

    /**
     * 功能描述: 查询终端所带的灯具参数
     * 命令: 0x0510
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd510(String deviceId) throws YcException;

    /**
     * 功能描述: 查询终端场景数据
     * 命令: 0x0511
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd511(String deviceId) throws YcException;

    /**
     * 功能描述: 查询存储定时策略
     * 命令: 0x0511
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendPatternCmd512(String deviceId) throws YcException;
}
