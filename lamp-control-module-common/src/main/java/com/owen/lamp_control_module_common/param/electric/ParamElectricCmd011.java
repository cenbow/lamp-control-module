package com.owen.lamp_control_module_common.param.electric;

import com.owen.lamp_control_module_common.param.base.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: ParamElectricCmd011</p>
 * <p>Description: 服务器下发传感器数据数值</p>
 * 数据：传感器数值（5个字节） 第一字节传感类型，后面4字节传感器数值（不足4字节补0）
 * 备注：传感类型：1代表光照度 暂时不做/2017年10月17日
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 19:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamElectricCmd011 extends BaseParam {

    public static final String CMD = getCmd(ParamElectricCmd011.class.getSimpleName());

    @Override
    public void setCmd() {
        super.cmd = CMD;
    }
}
