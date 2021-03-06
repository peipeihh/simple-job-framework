package com.pphh.job.factory;

import com.pphh.job.exception.SchedulerException;
import com.pphh.job.scheduler.Scheduler;

import java.util.Collection;

/**
 * initialize scheduler/schedulerThread/workThreadpool
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface SchedulerFactory {

    Scheduler getScheduler() throws SchedulerException;

    Scheduler getScheduler(String schedName) throws SchedulerException;

    Collection<Scheduler> getAllSchedulers() throws SchedulerException;

}
