package com.weasel.penetrate.manager.infrastructure.listener;

import com.weasel.penetrate.common.helper.SystemHelper;
import com.weasel.penetrate.manager.infrastructure.Frp;
import com.weasel.penetrate.manager.service.FrpConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;

/**应用启动完监听器
 * Created by dylan on 17-3-12.
 */
public class ApplicationStartOverListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(ApplicationStartOverListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        String runtimeEnv = System.getProperty("spring.profiles.active");
        logger.info("当前运行环境是[{}]",runtimeEnv);
        String frpHome = System.getProperty("frp.home");
        if(StringUtils.isEmpty(frpHome)){
            throw new RuntimeException("请在启动时传入frp.home参数，例如:java -jar frp-manager.jar -Dfrp.home=/usr/local/softwares/frp_0.9.3_windows_amd64");
        }
        logger.info("当前frp目录是:" + frpHome);
        Frp.setHome(frpHome);
        logger.info("当前操作系统[{}]", SystemHelper.getOSname());

        //防止重复执行。
        if(event.getApplicationContext().getParent() == null){
            FrpConfigService frpConfigService = event.getApplicationContext().getBean(FrpConfigService.class);
            try {
                frpConfigService.reloadConfig(Frp.getHome());
            } catch (IOException e) {
                logger.error(e.getMessage());
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }

        }
    }
}
