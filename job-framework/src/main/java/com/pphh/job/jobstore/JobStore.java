package com.pphh.job.jobstore;

import com.pphh.job.job.JobDetail;
import com.pphh.job.trigger.MutableTrigger;
import com.pphh.job.trigger.Trigger;

import java.util.List;

/**
 * Storage of Job and Triggers
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface JobStore {

    List<Trigger> acquireNextTriggers(long noLaterThan, int maxCount, long timeWindow);

    void storeJobAndTrigger(JobDetail jobDetail, Trigger newTrigger);

    void storeJob(JobDetail jobDetail, boolean replaceExisting);

    void removeJob(String jobKey);

    JobDetail retrieveJob(String jobKey);

    Boolean checkJobExists(String jobKey);

    void storeTrigger(Trigger newTrigger, boolean replaceExisting);

    void removeTrigger(String triggerKey);

    Trigger retrieveTrigger(String triggerKey);

    Boolean checkTriggerExists(String triggerKey);

    void onSchedulerStarted();

    void onSchedulerPaused();

    void onSchedulerResumed();

    void shutdown();

    Boolean supportsPersistence();

    void triggersFired(MutableTrigger trigger);

}
