package com.owen.lamp.lamp_type_api.api;

import com.owen.lamp_control_module_common.api.IBaseDeviceService;
import com.owen.lamp_control_module_common.bean.IRespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.electric.*;
import com.owen.lamp_control_module_common.param.electric.join.ParamElectricCmd0B0_1_2;

import java.util.Map;

/**
 * @description: 电力设备服务接口
 * <p>
 * 读取网关状态	0x0001
 * 服务器下发光照度值	0x0010
 * 服务器下发传感器数据数值	0x0011
 * 读取光照度	0x0020
 * 读1#~50#灯控器状态、电流、亮度、功率因数	0x0030
 * 读51#~100#灯状态、电流、亮度、功率因数	0x0031
 * 读101#~150#灯状态、电流、亮度、功率因数	0x0032
 * 读151#~200#灯状态、电流、亮度、功率因数	0x0033
 * 读201#~255#灯状态、电流、亮度、功率因数	0x0034
 * 读指定灯状态和电流、电压、功率、功率因素、电量、温度、亮度	0x0040
 * 读取数字量(此项功能扩展用)	0x0050
 * 读取回路的单灯检测	0x0051
 * 设置回路策略	0x0060
 * 设置灯策略	0x0070
 * 设置指定灯控器策略	0x0071
 * 设置回路策略-手动	0x0080
 * 设置灯策略-手动	0x0090
 * 设置MAC地址第（1#~100#灯）	0x00b0
 * 设置MAC地址第（101#~200#灯）	0x00b1
 * 设置MAC地址第（201#~300#灯）	0x00b2
 * 设置MAC地址第（301#~400#灯）	0x00b3
 * 读取MAC地址设置状态	0x00bf
 * 读取电表数据	0x00c0
 * 设置经纬度	0x00f0 经度（4字节） 维度（4字节） 注:前两字节代表整数位，后两字节代表小数位
 * 设置集中器远程重启	0x00f1
 * 设置回路的单灯检测	0x00f2
 * 设置节假日回路策略	0x0160
 * 设置节假日灯策略	0x0170
 * 读取电缆防盗	0x0180
 * 下发路由表	0x0190
 * 修改单灯地址标志	0x0200
 * 设置电缆防盗	0x0201
 * 配置传感器参数(该集中器下传感器配置全局信息)	0x01a0
 * 远程升级起始	0x0300
 * 远程升级BIN文件	0x0301
 * 0x0302
 * 传感器数据透传	0x0401
 * </p>
 * @version: 1.0
 * @author: Owen
 * @date: 2019/4/28
 */
public interface IIotElectricDeviceService extends IBaseDeviceService {

    /**
     * 单个回路开关设置	0x0080
     *
     * @param deviceId         设备ID         设备ID
     * @param loopSwitchOption 回路策略组 key = 回路 value = 开关
     * @return 结果 设置手动回路开关
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd080(String deviceId, Map<String, String> loopSwitchOption) throws YcException;

    /**
     * 单个回路开关设置	0x0080
     *
     * @param deviceId 设备ID 设备ID
     * @param param    回路策略组 key = 回路 value = 开关
     * @return 结果 设置手动回路开关
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd080(String deviceId, ParamElectricCmd080 param) throws YcException;

    /**
     * 设置经纬度	0f0
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0F0(String deviceId, ParamElectricCmd0F0 param) throws YcException;

    /**
     * 固件升级接口
     * <p>
     * 0030 4030
     * 0031 1024 D5C8D5C80031
     * 0032
     *
     * @param deviceId 设备ID 设备id
     * @param fileName 文件名称
     * @return 结果 发送结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd300(String deviceId, String fileName) throws YcException;

    /**
     * 更新固件指令
     *
     * @param deviceId 设备ID 设备ID号
     * @param fileName 文件名称
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd301(String deviceId, String fileName) throws YcException;

    /**
     * 更新固件指令
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd302(String deviceId) throws YcException;

    /**
     * 登录|网关->服务器|集中器MAC地址
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd001(String deviceId) throws YcException;

    /**
     * 心跳机制(GPRS链路测试)|0x0203	|
     * 网关->服务器	|
     * 信号强度1字节：0-31 为GPRS正常信号强度，99为检测不到信号，0xFF为以太网连接，0xAA备用	"每隔一段时间心跳一下（时间暂时定为1分钟）"|
     * 总1字节
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd203(String deviceId) throws YcException;

    /**
     * 主动上报灯状态和电流、亮度、功率因素(1#灯控器~50#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|每个灯控器有2个灯通道，每个通道代表1个灯，
     * 包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；亮度1个字节；功率因数1字节；
     * 电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd230(String deviceId) throws YcException;

    /**
     * 主动上报灯状态和电流、亮度、功率因素(51#灯控器~100#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd231(String deviceId) throws YcException;

    /**
     * 主动上报灯状态和电流、亮度、功率因素(101#灯控器~150#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd232(String deviceId) throws YcException;

    /**
     * 主动上报灯状态和电流、亮度、功率因素(201#灯控器~250#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd234(String deviceId) throws YcException;

    /**
     * 主动上报灯电量	0x023F	网关->服务器	1#灯控器1通道灯电量，1#灯控器2通道电量；
     * ....N#灯控器2通道灯电量	"灯每个通道电量2个字节；总共数据4N个字节（N表示灯数）默认都是双通道灯控器，单灯第二通道占位"	每天0点自动上传
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd23F(String deviceId) throws YcException;

    /**
     * 主动上报灯状态和电流、亮度、功率因素(151#灯控器~200#灯控器)|1-50#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)；
     * ----- N#灯状态、电流(2字节)、亮度、功率因数、电压(2字节)、功率(2字节)|
     * 每个灯控器有2个灯通道，每个通道代表1个灯，包括灯状态1个字节（1-手动开；2-手动关；3-自动开；4-自动关；5-灯故障；电流2字节（单位0.1A）；
     * 亮度1个字节；功率因数1字节；电压2字节，功率2字节 总共数据2*9*50个字节 数据为0xFFFFFFFFFFFFFFFFFF为灯不存在|灯状态或亮度变化的时候主动上传；
     * 每隔一定时间（暂定半小时）主动上传；1≤N≤50
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd233(String deviceId) throws YcException;

    /**
     * 主动上报数字量及回路状态
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd250(String deviceId) throws YcException;

    /**
     * 主动上报集中器端内部电表数据 0x02c0
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd2C0(String deviceId) throws YcException;

    /**
     * 主动上报集中器端外部电表数据 0x02c1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd2C1(String deviceId) throws YcException;

    /**
     * 上报重启/上电和GPRS重启	0x02F0
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd2F0(String deviceId) throws YcException;

    /**
     * 上报网关失电报警	0x02F1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd2F1(String deviceId) throws YcException;

    /**
     * 对时	0x0002
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd002(String deviceId) throws YcException;

    /**
     * 服务器下发光照度值	0x0010
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd010(String deviceId, ParamElectricCmd010 param) throws YcException;

    /**
     * 服务器下发传感器数据数值	0x0011
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd011(String deviceId) throws YcException;

    /**
     * 读取光照度	0x0020
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd020(String deviceId, ParamElectricCmd020 param) throws YcException;

    /**
     * 读1#~50#灯控器状态、电流、亮度、功率因数	0x0030
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd030_1_2_3_4(String deviceId) throws YcException;

    /**
     * 读1#~50#灯控器状态、电流、亮度、功率因数	0x0030
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd030(String deviceId) throws YcException;

    /**
     * 读51#~100#灯状态、电流、亮度、功率因数	0x0031
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd031(String deviceId) throws YcException;

    /**
     * 读101#~150#灯状态、电流、亮度、功率因数	0x0032
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd032(String deviceId) throws YcException;

    /**
     * 读151#~200#灯状态、电流、亮度、功率因数	0x0033
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd033(String deviceId) throws YcException;

    /**
     * 读201#~255#灯状态、电流、亮度、功率因数	0x0034
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd034(String deviceId) throws YcException;

    /**
     * 读指定灯状态和电流、电压、功率、功率因素、电量、温度、亮度	0x0040
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd040(String deviceId, ParamElectricCmd040 param) throws YcException;

    /**
     * 读取数字量(此项功能扩展用)	0x0050
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd050(String deviceId) throws YcException;

    /**
     * 读取回路的单灯检测	0x0051
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd051(String deviceId) throws YcException;

    /**
     * 读取网关参数	0x0052
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd052(String deviceId) throws YcException;

    /**
     * 设置回路策略	0x0060
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd060(String deviceId, ParamElectricCmd060 param) throws YcException;

    /**
     * 设置灯策略	0x0070
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd070(String deviceId, ParamElectricCmd070 param) throws YcException;

    /**
     * 设置指定灯控器策略	0x0071
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd071(String deviceId, ParamElectricCmd071 param) throws YcException;

    /**
     * 设置灯策略-手动	0x0090
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd090(String deviceId, ParamElectricCmd090 param) throws YcException;

    /**
     * 设置MAC地址第（1#~100#灯）	0x00b0
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0B0(String deviceId, ParamElectricCmd0B0 param) throws YcException;

    /**
     * 设置MAC地址第（101#~200#灯）	0x00b1
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0B1(String deviceId, ParamElectricCmd0B1 param) throws YcException;

    /**
     * 设置MAC地址第（201#~255#灯）	0x00b2
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0B2(String deviceId, ParamElectricCmd0B2 param) throws YcException;

    /**
     * 设置MAC地址第（1#~255#灯）
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0B0_1_2(String deviceId, ParamElectricCmd0B0_1_2 param) throws YcException;

    /**
     * 读取内部电表数据	0x00c0
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0C0(String deviceId) throws YcException;

    /**
     * 读取外接电表数据	0x00c1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0C1(String deviceId) throws YcException;

    /**
     * 设置集中器远程重启	0x00f1
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0F1(String deviceId) throws YcException;

    /**
     * 设置回路的单灯检测	0x00f2
     *
     * @param deviceId 设备ID
     * @param param
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd0F2(String deviceId, ParamElectricCmd0F2 param) throws YcException;

    /**
     * 远程升级起始	0x0300
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd300(String deviceId) throws YcException;

    /**
     * 远程升级BIN文件	0x0301
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd301(String deviceId) throws YcException;

    /**
     * 配置电力采集模块参数	0x0800
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd800(String deviceId) throws YcException;

    /**
     * 查询电力采集模块三相进线实时数据	0x0810
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd810(String deviceId) throws YcException;

    /**
     * 查询回路支路三相出线实时数据	0x0811
     *
     * @param deviceId 设备ID
     * @return 结果
     * @throws YcException 自定义异常
     */
    IRespCode sendElectricCmd811(String deviceId) throws YcException;
}
