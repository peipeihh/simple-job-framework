package com.pphh.job.scheduler;


import com.pphh.job.trigger.MutableTrigger;
import com.pphh.job.trigger.SimpleTrigger;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class SimpleScheduleBuilder extends ScheduleBuilder<SimpleTrigger>  {

    @Override
    public MutableTrigger build() {
        return null;
    }

    public static SimpleScheduleBuilder simpleSchedule() {
        return new SimpleScheduleBuilder();
    }
}
