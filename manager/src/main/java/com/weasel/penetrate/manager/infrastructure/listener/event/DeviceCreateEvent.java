package com.weasel.penetrate.manager.infrastructure.listener.event;

import com.weasel.penetrate.manager.domain.device.Device;

/**
 * Created by dell on 2017/3/29.
 */
public class DeviceCreateEvent extends AbstractCommonEvent<Device> {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DeviceCreateEvent(Device source) {
        super(source);
    }
}
