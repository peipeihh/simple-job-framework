package com.pphh.job.executor;

import com.pphh.job.job.Job;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/11/2018
 */
public class JobExecutor implements Executor {

    private Job job;
    private long triggerTime;

    public JobExecutor(Job job, long triggerTime) {
        this.job = job;
        this.triggerTime = triggerTime;
    }

    @Override
    public JobExecutionResult call() throws Exception {
        JobExecutionContext context = new JobExecutionContext();
        // wait for trigger
        long timeUntilTrigger = this.triggerTime - System.currentTimeMillis();
        while (timeUntilTrigger > 2){
            timeUntilTrigger = this.triggerTime - System.currentTimeMillis();
        }

        job.execute(context);
        JobExecutionResult result = new JobExecutionResult();
        return result;
    }

}
