package com.pphh.job.scheduler;

import com.pphh.job.exception.SchedulerException;
import com.pphh.job.factory.SchedulerResources;
import com.pphh.job.job.JobDetail;
import com.pphh.job.jobstore.JobStore;
import com.pphh.job.thread.ThreadPool;
import com.pphh.job.trigger.MutableTrigger;
import com.pphh.job.trigger.Trigger;

import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class StdScheduler implements Scheduler {

    private SchedulerResources schedResources;
    private SchedulerThread schedThread;

    public StdScheduler(SchedulerResources resources, Long idleWaitTime) {
        this.schedResources = resources;
        this.schedThread = new SchedulerThread(this, resources);
        this.schedThread.setIdleWaitTime(idleWaitTime);
        this.schedThread.start();
    }

    @Override
    public Date scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        this.schedResources.setJobDetail(jobDetail);
        this.schedResources.setTrigger(trigger);
        JobStore jobStore = this.schedResources.getJobStore();
        if (jobStore != null) {
            jobStore.storeJobAndTrigger(jobDetail, trigger);
        }

        MutableTrigger trig = (MutableTrigger) trigger;
        Date firstTriggerTime = trig.computeFirstFireTime();
        if (firstTriggerTime == null) {
            throw new SchedulerException("Based on configured schedule, the given trigger '" + trigger.getKey() + "' will never fire.");
        }
        return firstTriggerTime;
    }

    @Override
    public void start() {
        // scheduler thread start
        // jobStore.onStart
        schedThread.togglePause(false);
    }

    public void standby() {
        schedThread.togglePause(true);
    }

    @Override
    public void shutdown() {
        this.shutdown(false);
    }

    @Override
    public void shutdown(boolean waitForJobsToComplete) {
        // scheduler thread shutdown
        // jobStore.onShutdown

        schedThread.halt(waitForJobsToComplete);
        ThreadPool threadPool = schedResources.getThreadPool();
        threadPool.shutdown(waitForJobsToComplete);
    }
}
