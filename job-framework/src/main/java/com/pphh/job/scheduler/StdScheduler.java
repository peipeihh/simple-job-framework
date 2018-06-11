package com.pphh.job.scheduler;

import com.pphh.job.factory.SchedulerResources;
import com.pphh.job.job.JobDetail;
import com.pphh.job.trigger.Trigger;

import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class StdScheduler implements Scheduler {


    public StdScheduler(SchedulerResources resources, Long idleWaitTime){

    }

    @Override
    public Date scheduleJob(JobDetail jobDetail, Trigger trigger) {
        return null;
    }

    @Override
    public void start() {
        // scheduler thread start
        // jobStore.onStart
    }

    @Override
    public void shutdown() {
        // scheduler thread shutdown
        // jobStore.onShutdown
    }

    @Override
    public void shutdown(boolean waitForJobsToComplete) {

    }
}
