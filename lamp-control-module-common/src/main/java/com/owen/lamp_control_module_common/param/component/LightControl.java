package com.owen.lamp_control_module_common.param.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 功能描述：灯控器
 *
 * @author weilai
 * @version 1.0.0 2018/9/13
 */
@Data
@Slf4j
@AllArgsConstructor
public class LightControl implements Serializable {

    private LightStatus[] lightStatuses = new LightStatus[2];

    public LightControl(){}

    public LightControl(LightStatus lightStatus0, LightStatus lightStatus1) {
        this.lightStatuses[0] = lightStatus0;
        this.lightStatuses[1] = lightStatus1;
    }

    @Override
    public String toString() {
        if (log.isDebugEnabled()) {
            log.debug("[0]:{}", this.lightStatuses[0]);
            log.debug("[1]:{}", this.lightStatuses[1]);
        }
        return (this.lightStatuses[0] + "" + this.lightStatuses[1]).toUpperCase();
    }

}
