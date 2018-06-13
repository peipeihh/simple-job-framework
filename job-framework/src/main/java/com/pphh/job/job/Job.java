package com.pphh.job.job;

import com.pphh.job.executor.JobExecutionContext;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public interface Job {

    void execute(JobExecutionContext context);

}
