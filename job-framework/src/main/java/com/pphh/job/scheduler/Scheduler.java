package com.pphh.job.scheduler;

import com.pphh.job.exception.SchedulerException;
import com.pphh.job.job.JobDetail;
import com.pphh.job.trigger.Trigger;

import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface Scheduler {

    Date scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException;

    void start();

    void shutdown();

    void shutdown(boolean waitForJobsToComplete);

}
