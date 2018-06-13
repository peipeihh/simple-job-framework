package com.pphh.job.trigger;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class CronTriggerBuilder extends AbstractTriggerBuilder<SimpleTrigger> {

    @Override
    public MutableTrigger build() {
        return null;
    }

    public static CronTriggerBuilder cronSchedule(String cronExpression) {
        return new CronTriggerBuilder();
    }
}
