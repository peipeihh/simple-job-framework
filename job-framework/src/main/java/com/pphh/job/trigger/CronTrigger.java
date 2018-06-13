package com.pphh.job.trigger;

import java.util.Date;
import java.util.UUID;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class CronTrigger implements MutableTrigger {

    private String triggerKey = UUID.randomUUID().toString();
    private String cronExpression = null;
    private Date startTime = null;
    private Date endTime = null;
    private Date nextFireTime = null;
    private Date previousFireTime = null;


    @Override
    public void setKey(String key) {
        this.triggerKey = key;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public Date computeFirstFireTime() {
        return null;
    }

    @Override
    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    @Override
    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    @Override
    public AbstractTriggerBuilder<? extends Trigger> getTriggerBuilder() {
        return null;
    }

    @Override
    public void triggered() {

    }

    @Override
    public String getKey() {
        return this.triggerKey;
    }

    @Override
    public Date getStartTime() {
        return this.startTime;
    }

    @Override
    public Date getEndTime() {
        return this.endTime;
    }

    @Override
    public Date getNextFireTime() {
        return this.nextFireTime;
    }

    @Override
    public Date getPreviousFireTime() {
        return this.previousFireTime;
    }
    @Override
    public Date getFireTimeAfter(Date afterTime) {
        return null;
    }

    @Override
    public GeneralTriggerBuilder<CronTrigger> getGeneralTriggerBuilder() {
        return null;
    }
}
