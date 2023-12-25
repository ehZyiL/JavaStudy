package com.ehzyil.quartzStudy.test;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 * @author ehyzil
 * @Description
 * @create 2023-10-2023/10/15-21:33
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
//        System.out.println("hello quartz!");

        // 通过 JobDataMap 对象，可以在作业的执行逻辑中，获取参数
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String name = jobDataMap.getString("name");

        System.out.println("hello " + name);
    }
}