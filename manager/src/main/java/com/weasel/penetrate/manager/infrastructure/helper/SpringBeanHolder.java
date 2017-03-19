package com.weasel.penetrate.manager.infrastructure.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by dylan on 17-3-19.
 */
public class SpringBeanHolder implements ApplicationContextAware {

    private static ApplicationContext context;
    private final static Logger log = LoggerFactory.getLogger(SpringBeanHolder.class);

    @Override
    public void setApplicationContext(ApplicationContext _context)
            throws BeansException {
        context = _context;
    }

    public static ApplicationContext getContext() {
        if (null == context)
            throw new RuntimeException(
                    "please register the SpringBeanHolder bean to spring...");
        return context;
    }

    /**
     *
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return getContext().getBean(clazz);
        } catch (BeansException e) {
            log.warn("can not found bean " + clazz.getName());
        }
        return null;
    }

    /**
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        try {
            return getContext().getBean(beanName);
        } catch (BeansException e) {
            log.warn("can not found bean " + beanName);
        }
        return null;
    }
}
