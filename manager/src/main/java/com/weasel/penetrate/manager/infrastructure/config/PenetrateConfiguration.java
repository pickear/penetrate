package com.weasel.penetrate.manager.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by picke on 2017/5/14.
 */
@Configuration
@ConfigurationProperties(prefix = "penetrate")
public class PenetrateConfiguration {

    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
