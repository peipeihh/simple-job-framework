package com.pphh.job.trigger;

import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public interface MutableTrigger extends Trigger {

    public void setKey(String key);

    public void setStartTime(Date startTime);

    public void setEndTime(Date endTime);

    public Date computeFirstFireTime();

    public void setNextFireTime(Date nextFireTime);

    public void setPreviousFireTime(Date previousFireTime);

    public AbstractTriggerBuilder<? extends Trigger> getTriggerBuilder();

    public abstract void triggered();

}
