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
     * @param id
     * @return
     */
    User getUserById(long id);

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

    /**
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     *
     * @param id
     * @return
     */
    int delete(long id);
}
