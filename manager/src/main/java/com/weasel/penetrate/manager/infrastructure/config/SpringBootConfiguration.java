package com.weasel.penetrate.manager.infrastructure.config;

import com.weasel.penetrate.manager.infrastructure.formatter.factory.JQGridIdFormatAnnotationFormatterFactory;
import com.weasel.penetrate.manager.infrastructure.helper.SpringBeanHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by dell on 2017/3/14.
 */
@Configuration
public class SpringBootConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new JQGridIdFormatAnnotationFormatterFactory());
    }

    @Bean
    public SpringBeanHolder beanHolder(){
        return new SpringBeanHolder();
    }

}
