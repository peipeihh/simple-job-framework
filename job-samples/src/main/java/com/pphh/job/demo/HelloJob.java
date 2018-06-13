package com.pphh.job.demo;

import com.pphh.job.job.Job;
import com.pphh.job.executor.JobExecutionContext;
import com.pphh.job.util.LogUtil;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/10/2018
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        LogUtil.print("hello,world");
    }

}
