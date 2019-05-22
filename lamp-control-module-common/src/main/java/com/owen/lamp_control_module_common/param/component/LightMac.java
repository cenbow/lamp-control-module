package com.owen.lamp_control_module_common.param.component;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
/**
 * 功能描述：服务器->网关
 * 1#灯MAC,
 * 2#灯MAC …
 * 100#灯MAC地址（7字节,
 * 包括6字节的mac地址和
 * 1字节的通道数量(
 * 0X01表示启用通道1，
 * 0x02表示启用通道2，
 * 0x03表示启用两通道)）
 * 需要对每个灯进行设置。
 *
 * @author weilai
 * @version 1.0.0 2018/9/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LightMac implements Serializable {

    public static final int CHANNEL_1 = 1;
    public static final int CHANNEL_2 = 2;
    public static final int CHANNEL_BOTH = 3;
    public static final int CHANNEL_DELETE = 255;

    /**
     * mac 地址 6字节
     */
    @ApiParam(value = "mac 地址")
    private String mac = "FFFFFFFFFFFF";

    /**
     * 通道数量
     * 0X01表示启用通道1，
     * 0x02表示启用通道2，
     * 0x03表示启用两通道
     * 0xFF不启用
     */
    @ApiParam(value = "通道数量")
    private int channelNum = CHANNEL_DELETE;

    @Override
    public String toString() {
        return (mac + decimalToHex(channelNum)).toUpperCase();
    }

}
