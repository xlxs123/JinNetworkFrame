package com.jin.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
    private static ThreadManager threadManager = new ThreadManager();
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();
    private ThreadPoolExecutor threadPoolExecutor;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = mQueue.take();
                    threadPoolExecutor.execute(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static ThreadManager getInstance() {
        return threadManager;
    }

    public void addTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        mQueue.add(runnable);
    }

    private ThreadManager() {
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        mQueue.add(r);
                    }
                });
        threadPoolExecutor.execute(runnable);
    }
}
