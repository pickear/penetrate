package com.weasel.penetrate.home.listener;

import com.weasel.penetrate.common.helper.SystemHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**应用启动完监听器
 * Created by dylan on 17-3-12.
 */
public class ApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {

    private final Logger logger = LoggerFactory.getLogger(ApplicationPreparedListener.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        String runtimeEnv = System.getProperty("spring.profiles.active");
        logger.info("当前运行环境是[{}]",runtimeEnv);
        logger.info("当前操作系统[{}]", SystemHelper.getOSname());
    }
}
