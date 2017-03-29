package com.weasel.penetrate.manager.infrastructure.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by dell on 2017/3/29.
 */
public abstract class AbstractCommonEvent<T> extends ApplicationEvent {

    private T message;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AbstractCommonEvent(Object source,T message) {
        super(source);
        this.message = message;
    }

    public T getMessage() {
        return message;
    }
}
