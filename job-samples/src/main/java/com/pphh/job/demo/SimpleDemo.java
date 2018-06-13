package com.pphh.job.demo;

import com.pphh.job.scheduler.Scheduler;
import com.pphh.job.exception.SchedulerException;
import com.pphh.job.factory.SchedulerFactory;
import com.pphh.job.factory.StdSchedulerFactory;
import com.pphh.job.job.JobBuilder;
import com.pphh.job.job.JobDetail;
import com.pphh.job.trigger.SimpleTriggerBuilder;
import com.pphh.job.trigger.Trigger;
import com.pphh.job.trigger.GeneralTriggerBuilder;
import com.pphh.job.util.LogUtil;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class SimpleDemo {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        LogUtil.print("create job and trigger...");
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).build();
        Trigger trigger = GeneralTriggerBuilder.newTrigger()
                .withSchedule(SimpleTriggerBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(10))
                .build();


        LogUtil.print("prepare scheduler...");
        SchedulerFactory schedulerFactory = StdSchedulerFactory.getInstance();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);

        LogUtil.print("start scheduler and run...");
        scheduler.start();

        LogUtil.print("sleeping for 60 seconds and wait for scheduler to run job.");
        Thread.sleep(60 * 1000L);

        LogUtil.print("shutdown the scheduler gracefully.");
        scheduler.shutdown(Boolean.TRUE);
    }

}
