package com.pphh.job.trigger;


/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class SimpleTriggerBuilder extends AbstractTriggerBuilder<SimpleTrigger> {

    private long interval = 1;
    private int repeatCount = 1;

    public static SimpleTriggerBuilder simpleSchedule() {
        return new SimpleTriggerBuilder();
    }

    @Override
    public MutableTrigger build() {
        SimpleTrigger trigger = new SimpleTrigger();
        trigger.setRepeatInterval(interval);
        trigger.setRepeatCount(repeatCount);
        return trigger;
    }

    public SimpleTriggerBuilder withIntervalInSeconds(long intervalInSeconds) {
        this.interval = intervalInSeconds * 1000L;
        return this;
    }

    public SimpleTriggerBuilder withRepeatCount(int triggerRepeatCount) {
        this.repeatCount = triggerRepeatCount;
        return this;
    }

}
