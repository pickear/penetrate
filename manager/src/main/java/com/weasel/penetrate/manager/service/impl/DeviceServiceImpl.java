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

        if(portBinded(device)){
            throw new DevicePortBindedException("端口["+device.getListenPort()+"]已被占用!");
        }
        if(subDomainUsed(device)){
            throw new DeviceSubDomainUsedException("子域["+device.getSubdomain()+"]已被使用");
        }
        if (device.getId() != -1){
            repository.update(device);
            return device;
        }
        device = repository.add(device);
        return device;
    }

    private boolean portBinded(Device device){
        return repository.countByPort(device.getListenPort()) > 0;
    }

    private boolean subDomainUsed(Device device){
        return repository.countBySubDomain(device.getSubdomain()) > 0;
    }
}
