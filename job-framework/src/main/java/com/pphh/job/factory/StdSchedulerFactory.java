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
    private ThreadPool threadPool = SimpleThreadPool.getInstance();

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
            schedulerMap.put(schedName, scheduler);
        }
        return scheduler;
    }

    @Override
    public Collection<Scheduler> getAllSchedulers() throws SchedulerException {
        return null;
    }

    private Scheduler instantiate() {
        // TODO: one JobStore for each scheduler
        SchedulerResources schedulerRes = new SchedulerResources();
        schedulerRes.setThreadPool(this.threadPool);
        JobStore jobStore = new RamJobStore();
        schedulerRes.setJobStore(jobStore);

        Long idleWaitTimeBySeconds = 5L;
        return new StdScheduler(schedulerRes, idleWaitTimeBySeconds);
    }
}
