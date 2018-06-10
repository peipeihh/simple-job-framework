package com.pphh.job.job;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface JobDetail {

    public Class<? extends Job> getJobClass();

}
