package com.weasel.penetrate.manager.infrastructure.exception;

/**
 * Created by dell on 2017/3/20.
 */
public class DevicePortBindedException extends Exception {

    public DevicePortBindedException() {
        super();
    }

    public DevicePortBindedException(String s) {
        super(s);
    }

    public DevicePortBindedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
