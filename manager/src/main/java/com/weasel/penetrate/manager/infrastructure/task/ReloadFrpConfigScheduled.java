package com.weasel.penetrate.manager.infrastructure.task;

import com.weasel.penetrate.manager.infrastructure.Frp;
import com.weasel.penetrate.manager.infrastructure.helper.SpringBeanHolder;
import com.weasel.penetrate.manager.service.FrpConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by dylan on 17-3-19.
 */
public class ReloadFrpConfigScheduled {

    private final static Logger log = LoggerFactory.getLogger(ReloadFrpConfigScheduled.class);

    private final static ReloadFrpConfigQueue<ReloadFrpConfigQueue.ReloadFrpConfigTask> queue = new ReloadFrpConfigQueue<>();
    private final static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    static {
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    ReloadFrpConfigQueue.ReloadFrpConfigTask _task = queue.get();
                    if(null != _task){
                        log.info("重新加载FRP配置文件任务执行...");
                        FrpConfigService frpConfigService = SpringBeanHolder.getBean(FrpConfigService.class);
                        frpConfigService.reloadConfig(Frp.getHome());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5, TimeUnit.MINUTES);
    }
    public synchronized static void submitTask(ReloadFrpConfigQueue.ReloadFrpConfigTask task){
        queue.addIfEmpty(task);
    }
}
