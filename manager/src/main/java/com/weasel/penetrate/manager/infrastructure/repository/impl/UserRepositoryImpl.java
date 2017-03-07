package com.weasel.penetrate.manager.infrastructure.repository.impl;

import com.weasel.penetrate.manager.domain.Page;
import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.infrastructure.repository.MybatisDaoSupport;
import com.weasel.penetrate.manager.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/2/6.
 */
@Repository
public class UserRepositoryImpl extends MybatisDaoSupport implements UserRepository {


    @Override
    public User getUserByName(String name) {
        return getSqlSession().selectOne(namespace().concat(".getUserByName"),name);
    }

    @Override
    public User getUserByEmail(String email) {
        return getSqlSession().selectOne(namespace().concat(".getUserByEmail"),email);
    }

    @Override
    public Page<User> queryPage(Page page) {

        List<User> users = getSqlSession().selectList(namespace().concat(".queryPage"),page);
        page.setResult(users);
        return page;
    }

    @Override
    public int update(User user) {
        return getSqlSession().update(namespace().concat(".update"),user);
    }

    @Override
    public int insert(User user) {
        return getSqlSession().insert(namespace().concat(".insert"),user);
    }

    @Override
    protected String namespace() {
        return User.class.getName();
    }
}
