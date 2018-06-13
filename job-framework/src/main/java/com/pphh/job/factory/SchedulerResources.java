package com.pphh.job.factory;

import com.pphh.job.job.JobDetail;
import com.pphh.job.jobstore.JobStore;
import com.pphh.job.thread.ThreadPool;
import com.pphh.job.trigger.Trigger;

/**
 * Contains all of the resources necessary to create a Scheduler
 * - Job Store
 * - ThreadPool
 * - etc
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class SchedulerResources {

    private ThreadPool threadPool;
    private JobStore jobStore;
    private JobDetail jobDetail;
    private Trigger trigger;

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        if (threadPool == null) {
            throw new IllegalArgumentException("ThreadPool cannot be null.");
        }

        this.threadPool = threadPool;
    }

    public JobStore getJobStore() {
        return jobStore;
    }

    public void setJobStore(JobStore jobStore) {
        if (jobStore == null) {
            throw new IllegalArgumentException("JobStore cannot be null.");
        }

        this.jobStore = jobStore;
    }
}
