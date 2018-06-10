package com.pphh.job.scheduler;

import com.pphh.job.trigger.MutableTrigger;
import com.pphh.job.trigger.Trigger;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public abstract class ScheduleBuilder<T extends Trigger>  {

    public abstract MutableTrigger build();

}
