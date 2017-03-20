package com.weasel.penetrate.manager.infrastructure.exception;

/**
 * Created by dell on 2017/3/20.
 */
public class DeviceSubDomainUsedException extends Exception {

    public DeviceSubDomainUsedException() {
        super();
    }

    public DeviceSubDomainUsedException(String s) {
        super(s);
    }

    public DeviceSubDomainUsedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
