package com.pphh.job.trigger;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public abstract class AbstractTriggerBuilder<T extends Trigger>  {

    public abstract MutableTrigger build();

}
