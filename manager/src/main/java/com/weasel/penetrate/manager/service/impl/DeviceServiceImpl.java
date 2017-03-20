package com.weasel.penetrate.manager.service.impl;

import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortBindedException;
import com.weasel.penetrate.manager.infrastructure.exception.DeviceSubDomainUsedException;
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
            return device;
        }
        if(portBinded(device)){
            throw new DevicePortBindedException("端口["+device.getListenPort()+"]已被占用!");
        }
        if(subDomainUsed(device)){
            throw new DeviceSubDomainUsedException("子域["+device.getCustomDomains()+"]已被使用");
        }
        device = repository.add(device);
        return device;
    }

    private boolean portBinded(Device device){
        return repository.countByPort(device.getListenPort()) > 0;
    }

    private boolean subDomainUsed(Device device){
        return repository.countBySubDomain(device.getCustomDomains()) > 0;
    }
}
