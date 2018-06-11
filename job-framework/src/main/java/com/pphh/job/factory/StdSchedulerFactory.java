package com.pphh.job.factory;

import com.pphh.job.jobstore.JobStore;
import com.pphh.job.jobstore.RamJobStore;
import com.pphh.job.scheduler.Scheduler;
import com.pphh.job.exception.SchedulerException;
import com.pphh.job.scheduler.StdScheduler;
import com.pphh.job.thread.SimpleThreadPool;
import com.pphh.job.thread.ThreadPool;

import java.util.Collection;
import java.util.HashMap;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class StdSchedulerFactory implements SchedulerFactory {
    private static StdSchedulerFactory ourInstance = new StdSchedulerFactory();
    private HashMap<String, Scheduler> schedulerMap = new HashMap<String, Scheduler>();
    private ThreadPool threadPool = new SimpleThreadPool();

    private StdSchedulerFactory() {
    }

    public static StdSchedulerFactory getInstance() {
        return ourInstance;
    }

    @Override
    public Scheduler getScheduler() throws SchedulerException {
        return getScheduler("default");
    }

    @Override
    public Scheduler getScheduler(String schedName) throws SchedulerException {
        Scheduler scheduler = null;
        if (schedulerMap.containsKey(schedName)) {
            scheduler = schedulerMap.get(schedName);
        } else {
            scheduler = instantiate();
        }
        return scheduler;
    }

    @Override
    public Collection<Scheduler> getAllSchedulers() throws SchedulerException {
        return null;
    }

    private Scheduler instantiate() {

        // TODO: Shared ThreadPool

        // TODO: one JobStore for each scheduler
        JobStore jobStore = new RamJobStore();

        SchedulerResources schedulerRes = new SchedulerResources();
        schedulerRes.setThreadPool(this.threadPool);
        schedulerRes.setJobStore(jobStore);

        Long idleWaitTime = 0L;
        Scheduler scheduler = new StdScheduler(schedulerRes, idleWaitTime);
        return scheduler;
    }
}
