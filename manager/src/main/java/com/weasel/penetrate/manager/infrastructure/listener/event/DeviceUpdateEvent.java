package com.weasel.penetrate.manager.infrastructure.listener.event;

import com.weasel.penetrate.manager.domain.device.Device;

/**
 * Created by dell on 2017/3/29.
 */
public class DeviceUpdateEvent extends AbstractCommonEvent<Device> {

    public DeviceUpdateEvent(Object source, Device message) {
        super(source, message);
    }
}
