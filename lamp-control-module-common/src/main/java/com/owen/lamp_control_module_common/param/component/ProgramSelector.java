package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * 功能描述：节目片选器
 * <p>
 * 05
 * 02
 * 00 02(ID)
 * 01 02 03 04 05
 *
 * @author weilai
 * @version 1.0.0 2018/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramSelector implements Serializable {

    /**
     * 片选器ID
     */
    private int id;

    /**
     * 节目编号
     * 01
     */
    private Integer program;

    @Override
    public String toString() {
        return (decimalToHexFF(id) + decimalToHex(program)).toUpperCase();
    }
}
