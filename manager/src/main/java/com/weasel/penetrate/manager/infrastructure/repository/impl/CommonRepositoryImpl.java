package com.weasel.penetrate.manager.infrastructure.repository.impl;

import com.google.common.collect.Lists;
import com.weasel.penetrate.manager.domain.Common;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.repository.CommonRepository;
import com.weasel.penetrate.manager.infrastructure.repository.DeviceRepository;
import com.weasel.penetrate.manager.infrastructure.repository.MybatisDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Repository
public class CommonRepositoryImpl extends MybatisDaoSupport implements CommonRepository {

    @Override
    public Common get() {
        return getSqlSession().selectOne(namespace().concat(".get"));
    }

    @Override
    protected String namespace() {
        return Common.class.getName();
    }
}
