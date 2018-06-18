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
    private CronExpression cronExpression = null;
    private Date startTime = null;
    private Date endTime = null;
    private Date nextFireTime = null;
    private Date previousFireTime = null;
    private int timesTriggered = 0;

    public CronExpression getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(CronExpression cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public Date computeFirstFireTime() {
        this.nextFireTime = getStartTime();
        this.previousFireTime = this.nextFireTime;
        return this.nextFireTime;
    }

    @Override
    public AbstractTriggerBuilder<CronTrigger> getTriggerBuilder() {
        return CronTriggerBuilder.cronSchedule(this.cronExpression);
    }

    @Override
    public void triggered() {
        this.timesTriggered++;
        this.previousFireTime = this.nextFireTime;
        this.nextFireTime = getFireTimeAfter(this.nextFireTime);
    }

    @Override
    public String getKey() {
        return this.triggerKey;
    }

    @Override
    public void setKey(String key) {
        this.triggerKey = key;
    }

    @Override
    public Date getStartTime() {
        return this.startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return this.endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public Date getNextFireTime() {
        return this.nextFireTime;
    }

    @Override
    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    @Override
    public Date getPreviousFireTime() {
        return this.previousFireTime;
    }

    @Override
    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    @Override
    public Date getFireTimeAfter(Date afterTime) {
        if (afterTime == null) {
            afterTime = new Date();
        }

        if (getStartTime().after(afterTime)) {
            afterTime = new Date(getStartTime().getTime() - 1000L);
        }

        if (getEndTime() != null && (afterTime.compareTo(getEndTime()) >= 0)) {
            return null;
        }

        Date time = getTimeAfter(afterTime);
        if (getEndTime() != null && time != null && time.after(getEndTime())) {
            return null;
        }

        return time;
    }

    protected Date getTimeAfter(Date afterTime) {
        return (cronExpression == null) ? null : cronExpression.getTimeAfter(afterTime);
    }

    @Override
    public GeneralTriggerBuilder<CronTrigger> getGeneralTriggerBuilder() {
        return GeneralTriggerBuilder.newTrigger()
                .startAt(getStartTime())
                .endAt(getEndTime())
                .withSchedule(getTriggerBuilder());
    }
}
