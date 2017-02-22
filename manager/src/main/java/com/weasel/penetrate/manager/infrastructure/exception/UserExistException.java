package com.weasel.penetrate.manager.infrastructure.exception;

/**
 * @author Dylan
 * @date 2017/2/6.
 */
public class UserExistException extends Exception {

    public UserExistException(String s) {
        super(s);
    }

    public UserExistException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
