package com.weasel.penetrate.manager.infrastructure.repository;


import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortUsedUpException;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
public interface DeviceRepository {

    /**
     * 添加设备
     * @param device
     * @return
     */
    Device add(Device device);

    /**
     * 更新设备
     * @param device
     * @return
     */
    int update(Device device);

    /**
     * 查询设备
     * @param device
     * @return
     */
    List<Device> query(Device device);

    /**
     * 删除设备
     * @param device
     * @return
     */
    int delete(Device device);

    /**
     *
     * @param listenPort
     * @return
     */
    int countByPort(String listenPort);

    /**
     * @param subdomain
     * @return
     */
    int countBySubDomain(String subdomain);

    /**
     *
     * @return
     */
    int getMaxDistributedPort() throws DevicePortUsedUpException;

    /**
     *
     * @param name
     * @return
     */
    int deleteByUsername(String name);
}
