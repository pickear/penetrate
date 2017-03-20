package com.weasel.penetrate.manager.service;


import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortBindedException;
import com.weasel.penetrate.manager.infrastructure.exception.DeviceSubDomainUsedException;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
public interface DeviceService {


    /**
     *
     * @param device
     * @return
     */
     List<Device> query(Device device);

    /**
     *
     * @param device
     * @return
     */
    Device save(Device device) throws DevicePortBindedException, DeviceSubDomainUsedException;
}
