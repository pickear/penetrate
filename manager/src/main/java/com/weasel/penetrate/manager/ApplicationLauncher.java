package com.weasel.penetrate.manager;

import com.weasel.penetrate.manager.infrastructure.listener.ApplicationStartOverListener;
import com.weasel.penetrate.manager.infrastructure.listener.DeviceCreateListener;
import com.weasel.penetrate.manager.infrastructure.listener.DeviceUpdateListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dylan
 * @date 2016/7/20.
 */
@SpringBootApplication
public class ApplicationLauncher {

    private final static Logger log = LoggerFactory.getLogger(ApplicationLauncher.class);

    private final static String DEFAULT_ENV = "dev";

    public static void main(String[] args) {

        String runtimeEnv = System.getProperty("spring.profiles.active");
        if(StringUtils.isBlank(runtimeEnv)){
            log.warn("找不到环境参数设置，使用默认环境[{}]，如果需要设置环境参数，在启动脚本加上如:[{}]",DEFAULT_ENV,"-Dspring.profiles.active=test");
            System.setProperty("spring.profiles.active",DEFAULT_ENV);
        }
        SpringApplication application = new SpringApplication(ApplicationLauncher.class);
        //application.setBannerMode(Banner.Mode.OFF);
        application.addListeners(new ApplicationStartOverListener(),new DeviceCreateListener(),new DeviceUpdateListener());
        application.run(args);
    }

}
