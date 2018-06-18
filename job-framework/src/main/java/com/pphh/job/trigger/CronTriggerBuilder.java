package com.pphh.job.trigger;

import java.text.ParseException;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class CronTriggerBuilder extends AbstractTriggerBuilder<CronTrigger> {

    private CronExpression cronExpression;

    protected CronTriggerBuilder(CronExpression cronExpression) {
        if (cronExpression == null) {
            throw new NullPointerException("cronExpression cannot be null");
        }
        this.cronExpression = cronExpression;
    }

    public static CronTriggerBuilder cronSchedule(String cronExpressionStr) throws ParseException {
        if (cronExpressionStr == null) {
            throw new NullPointerException("cronExpression cannot be null");
        }
        CronExpression cronExpression = new CronExpression(cronExpressionStr);
        return new CronTriggerBuilder(cronExpression);
    }

    public static CronTriggerBuilder cronSchedule(CronExpression cronExpression) {
        return new CronTriggerBuilder(cronExpression);
    }

    @Override
    public MutableTrigger build() {
        CronTrigger trigger = new CronTrigger();
        trigger.setCronExpression(this.cronExpression);
        return trigger;
    }

}
