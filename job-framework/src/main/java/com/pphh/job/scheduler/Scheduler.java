package com.pphh.job.scheduler;

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

    void start();

    void shutdown();

    void shutdown(boolean waitForJobsToComplete);

    Date scheduleJob(JobDetail jobDetail, Trigger trigger);

}
