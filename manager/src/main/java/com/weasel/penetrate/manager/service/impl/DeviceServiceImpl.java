package com.weasel.penetrate.manager.service.impl;

import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.EventPublisher;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortBindedException;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortUsedUpException;
import com.weasel.penetrate.manager.infrastructure.exception.DeviceSubDomainUsedException;
import com.weasel.penetrate.manager.infrastructure.listener.event.DeviceCreateEvent;
import com.weasel.penetrate.manager.infrastructure.listener.event.DeviceUpdateEvent;
import com.weasel.penetrate.manager.infrastructure.repository.DeviceRepository;
import com.weasel.penetrate.manager.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private static int maxDistributedPort = 0;

    @Autowired
    private DeviceRepository repository;

    @Override
    public List<Device> query(Device device) {

        return repository.query(device);
    }

    @Override
    public Device save(Device device) throws DevicePortBindedException, DeviceSubDomainUsedException {

        if (device.getId() != -1){
            repository.update(device);
            EventPublisher.publishEvent(new DeviceUpdateEvent(this,device));
            return device;
        }
        if(portBinded(device)){
            throw new DevicePortBindedException("端口["+device.getListenPort()+"]已被占用!");
        }
        if(subDomainUsed(device)){
            throw new DeviceSubDomainUsedException("子域["+device.getCustomDomains()+"]已被使用");
        }
        device = repository.add(device);
        EventPublisher.publishEvent(new DeviceCreateEvent(this,device));
        return device;
    }

    @Override
    public int distributePort() throws DevicePortUsedUpException {
        synchronized (this){
            if(maxDistributedPort == 0){
                maxDistributedPort = getMaxDistributedPort();
            }
            return ++maxDistributedPort;
        }
    }

    private int getMaxDistributedPort() throws DevicePortUsedUpException {
        return repository.getMaxDistributedPort();
    }

    private boolean portBinded(Device device){
        return repository.countByPort(device.getListenPort()) > 0;
    }

    private boolean subDomainUsed(Device device){
        return repository.countBySubDomain(device.getCustomDomains()) > 0;
    }
}
