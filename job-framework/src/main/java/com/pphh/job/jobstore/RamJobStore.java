package com.pphh.job.jobstore;

import com.pphh.job.job.JobDetail;
import com.pphh.job.trigger.Trigger;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class RamJobStore implements JobStore {

    @Override
    public void storeJobAndTrigger(JobDetail jobDetail, Trigger newTrigger) {

    }

    @Override
    public void storeJob(JobDetail jobDetail, Boolean replaceExisting) {

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
    public void storeTrigger(Trigger newTrigger, Boolean replaceExisting) {

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

}
