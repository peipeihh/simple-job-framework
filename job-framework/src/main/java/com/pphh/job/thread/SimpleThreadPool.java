package com.pphh.job.thread;

import com.pphh.job.executor.JobExecutionResult;
import com.pphh.job.util.LogUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class SimpleThreadPool implements ThreadPool {

    private static long DEFAULT_TIMEOUT = 60L * 1000L;
    private static SimpleThreadPool ourInstance = new SimpleThreadPool();
    ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
    ThreadPoolTaskExecutor futureThreadPool = new ThreadPoolTaskExecutor();
    private long timeout = DEFAULT_TIMEOUT;

    private SimpleThreadPool() {
    }

    public static SimpleThreadPool getInstance() {
        ourInstance.initialize();
        return ourInstance;
    }

    @Override
    public void initialize() {
        initializedThreadPool(threadPool);
        initializedThreadPool(futureThreadPool);
    }

    private void initializedThreadPool(ThreadPoolTaskExecutor threadPool) {
        threadPool.setCorePoolSize(10);
        threadPool.setMaxPoolSize(45);
        threadPool.setKeepAliveSeconds(200);
        threadPool.setQueueCapacity(100);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPool.initialize();
    }

    @Override
    public boolean runInThread(Callable<JobExecutionResult> callable) {
        Future<JobExecutionResult> future = threadPool.submit(callable);

        futureThreadPool.submit(() -> {
            try {
                future.get(this.timeout, TimeUnit.SECONDS);
                //LogUtil.print("a job task is finished successfully");
            } catch (Exception e) {
                LogUtil.print("an exception happened when execute a job task");
            }
        });

        return false;
    }

    @Override
    public void shutdown(boolean waitForJobsToComplete) {
        threadPool.shutdown();
        futureThreadPool.shutdown();
    }

}
