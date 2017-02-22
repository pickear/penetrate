package com.weasel.penetrate.manager.infrastructure.repository.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.repository.DeviceRepository;
import com.weasel.penetrate.manager.infrastructure.repository.MybatisDaoSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Repository
public class DeviceRepositoryImpl extends MybatisDaoSupport implements DeviceRepository {

    private final static Cache<String,Device> devicesCache = CacheBuilder.newBuilder().build();

    static {
        Device ssh = Device.ssh();
        ssh.setAuthToken("123");
        ssh.setBindAddr("0.0.0.0");
        ssh.setListenPort("9000");
        ssh.setUsername("dylan1");
        ssh.setNumber("01");

        Device dns = Device.dns();
        dns.setAuthToken("123");
        dns.setBindAddr("0.0.0.0");
        dns.setListenPort("9000");
        dns.setUsername("dylan");
        dns.setNumber("02");

        Device http = Device.http();
        http.setAuthToken("123");
        http.setBindAddr("0.0.0.0");
        http.setListenPort("9000");
        http.setUsername("dylan");
        http.setNumber("03");

        Device https = Device.https();
        https.setAuthToken("123");
        https.setBindAddr("0.0.0.0");
        https.setListenPort("9000");
        https.setUsername("dylan");
        https.setNumber("04");

        devicesCache.put("ssh", ssh);
        devicesCache.put("dns", dns);
        devicesCache.put("http", http);
        devicesCache.put("https", https);
    }


    @Override
    public Device add(Device device) {
        return null;
    }

    @Override
    public int update(Device device) {
        return 0;
    }

    @Override
    public List<Device> query(Device device) {
        Collection<Device> devices = devicesCache.asMap().values();
        List<Device> _devices = devices.stream()
                .filter(_device -> null == device.getDeviceType() || StringUtils.equals(device.getDeviceType().getValue(), _device.getDeviceType().getValue()))
                .filter(_device -> StringUtils.isEmpty(device.getUsername()) || StringUtils.equals(device.getUsername(), _device.getUsername()))
                .collect(Collectors.toList());
        return _devices;
    }

    @Override
    public int delete(Device device) {
        return 0;
    }

    @Override
    protected String namespace() {
        return Device.class.getName();
    }
}
