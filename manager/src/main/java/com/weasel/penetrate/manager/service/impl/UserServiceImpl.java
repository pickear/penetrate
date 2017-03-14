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
    public int save(User user) {

        Preconditions.checkNotNull(user,"user can not be null");
        if(null != user.getId() && -1 != user.getId()){
            if(logger.isDebugEnabled()){
                logger.debug("执行更新用户操作......");
            }
            return repository.update(user);
        }
        if(logger.isDebugEnabled()){
            logger.debug("执行添加用户操作......");
        }
        return repository.insert(user);
    }

    @Override
    public boolean notExist(User user) throws UserExistException {

        User u = repository.getUserByName(user.getName());
        if(null != u){
            throw new UserExistException("用户["+user.getName()+"]已存在");
        }

        u = repository.getUserByEmail(user.getEmail());

        if(null != u){
            throw new UserExistException("邮箱["+user.getEmail()+"]已存在");
        }

        return true;
    }

    @Override
    public Page<User> queryPage(Page<User> page) {
        return repository.queryPage(page);
    }
}
