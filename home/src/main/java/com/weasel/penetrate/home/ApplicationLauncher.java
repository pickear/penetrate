package com.weasel.penetrate.home;

import com.weasel.penetrate.common.helper.SystemHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

/**
 * 启动方式:java -jar penetrate-home.jar -Dspring.profiles.active=prod|dev|test
 */
@SpringBootApplication
public class ApplicationLauncher {

	private final static Logger log = LoggerFactory.getLogger(ApplicationLauncher.class);

	private final static String DEFAULT_ENV = "dev";

	public static void main(String[] args) {
		String runtimeEnv = System.getProperty("spring.profiles.active");
		if(StringUtils.isEmpty(runtimeEnv)){
			log.warn("找不到环境参数设置，使用默认环境[{}]，如果需要设置环境参数，在启动脚本加上如:[{}]",DEFAULT_ENV,"-Dspring.profiles.active=test");
			runtimeEnv = DEFAULT_ENV;
		}

		System.setProperty("spring.profiles.active",runtimeEnv);
		log.info("当前运行环境是[{}]",runtimeEnv);
		log.info("当前操作系统[{}]", SystemHelper.getOSname());

		SpringApplication.run(ApplicationLauncher.class, args);
	}
}
