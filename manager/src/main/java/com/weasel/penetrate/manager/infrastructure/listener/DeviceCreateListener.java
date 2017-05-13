package com.weasel.penetrate.manager.infrastructure.listener;

import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.listener.event.DeviceCreateEvent;
import com.weasel.penetrate.manager.infrastructure.task.ReloadFrpConfigQueue;
import com.weasel.penetrate.manager.infrastructure.task.ReloadFrpConfigScheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * Created by dell on 2017/3/29.
 */
public class DeviceCreateListener implements ApplicationListener<DeviceCreateEvent> {

    private final static Logger logger = LoggerFactory.getLogger(DeviceCreateListener.class);

    @Override
    public void onApplicationEvent(DeviceCreateEvent event) {

        Device device = event.getMessage();
        logger.info("device [{}] create event happend.",device.getNumber());
        ReloadFrpConfigScheduled.submitTask(new ReloadFrpConfigQueue.ReloadFrpConfigTask());
    }
}
