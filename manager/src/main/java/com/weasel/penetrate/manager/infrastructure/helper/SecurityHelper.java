package com.weasel.penetrate.manager.infrastructure.helper;

import com.weasel.penetrate.manager.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by dell on 2017/2/24.
 */
public final class SecurityHelper {

    public static User getCurrentUser(){

        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            return (User)currentUser.getPrincipal();
        }

        return null;
    }

    private SecurityHelper(){}
}
