package com.owen.lamp_control_module_common.param.pattern;

import com.owen.lamp_control_module_common.bean.Constants;
import com.owen.lamp_control_module_common.param.base.BaseParam;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;

/**
 * <p>Title: ParamPatternCmd501</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamPatternCmd501 extends BaseParam {

    public static final String CMD = getCmd(ParamPatternCmd501.class.getSimpleName());

    /**
     * 节点ID和所带灯具数的关联由'&'隔开 十六进制字节表示
     * 例子 01,03&02,02
     */
    @ApiParam("节点ID和所带灯具数的关联")
    private String nodeIdAndLampNum;

    /**
     * 灯具通道数
     */
    @ApiParam("灯具通道数")
    private int lampLoopNum;

    /**
     * 构造实际下发指令
     *
     * @return 结果 设备接收到的 data 数据内容
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] nodeIdAndLamps = nodeIdAndLampNum.split(Constants.AND);
        // 获得节点数
        stringBuilder.append(decimalToHex(nodeIdAndLamps.length));
        for (String nodeIdAndLamp : nodeIdAndLamps) {
            String[] idAndLamp = nodeIdAndLamp.split(Constants.SEPARATOR);
            // 节点ID
            stringBuilder.append(decimalToHex(idAndLamp[0]));
            // 节点所带灯具数量
            stringBuilder.append(decimalToHex(idAndLamp[1]));
        }
        stringBuilder.append(decimalToHex(lampLoopNum));
        return stringBuilder.toString().toUpperCase();
    }


    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }
}
