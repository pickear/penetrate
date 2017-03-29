package com.weasel.penetrate.manager.infrastructure.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by dell on 2017/3/29.
 */
public abstract class AbstractCommonEvent<T> extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AbstractCommonEvent(T source) {
        super(source);
        this.source = source;
    }

    @Override
    public T getSource() {
        return (T)super.getSource();
    }
}
