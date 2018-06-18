package com.pphh.job.trigger;

import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class GeneralTriggerBuilder<T extends Trigger> {

    private String triggerKey;
    private String description;
    private Date startTime = new Date();
    private Date endTime;
    private AbstractTriggerBuilder<?> scheduleBuilder = null;

    private GeneralTriggerBuilder() {

    }

    public static GeneralTriggerBuilder<Trigger> newTrigger() {
        return new GeneralTriggerBuilder<Trigger>();
    }

    public T build() {
        if (scheduleBuilder == null) {
            scheduleBuilder = SimpleTriggerBuilder.simpleSchedule();
        }
        MutableTrigger trig = scheduleBuilder.build();
        trig.setStartTime(startTime);
        trig.setEndTime(endTime);
        return (T) trig;
    }

    public <SBT extends T> GeneralTriggerBuilder<SBT> withSchedule(AbstractTriggerBuilder<SBT> schedBuilder) {
        this.scheduleBuilder = schedBuilder;
        return (GeneralTriggerBuilder<SBT>) this;
    }

    public GeneralTriggerBuilder<T> startAt(Date triggerStartTime) {
        this.startTime = triggerStartTime;
        return this;
    }

    public GeneralTriggerBuilder<T> endAt(Date triggerEndTime) {
        this.endTime = triggerEndTime;
        return this;
    }

}
