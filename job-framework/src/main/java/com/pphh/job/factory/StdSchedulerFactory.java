package com.pphh.job.factory;

import com.pphh.job.scheduler.Scheduler;
import com.pphh.job.exception.SchedulerException;

import java.util.Collection;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class StdSchedulerFactory implements SchedulerFactory{
    private static StdSchedulerFactory ourInstance = new StdSchedulerFactory();

    public static StdSchedulerFactory getInstance() {
        return ourInstance;
    }

    private StdSchedulerFactory() {
    }

    @Override
    public Scheduler getScheduler() throws SchedulerException {
        return null;
    }

    @Override
    public Scheduler getScheduler(String schedName) throws SchedulerException {
        return null;
    }

    @Override
    public Collection<Scheduler> getAllSchedulers() throws SchedulerException {
        return null;
    }
}
