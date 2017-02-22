package com.weasel.penetrate.manager.infrastructure.ini.section;

import com.google.common.collect.Lists;
import org.ini4j.Profile;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Dylan
 * @date 2017/1/18.
 */
public class ListSection {

    private List<Profile.Section> sections = Lists.newArrayList();


    public ListSection(List<Profile.Section> sections) {
        Assert.notEmpty(sections,"sections can not be null and empty");
        this.sections = sections;
    }

    public <T> List<T> to(Class<T> clazz){

        List<T> objects = Lists.newArrayList();
        sections.forEach(section -> {
            try {
                T object = clazz.newInstance();
                section.to(object);
                objects.add(object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return objects;
    }
}
