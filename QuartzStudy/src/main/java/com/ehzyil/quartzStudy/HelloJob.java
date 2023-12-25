package com.ehzyil.quartzStudy;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author ehyzil
 * @Description
 * @create 2023-10-2023/10/15-22:06
 */
public class HelloJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String name = jobDataMap.getString("name");
        System.out.println("Hello :" + name);

    }
}
