package com.weasel.penetrate.manager.infrastructure.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by dylan on 17-3-19.
 */
public class ReloadFrpConfigQueue<T> {

    private final static Logger logger = LoggerFactory.getLogger(ReloadFrpConfigQueue.class);

    private final LinkedBlockingQueue<T> queue = new LinkedBlockingQueue();

    public synchronized void addIfEmpty(T obj){
        if(null == queue.peek()){
            logger.info("更新配置文件任务为空,往队列添加任务。");
            queue.offer(obj);
        }
    }

    public T get() throws InterruptedException {
        return queue.poll();
    }

    public static class ReloadFrpConfigTask{

    }
}
