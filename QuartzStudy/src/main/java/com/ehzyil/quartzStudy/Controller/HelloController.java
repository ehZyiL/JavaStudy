package com.ehzyil.quartzStudy.Controller;

import com.ehzyil.quartzStudy.HelloJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ehyzil
 * @Description
 * @create 2023-10-2023/10/15-22:09
 */
@RestController
public class HelloController {

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/hello")
    public void helloJob(String name) throws SchedulerException {
        // 定义一个的任务
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job11", "group1")
                .usingJobData("name", name)
                .build();

        // 定义一个简单的触发器: 每隔 1 秒执行 1 次，任务永不停止
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever()
                ).build();

        // 开始调度
        scheduler.scheduleJob(job, trigger);
    }
}