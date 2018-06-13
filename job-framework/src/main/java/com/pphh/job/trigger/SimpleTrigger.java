package com.pphh.job.trigger;

import com.pphh.job.util.LogUtil;

import java.util.Date;
import java.util.UUID;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class SimpleTrigger implements MutableTrigger {
    public static final int REPEAT_INDEFINITELY = -1;
    private static final int YEAR_TO_GIVEUP_SCHEDULING_AT = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + 100;

    private String triggerKey = UUID.randomUUID().toString();

    private Date startTime = new Date();

    private Date endTime = null;

    private Date nextFireTime = null;

    private Date previousFireTime = null;

    private int repeatCount = 0;

    private long repeatInterval = 1;

    private int timesTriggered = 0;

    @Override
    public String getKey() {
        return this.triggerKey;
    }

    @Override
    public void setKey(String key) {
        this.triggerKey = triggerKey;
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
    public Date computeFirstFireTime() {
        this.nextFireTime = getStartTime();
        this.previousFireTime = this.nextFireTime;
        return this.nextFireTime;
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
        if ((timesTriggered >= repeatCount) && (repeatCount != REPEAT_INDEFINITELY)) {
            return null;
        }

        if (afterTime == null) {
            afterTime = new Date();
        }

        if (this.repeatCount == 0 && afterTime.compareTo(getStartTime()) >= 0) {
            return null;
        }

        long startMillis = getStartTime().getTime();
        long afterMillis = afterTime.getTime();
        long endMillis = (getEndTime() == null) ? Long.MAX_VALUE : getEndTime().getTime();

        if (endMillis <= afterMillis) {
            return null;
        }

        if (afterMillis < startMillis) {
            return new Date(startMillis);
        }

        long numberOfTimesExecuted = ((afterMillis - startMillis) / repeatInterval) + 1;
        if ((numberOfTimesExecuted > repeatCount) && (repeatCount != REPEAT_INDEFINITELY)) {
            return null;
        }

        Date time = new Date(startMillis + (numberOfTimesExecuted * repeatInterval));
        if (endMillis <= time.getTime()) {
            return null;
        }

        //LogUtil.print("trigger time: " + time.toString());
        return time;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        if (repeatCount < 0 && repeatCount != REPEAT_INDEFINITELY) {
            throw new IllegalArgumentException(
                    "Repeat count must be >= 0, use the "
                            + "constant REPEAT_INDEFINITELY for infinite.");
        }

        this.repeatCount = repeatCount;
    }

    public long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(long repeatInterval) {
        if (repeatInterval < 0) {
            throw new IllegalArgumentException(
                    "Repeat interval must be >= 0");
        }

        this.repeatInterval = repeatInterval;
    }

    @Override
    public AbstractTriggerBuilder<SimpleTrigger> getTriggerBuilder() {
        return SimpleTriggerBuilder.simpleSchedule()
                .withIntervalInSeconds(getRepeatInterval())
                .withRepeatCount(getRepeatCount());
    }

    @Override
    public void triggered() {
        this.timesTriggered++;
        this.previousFireTime = this.nextFireTime;
        this.nextFireTime = getFireTimeAfter(this.nextFireTime);
    }

    @Override
    public GeneralTriggerBuilder<SimpleTrigger> getGeneralTriggerBuilder() {
        return GeneralTriggerBuilder.newTrigger()
                .startAt(getStartTime())
                .endAt(getEndTime())
                .withSchedule(getTriggerBuilder());
    }
}
