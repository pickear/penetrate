package com.weasel.penetrate.manager.service;


import com.weasel.penetrate.manager.domain.device.Device;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
public interface DeviceService {


     List<Device> query(Device device);
}
