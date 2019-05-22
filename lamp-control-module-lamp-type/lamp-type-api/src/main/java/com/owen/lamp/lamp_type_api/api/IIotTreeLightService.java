package com.owen.lamp.lamp_type_api.api;

import com.owen.lamp_control_module_common.api.IBaseDeviceService;
import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.common.ParamCommonCmd506;
import com.owen.lamp_control_module_common.param.tree.*;

/**
 * @description: 照树灯服务接口
 * @version: 1.0
 * @author: Owen
 * @date: 2019/4/28
 */
public interface IIotTreeLightService extends IBaseDeviceService {
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
    IRespCode sendTreeCmd506(String deviceId, ParamCommonCmd506 param) throws YcException;

    /**
     * 0600 设置节点信息
     *
     * @param id    设备id
     * @param param 0600 参数
     * @return 结果
     */
    IRespCode sendTreeCmd600(String id, ParamTreeCmd600 param);

    /**
     * 0601 设置系统信息
     *
     * @param id    设备id
     * @param param 0601 参数
     * @return 结果
     */
    IRespCode sendTreeCmd601(String id, ParamTreeCmd601 param);

    /**
     * 0602 实时控制灯具模式
     *
     * @param id    设备id
     * @param param 0602 参数
     * @return 结果
     */
    IRespCode sendTreeCmd602(String id, ParamTreeCmd602 param);

    /**
     * 0604 设置亮灯模式
     *
     * @param id    设备id
     * @param param 0600 参数
     * @return 结果
     */
    IRespCode sendTreeCmd604(String id, ParamTreeCmd604 param);

    /**
     * 0605 设置定时策略
     *
     * @param id    设备id
     * @param param 0600 参数
     * @return 结果
     */
    IRespCode sendTreeCmd605(String id, ParamTreeCmd605 param);

    /**
     * 0607 设置开机自动运行场景
     *
     * @param id    设备id
     * @param param 0607 参数
     * @return 结果
     */
    IRespCode sendTreeCmd607(String id, ParamTreeCmd607 param);

    /**
     * 0610 查询节点信息
     *
     * @param id 设备id
     * @return 结果
     */
    IRespCode sendTreeCmd610(String id);

    /**
     * 0611 查询系统信息
     *
     * @param id 设备id
     * @return 结果
     */
    IRespCode sendTreeCmd611(String id);

    /**
     * 0614 查询模式数据
     *
     * @param id 设备id
     * @return 结果
     */
    IRespCode sendTreeCmd614(String id);

    /**
     * 0615 查询定时策略
     *
     * @param id 设备id
     * @return 结果
     */
    IRespCode sendTreeCmd615(String id);

}
