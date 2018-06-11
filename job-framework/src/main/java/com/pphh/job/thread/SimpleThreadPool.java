package com.pphh.job.thread;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class SimpleThreadPool implements ThreadPool  {

    @Override
    public void initialize() {

    }

    @Override
    public boolean runInThread(Runnable runnable) {
        return false;
    }

    @Override
    public void shutdown(boolean waitForJobsToComplete) {

    }

}
