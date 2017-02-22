package com.weasel.penetrate.manager;

import com.weasel.penetrate.common.helper.SystemHelper;
import com.weasel.penetrate.manager.infrastructure.Frp;
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

        String runtimeEnv = System.getProperty("runtime.env",DEFAULT_ENV);
        System.setProperty("runtime.env",runtimeEnv);
        log.info("当前运行环境是[{}]",runtimeEnv);

        String frpHome = System.getProperty("frp.home");

        if(StringUtils.isEmpty(frpHome)){
            throw new RuntimeException("请在启动时传入frp.home参数，例如:java -jar frp-manager.jar -Dfrp.home=/usr/local/softwares/frp_0.9.3_windows_amd64");
        }

        log.info("当前frp目录是:" + frpHome);

        Frp.setHome(frpHome);

        log.info("当前操作系统[{}]", SystemHelper.getOSname());

        SpringApplication.run(ApplicationLauncher.class,args);
    }
}
