package com.pphh.job.thread;

import com.pphh.job.executor.JobExecutionResult;

import java.util.concurrent.Callable;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface ThreadPool {

    void initialize();

    boolean runInThread(Callable<JobExecutionResult> callable);

    void shutdown(boolean waitForJobsToComplete);

}
