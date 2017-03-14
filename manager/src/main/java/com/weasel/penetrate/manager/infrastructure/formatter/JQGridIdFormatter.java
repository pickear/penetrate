package com.weasel.penetrate.manager.infrastructure.formatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by dell on 2017/3/14.
 */
public class JQGridIdFormatter implements Formatter<Long>,Serializable {

    @Override
    public Long parse(String value, Locale locale) throws ParseException {
        if(StringUtils.isBlank(value) || StringUtils.equals(value,"_empty")){
            return new Long(-1);
        }
        return Long.valueOf(value);
    }

    @Override
    public String print(Long value, Locale locale) {
        if(value == null) {
            return "";
        }
        return value.toString();
    }
}
