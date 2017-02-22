package com.weasel.penetrate.manager.infrastructure.repository;


import com.weasel.penetrate.manager.domain.Page;
import com.weasel.penetrate.manager.domain.User;

/**
 * @author Dylan
 * @date 2017/2/6.
 */
public interface UserRepository {

    /**
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     *
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     *
     * @param page
     * @return
     */
    Page<User> queryPage(Page page);
}
