package com.owen.lamp.lamp_type_service.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.owen.lamp.lamp_type_api.api.IIotTreeLightService;
import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.common.ParamCommonCmd506;
import com.owen.lamp_control_module_common.param.tree.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.owen.lamp_control_module_common.bean.Constants.IOT_TREE_HEAD;
import static com.owen.lamp_control_module_common.entity.MessageFrame.build;

/**
 * <p>Title: IotTreeLightServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/8 10:25
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = IIotTreeLightService.class, timeout = 1000000, version = "${dubbo.service.version}")
public class IotTreeLightServiceImpl extends BaseService implements IIotTreeLightService {


    public IotTreeLightServiceImpl() {
        super.head = IOT_TREE_HEAD;
    }

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
    @Override
    public IRespCode sendTreeCmd506(String deviceId, ParamCommonCmd506 param) throws YcException {
        return send(deviceId, build(messageFrame.getHead(), ParamCommonCmd506.CMD, param));
    }

    /**
     * 0600 设置节点信息
     *
     * @param id    设备id
     * @param param 0600 参数
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd600(String id, ParamTreeCmd600 param) {
        return send(id, build(head, param));
    }

    /**
     * 0601 设置系统信息
     *
     * @param id    设备id
     * @param param 0601 参数
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd601(String id, ParamTreeCmd601 param) {
        return send(id, build(head, param));
    }

    /**
     * 0602 实时控制灯具模式
     *
     * @param id    设备id
     * @param param 0602 参数
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd602(String id, ParamTreeCmd602 param) {
        return send(id, build(head, param));
    }

    /**
     * 0604 设置亮灯模式
     *
     * @param id    设备id
     * @param param 0600 参数
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd604(String id, ParamTreeCmd604 param) {
        return send(id, build(head, param));
    }

    /**
     * 0605 设置定时策略
     *
     * @param id    设备id
     * @param param 0600 参数
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd605(String id, ParamTreeCmd605 param) {
        return send(id, build(head, param));
    }

    /**
     * 0607 设置开机自动运行场景
     *
     * @param id    设备id
     * @param param 0607 参数
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd607(String id, ParamTreeCmd607 param) {
        return send(id, build(head, param));
    }

    /**
     * 0610 查询节点信息
     *
     * @param id 设备id
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd610(String id) {
        return send(id, build(head, ParamTreeCmd610.CMD));
    }

    /**
     * 0611 查询系统信息
     *
     * @param id 设备id
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd611(String id) {
        return send(id, build(head, ParamTreeCmd611.CMD));
    }

    /**
     * 0614 查询模式数据
     *
     * @param id 设备id
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd614(String id) {
        return send(id, build(head, ParamTreeCmd614.CMD));
    }

    /**
     * 0615 查询定时策略
     *
     * @param id 设备id
     * @return 结果
     */
    @Override
    public IRespCode sendTreeCmd615(String id) {
        return send(id, build(head, ParamTreeCmd615.CMD));
    }

    /**
     * 查询设备是否已入库
     *
     * @param deviceId 设备ID 设备ID
     * @return 结果 查询结果
     */
    @Override
    protected boolean findById(String deviceId) {
        throw new NotImplementedException();
    }

    /**
     * 设置项目ID
     *
     * @return 项目ID
     */
    @Override
    protected String getProjectId() {
        throw new NotImplementedException();
    }

    /**
     * 更新设备状态
     */
    @Override
    protected void update() {
        throw new NotImplementedException();
    }

    public void process506() {
        process();
    }

    public void process600() {
        process();
    }

    public void process601() {
        process();
    }

    public void process602() {
        process();
    }

    public void process603() {
        process();
    }

    public void process604() {
        process();
    }

    public void process605() {
        process();
    }

    public void process606() {
        process();
    }

    public void process607() {
        process();
    }

    public void process608() {
        process();
    }

    public void process609() {
        process();
    }

    public void process610() {
        process();
    }

    public void process611() {
        process();
    }

    public void process612() {
        process();
    }

    public void process613() {
        process();
    }

    public void process614() {
        process();
    }

    public void process615() {
        process();
    }
}
