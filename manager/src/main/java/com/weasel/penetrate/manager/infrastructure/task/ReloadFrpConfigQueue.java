package com.weasel.penetrate.manager.infrastructure.task;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by dylan on 17-3-19.
 */
public class ReloadFrpConfigQueue<T> {

    private final LinkedBlockingQueue<T> queue = new LinkedBlockingQueue();

    public synchronized void addIfEmpty(T obj){
        if(null == queue.peek()){
            queue.add(obj);
        }
    }

    public T get() throws InterruptedException {
        return queue.take();
    }

    public static class ReloadFrpConfigTask{

    }
}
