package com.pphh.job.trigger;

import com.pphh.job.scheduler.ScheduleBuilder;
import com.pphh.job.scheduler.SimpleScheduleBuilder;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class TriggerBuilder<T extends Trigger> {

    private ScheduleBuilder<?> scheduleBuilder = null;

    private TriggerBuilder() {

    }

    public static TriggerBuilder<Trigger> newTrigger() {
        return new TriggerBuilder<Trigger>();
    }

    public T build() {

        if(scheduleBuilder == null){
            scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        }
        MutableTrigger trig = scheduleBuilder.build();

        return (T) trig;
    }

    public <SBT extends T> TriggerBuilder<SBT> withSchedule(ScheduleBuilder<SBT> schedBuilder) {
        this.scheduleBuilder = schedBuilder;
        return (TriggerBuilder<SBT>) this;
    }

}
