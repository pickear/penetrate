package com.weasel.penetrate.manager.infrastructure.exception;

/**
 * Created by dell on 2017/3/20.
 */
public class DevicePortUsedUpException extends Exception {

    public DevicePortUsedUpException() {
        super();
    }

    public DevicePortUsedUpException(String s) {
        super(s);
    }

    public DevicePortUsedUpException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
