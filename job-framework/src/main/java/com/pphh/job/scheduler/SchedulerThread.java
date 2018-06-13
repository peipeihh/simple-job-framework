package com.pphh.job.scheduler;

import com.pphh.job.executor.Executor;
import com.pphh.job.executor.JobExecutor;
import com.pphh.job.factory.SchedulerResources;
import com.pphh.job.job.Job;
import com.pphh.job.job.JobDetail;
import com.pphh.job.trigger.MutableTrigger;
import com.pphh.job.trigger.Trigger;
import com.pphh.job.util.LogUtil;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * generate triggers to be fired
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class SchedulerThread extends Thread {

    private static long DEFAULT_IDLE_WAIT_TIME = 30L * 1000L;
    private long idleWaitTime = DEFAULT_IDLE_WAIT_TIME;
    private final Object sigLock = new Object();
    private Scheduler scheduler;
    private SchedulerResources scheResources;
    private boolean signaled;
    private long signaledNextFireTime;

    private AtomicBoolean halted = new AtomicBoolean(false);
    private AtomicBoolean paused = new AtomicBoolean(true);

    public SchedulerThread(Scheduler scheduler, SchedulerResources resources) {
        //this.scheduler = scheduler;
        this.scheResources = resources;
    }

    @Override
    public void run() {
        //LogUtil.print("SchedulerThread::Run()");
        int acquiresFailed = 0;

        while (!halted.get()) {
            // check if we're supposed to pause...
            synchronized (sigLock) {
                while (paused.get() && !halted.get()) {
                    try {
                        // wait until togglePause(false) is called...
                        sigLock.wait(1000L);
                    } catch (InterruptedException ignore) {
                    }

                    // reset failure counter when paused, so that we don't
                    // wait again after unpausing
                    acquiresFailed = 0;
                }

                if (halted.get()) {
                    break;
                }
            }

            // check threadpool if there is available worker
            if (scheResources == null || scheResources.getThreadPool() == null) {
                continue;
            }

            // wait a bit, if there is failure to acquire next triggers
            // sleeping time is growing by failed times and max waiting time is 1 second
            if (acquiresFailed > 1) {
                try {
                    Thread.sleep(100 * Math.min(10, acquiresFailed));
                } catch (InterruptedException ignore) {
                }
            }

            // collect triggers to be fired within next 5 seconds
            long idleWaitTime = 5L * 1000L;
            long now = System.currentTimeMillis();
            List<Trigger> triggersToFired = null;
            try {
                triggersToFired = this.scheResources.getJobStore().acquireNextTriggers(now + idleWaitTime, 10, 0);
                acquiresFailed = 0;
            } catch (Exception e) {
                if (acquiresFailed < Integer.MAX_VALUE) {
                    acquiresFailed++;
                }
            }

            if (triggersToFired == null || triggersToFired.isEmpty()) {
                continue;
            }

            // run the trigger
            JobDetail jobDetail = scheResources.getJobDetail();
            Class<? extends Job> jobClazz = jobDetail.getJobClass();
            for (Trigger trigger : triggersToFired) {
                Date nextFireTime = trigger.getNextFireTime();

                Job job = null;
                try {
                    job = jobClazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    LogUtil.print("a exception happend when trying to initialize job instances");
                }
                if (job != null) {
                    Executor executor = new JobExecutor(job, nextFireTime.getTime());
                    scheResources.getThreadPool().runInThread(executor);
                }

                if (trigger instanceof MutableTrigger) {
                    this.scheResources.getJobStore().triggersFired((MutableTrigger) trigger);
                }
            }


            // save into job store
            //this.scheResources.getJobStore().

        }

    }

    public void togglePause(boolean pause) {
        synchronized (sigLock) {
            paused.set(pause);

            if (paused.get()) {
                signalSchedulingChange(0);
            } else {
                sigLock.notifyAll();
            }
        }
    }

    public void signalSchedulingChange(long candidateNewNextFireTime) {
        synchronized (sigLock) {
            signaled = true;
            signaledNextFireTime = candidateNewNextFireTime;
            sigLock.notifyAll();
        }
    }

    public void halt(boolean wait) {
        synchronized (sigLock) {
            halted.set(true);

            if (paused.get()) {
                sigLock.notifyAll();
            } else {
                signalSchedulingChange(0);
            }
        }

        if (wait) {
            boolean interrupted = false;
            try {
                while (true) {
                    try {
                        join();
                        break;
                    } catch (InterruptedException _) {
                        interrupted = true;
                    }
                }
            } finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    void setIdleWaitTime(long waitTime) {
        idleWaitTime = waitTime;
    }

}
