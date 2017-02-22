package com.weasel.penetrate.manager.infrastructure.ini.section;

import org.ini4j.Profile;
import org.springframework.util.Assert;

/**
 * @author Dylan
 * @date 2017/1/18.
 */
public class OneSection {

    private Profile.Section section = null;

    public OneSection(Profile.Section section) {
        Assert.notNull(section,"section can not be null");
        this.section = section;
    }

    public <T> T to(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T object = clazz.newInstance();
        section.to(clazz);
        return object;
    }
}
