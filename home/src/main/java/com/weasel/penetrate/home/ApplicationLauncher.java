package com.weasel.penetrate.home;

import com.weasel.penetrate.home.listener.ApplicationPreparedListener;
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

	private final static Logger logger = LoggerFactory.getLogger(ApplicationLauncher.class);

	private final static String DEFAULT_ENV = "dev";

	public static void main(String[] args) {
		/*HomeBanner homeBanner = new HomeBanner();
		homeBanner.printBanner(" :: FRP Home :: ",homeBanner.getVersion());*/
		String runtimeEnv = System.getProperty("spring.profiles.active");
		if(StringUtils.isEmpty(runtimeEnv)){
			logger.warn("找不到环境参数设置，使用默认环境[{}]，如果需要设置环境参数，在启动脚本加上如:[{}]",DEFAULT_ENV,"-Dspring.profiles.active=test");
			System.setProperty("spring.profiles.active",DEFAULT_ENV);
		}
		SpringApplication application = new SpringApplication(ApplicationLauncher.class);
		application.addListeners(new ApplicationPreparedListener());
	//	application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}
