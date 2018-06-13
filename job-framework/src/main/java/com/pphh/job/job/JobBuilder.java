package com.pphh.job.job;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/8/2018
 */
public class JobBuilder {

    private Class<? extends Job> jobClass;

    protected JobBuilder() {
    }

    public static JobBuilder newJob() {
        return new JobBuilder();
    }

    public static JobBuilder newJob(Class<? extends Job> jobClass) {
        JobBuilder b = new JobBuilder();
        b.ofType(jobClass);
        return b;
    }

    public JobBuilder ofType(Class<? extends Job> jobClazz) {
        this.jobClass = jobClazz;
        return this;
    }

    public JobDetail build() {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setJobClass(this.jobClass);
        return jobDetail;
    }

}
