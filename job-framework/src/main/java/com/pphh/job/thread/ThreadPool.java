package com.pphh.job.thread;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface ThreadPool {

    void initialize();

    boolean runInThread(Runnable runnable);

    void shutdown(boolean waitForJobsToComplete);
}
