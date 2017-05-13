package com.weasel.penetrate.manager.infrastructure.repository.impl;

import com.google.common.collect.Lists;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.EventPublisher;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortUsedUpException;
import com.weasel.penetrate.manager.infrastructure.listener.event.DeviceCreateEvent;
import com.weasel.penetrate.manager.infrastructure.listener.event.DeviceUpdateEvent;
import com.weasel.penetrate.manager.infrastructure.repository.DeviceRepository;
import com.weasel.penetrate.manager.infrastructure.repository.MybatisDaoSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Repository
public class DeviceRepositoryImpl extends MybatisDaoSupport implements DeviceRepository {

    private final static int minPort = 8000;
    private final static int maxPort = 9999;

    @Override
    public Device add(Device device) {
        getSqlSession().insert(namespace().concat(".insert"),device);
        return device;
    }

    @Override
    public int update(Device device) {
        int result = delete(device);
        add(device);
        return result;
    }

    @Override
    public List<Device> query(Device device) {

        List<Device> devices = getSqlSession().selectList(namespace().concat(".query"),device);
        return null != device ? devices : Lists.newArrayList();
    }

    @Override
    public int delete(Device device) {
        return getSqlSession().delete(namespace().concat(".deleteById"),device.getId());
    }

    @Override
    public int countByPort(String listenPort) {
        if(StringUtils.isBlank(listenPort)){
            return 0;
        }
        return getSqlSession().selectOne(namespace().concat(".countByPort"),listenPort);
    }

    @Override
    public int countBySubDomain(String subdomain) {
        if(StringUtils.isBlank(subdomain)){
            return 0;
        }
        return getSqlSession().selectOne(namespace().concat(".countBySubDomain"),subdomain);
    }

    @Override
    public int getMaxDistributedPort() throws DevicePortUsedUpException {
        Integer port = getSqlSession().selectOne(namespace().concat(".getMaxDistributedPort"));
        if(null != port && port > maxPort){
            throw new DevicePortUsedUpException("["+minPort+"]-["+maxPort+"]之间的端口已被用完");
        }
        return (null != port &&port >=minPort) ? port : minPort;
    }

    @Override
    protected String namespace() {
        return Device.class.getName();
    }
}
