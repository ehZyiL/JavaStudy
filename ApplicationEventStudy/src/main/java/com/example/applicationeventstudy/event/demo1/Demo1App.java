package com.example.applicationeventstudy.event.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
@Slf4j
@SpringBootApplication
public class Demo1App implements ApplicationRunner {
    /**
     * Spring事件驱动最基础的使用  ApplicationEventPublisher、ApplicationEvent、ApplicationListener
     * ApplicationEventPublisher 子类ApplicationContext
     * 事件源、监听器 需要被spring管理
     * 监听器 需要实现 ApplicationListener<ApplicationEvent>
     * 可体现事件源和监听器之间的松耦合 仅依赖spring、ApplicationEvent
     */

//    @Autowired
//    ApplicationEventPublisher appEventPublisher;

    @Autowired
    ApplicationContext applicationContext;
    //因为ApplicationContext实现了ApplicationEventPublisher接口因此常用它来发布事件

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.发布事件
        applicationContext.publishEvent(new ApplicationEvent(this) {

        });

    }

    public static void main(String[] args) {
        SpringApplication.run(Demo1App.class, args);
    }
}
