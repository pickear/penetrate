package com.weasel.penetrate.manager.infrastructure.formatter.factory;

import com.google.common.collect.Sets;
import com.weasel.penetrate.manager.infrastructure.annotation.JQGridId;
import com.weasel.penetrate.manager.infrastructure.formatter.JQGridIdFormatter;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Set;

/**
 * Created by dell on 2017/3/14.
 */
public class JQGridIdFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<JQGridId> {

    private final Set<Class<?>> fieldTypes = Sets.newHashSet(Long.class);
    private final JQGridIdFormatter formatter = new JQGridIdFormatter();

    @Override
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    @Override
    public Printer<Long> getPrinter(JQGridId jqGridId, Class<?> aClass) {
        return formatter;
    }

    @Override
    public Parser<Long> getParser(JQGridId jqGridId, Class<?> aClass) {
        return formatter;
    }
}
