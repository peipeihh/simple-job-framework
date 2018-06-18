package com.pphh.job.demo;

import com.pphh.job.exception.SchedulerException;
import com.pphh.job.factory.SchedulerFactory;
import com.pphh.job.factory.StdSchedulerFactory;
import com.pphh.job.job.JobBuilder;
import com.pphh.job.job.JobDetail;
import com.pphh.job.scheduler.Scheduler;
import com.pphh.job.trigger.CronTrigger;
import com.pphh.job.trigger.CronTriggerBuilder;
import com.pphh.job.trigger.GeneralTriggerBuilder;
import com.pphh.job.util.LogUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/18/2018
 */
public class CronTriggerDemo {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
        LogUtil.print("create job and trigger...");
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).build();
        CronTrigger trigger = GeneralTriggerBuilder.newTrigger()
                .startAt(new Date())
                .endAt(new Date(System.currentTimeMillis() + 60 * 1000))
                .withSchedule(CronTriggerBuilder.cronSchedule("0/10 * * * * ?"))
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
