package com.weasel.penetrate.manager.infrastructure.listener;

import com.weasel.penetrate.manager.infrastructure.Frp;
import com.weasel.penetrate.manager.service.FrpConfigService;
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
