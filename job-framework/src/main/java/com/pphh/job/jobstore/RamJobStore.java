package com.pphh.job.jobstore;

import com.pphh.job.job.JobDetail;
import com.pphh.job.trigger.MutableTrigger;
import com.pphh.job.trigger.Trigger;
import com.pphh.job.util.LogUtil;

import java.util.*;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class RamJobStore implements JobStore {

    protected final Object lock = new Object();
    private Map<String, Trigger> triggerMap = new HashMap<>();

    @Override
    public List<Trigger> acquireNextTriggers(long noLaterThan, int maxCount, long timeWindow) {
        synchronized (lock) {
            List<Trigger> triggers = new ArrayList<>();
            if (triggerMap.size() == 0) {
                return triggers;
            }

            for (Map.Entry<String, Trigger> entry : triggerMap.entrySet()) {
                Trigger trigger = entry.getValue();
                Date nextFireTime = trigger.getNextFireTime();
                if (nextFireTime != null) {
                    if (nextFireTime.getTime() < noLaterThan) {
                        //LogUtil.print(String.format("noLaterthan %s, nextFireTime %s, %s", nextFireTime, new Date(noLaterThan),nextFireTime.getTime() < noLaterThan ));
                        triggers.add(trigger);
                    }
                }

                if (triggers.size() == maxCount) {
                    break;
                }
            }

            return triggers;
        }
    }

    @Override
    public void storeJobAndTrigger(JobDetail jobDetail, Trigger newTrigger) {
        storeJob(jobDetail, true);
        storeTrigger(newTrigger, true);
    }

    @Override
    public void storeJob(JobDetail jobDetail, boolean replaceExisting) {

    }

    @Override
    public void removeJob(String jobKey) {

    }

    @Override
    public JobDetail retrieveJob(String jobKey) {
        return null;
    }

    @Override
    public Boolean checkJobExists(String jobKey) {
        return null;
    }

    @Override
    public void storeTrigger(Trigger newTrigger, boolean replaceExisting) {
        synchronized (lock) {
            if (triggerMap.containsKey(newTrigger.getKey())) {
                if (!replaceExisting) {
                    // throw exception
                } else {
                    triggerMap.put(newTrigger.getKey(), newTrigger);
                }
            } else {
                triggerMap.put(newTrigger.getKey(), newTrigger);
            }
        }
    }

    @Override
    public void removeTrigger(String triggerKey) {

    }

    @Override
    public Trigger retrieveTrigger(String triggerKey) {
        return null;
    }

    @Override
    public Boolean checkTriggerExists(String triggerKey) {
        return null;
    }

    @Override
    public void onSchedulerStarted() {

    }

    @Override
    public void onSchedulerPaused() {

    }

    @Override
    public void onSchedulerResumed() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public Boolean supportsPersistence() {
        return null;
    }

    @Override
    public void triggersFired(MutableTrigger trigger) {
        trigger.triggered();
    }

}
