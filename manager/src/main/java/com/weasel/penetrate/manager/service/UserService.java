package com.weasel.penetrate.manager.service;


import com.weasel.penetrate.manager.domain.Page;
import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.infrastructure.exception.UserExistException;

/**
 * @author Dylan
 * @date 2017/2/6.
 */
public interface UserService {

    /**
     *
     * @param user
     * @return
     */
    int save(User user);

    /**
     *
     * @param user
     * @return
     * @throws UserExistException
     */
    boolean notExist(User user)throws UserExistException;

    /**
     *
     * @param page
     * @return
     */
    Page<User> queryPage(Page<User> page);
}
