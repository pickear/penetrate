package com.weasel.penetrate.manager.domain;


import com.weasel.penetrate.manager.domain.device.Device;

import java.util.Set;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
public class IniConfig {

    /**
     * 通用配置
     */
    private Common common;

    /**
     * ssh配置
     */
    private Set<Device> devices;


    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
