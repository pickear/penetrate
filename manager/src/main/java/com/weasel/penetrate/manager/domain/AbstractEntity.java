package com.weasel.penetrate.manager.domain;

import com.weasel.penetrate.manager.infrastructure.helper.SpringBeanHolder;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author dylan
 * @time 2017/6/7
 */
public abstract class AbstractEntity<T>  implements Serializable {

    protected transient Class<T> repoClass;

    public AbstractEntity() {
        Type type = getClass().getGenericSuperclass();
        repoClass = (Class<T>)((ParameterizedType)type).getActualTypeArguments()[0];
    }

    protected  T repo(){
        return SpringBeanHolder.getBean(repoClass);
    }
}
