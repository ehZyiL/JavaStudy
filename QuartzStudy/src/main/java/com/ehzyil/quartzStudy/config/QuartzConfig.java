package com.ehzyil.quartzStudy.config;

import com.ehzyil.quartzStudy.HelloJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ehyzil
 * @Description
 * @create 2023-10-2023/10/15-22:16
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job11", "group1")
                .usingJobData("name", "springboot")
                .storeDurably()
                .build();

        return job;
    }

    @Bean
    public Trigger trigger() {
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever()
                ).build();

        return trigger;
    }
}