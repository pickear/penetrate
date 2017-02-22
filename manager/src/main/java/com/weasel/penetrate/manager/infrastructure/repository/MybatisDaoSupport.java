package com.weasel.penetrate.manager.infrastructure.repository;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dypan on 2017/2/18.
 */
public abstract class MybatisDaoSupport extends SqlSessionDaoSupport {



    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    protected abstract String namespace();
}
