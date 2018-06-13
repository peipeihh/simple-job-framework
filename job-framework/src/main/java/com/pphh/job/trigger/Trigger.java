package com.pphh.job.trigger;

import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface Trigger {

    public String getKey();

    public Date getStartTime();

    public Date getEndTime();

    public Date getNextFireTime();

    public Date getPreviousFireTime();

    public Date getFireTimeAfter(Date afterTime);

    public GeneralTriggerBuilder<? extends Trigger> getGeneralTriggerBuilder();
}
