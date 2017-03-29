package com.weasel.penetrate.manager.infrastructure;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2017/3/29.
 */
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public static void publishEvent(ApplicationEvent event){
        applicationEventPublisher.publishEvent(event);
    }

    public static void publishEvent(Object event){
        applicationEventPublisher.publishEvent(event);
    }
}
