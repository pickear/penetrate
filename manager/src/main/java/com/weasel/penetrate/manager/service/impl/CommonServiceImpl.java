package com.weasel.penetrate.manager.service.impl;

import com.weasel.penetrate.manager.domain.Common;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.repository.CommonRepository;
import com.weasel.penetrate.manager.infrastructure.repository.DeviceRepository;
import com.weasel.penetrate.manager.service.CommonService;
import com.weasel.penetrate.manager.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonRepository repository;

    @Override
    public Common get() {
        return repository.get();
    }
}
