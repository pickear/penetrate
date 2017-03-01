package com.weasel.penetrate.home;

import com.weasel.penetrate.common.helper.SystemHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动方式:java -jar penetrate-home.jar -Dspring.profiles.active=prod|dev|test
 */
@SpringBootApplication
public class ApplicationLauncher {

	private final static Logger log = LoggerFactory.getLogger(ApplicationLauncher.class);

	private final static String DEFAULT_ENV = "dev";

	public static void main(String[] args) {
		String runtimeEnv = System.getProperty("runtime.env",DEFAULT_ENV);
		System.setProperty("runtime.env",runtimeEnv);
		log.info("当前运行环境是[{}]",runtimeEnv);
		log.info("当前操作系统[{}]", SystemHelper.getOSname());

		SpringApplication.run(ApplicationLauncher.class, args);
	}
}
