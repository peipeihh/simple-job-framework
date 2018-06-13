package com.pphh.job.job;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/13/2018
 */
public class JobDetailImpl implements JobDetail{

    private Class<? extends Job> jobClass;

    @Override
    public Class<? extends Job> getJobClass() {
        return this.jobClass;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        if (jobClass == null) {
            throw new IllegalArgumentException("Job class cannot be null.");
        }

        if (!Job.class.isAssignableFrom(jobClass)) {
            throw new IllegalArgumentException(
                    "Job class must implement the Job interface.");
        }

        this.jobClass = jobClass;
    }

}
