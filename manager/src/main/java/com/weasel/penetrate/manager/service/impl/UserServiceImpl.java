package com.weasel.penetrate.manager.service.impl;

import com.google.common.base.Preconditions;
import com.weasel.penetrate.manager.domain.Page;
import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.infrastructure.exception.UserExistException;
import com.weasel.penetrate.manager.infrastructure.repository.UserRepository;
import com.weasel.penetrate.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dylan
 * @date 2017/2/6.
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {

        Preconditions.checkNotNull(user,"user can not be null");
        return user.save();
    }

    @Override
    public boolean notExist(User user) throws UserExistException {
        return user.notExist();
    }

    @Override
    public Page<User> queryPage(Page<User> page) {
        return repository.queryPage(page);
    }
}
