package com.weasel.penetrate.manager.service.impl;

import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.EventPublisher;
import com.weasel.penetrate.manager.infrastructure.config.PenetrateConfiguration;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortBindedException;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortUsedUpException;
import com.weasel.penetrate.manager.infrastructure.exception.DeviceSubDomainUsedException;
import com.weasel.penetrate.manager.infrastructure.helper.SecurityHelper;
import com.weasel.penetrate.manager.infrastructure.listener.event.DeviceCreateEvent;
import com.weasel.penetrate.manager.infrastructure.listener.event.DeviceUpdateEvent;
import com.weasel.penetrate.manager.infrastructure.repository.DeviceRepository;
import com.weasel.penetrate.manager.service.DeviceService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
    @Autowired
    private DeviceRepository repository;
    @Autowired
    private PenetrateConfiguration configuration;

    @Override
    public List<Device> query(Device device) {

        return repository.query(device);
    }

    @Override
    public Device save(Device device) throws DevicePortBindedException, DeviceSubDomainUsedException, DevicePortUsedUpException {

        if (device.getId() != -1){
            repository.update(device);
            EventPublisher.publishEvent(new DeviceUpdateEvent(this,device));
            return device;
        }
        if(portBinded(device)){
            throw new DevicePortBindedException("端口["+device.getListenPort()+"]已被占用!");
        }
       /* if(subDomainUsed(device)){
            throw new DeviceSubDomainUsedException("子域["+device.getCustomDomains()+"]已被使用");
        }*/

        User user = SecurityHelper.getCurrentUser();
        int port = distributePort();
        device.setListenPort(String.valueOf(port));
        String number = String.valueOf(RandomUtils.nextInt());
        device.setUsername(user.getName());
        device.setNumber(number);
        if(StringUtils.isBlank(device.getCustomDomains())
                &&(StringUtils.equalsIgnoreCase("http",device.getProtocolType().getValue())
                || StringUtils.equalsIgnoreCase("https",device.getProtocolType().getValue()))){

            device.setCustomDomains(device.getNumber() + "." + configuration.getDomain());
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

    @Override
    public int delete(long id) {

        User currentUser = SecurityHelper.getCurrentUser();
        if(null == currentUser){
            logger.warn("用户未登录，无法删除device");
            return 0;
        }
        Device device = new Device();
        device.setId(id);
        //如果当前用户是admin,那么不设置username,这时admin可以删除任意用户的device。
        if(!SecurityHelper.isAdmin()){
            logger.debug("用户是非管理员，只能删除自己的device");
            device.setUsername(currentUser.getName());
        }
        return repository.delete(device);
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
