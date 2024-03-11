package com.example.applicationeventstudy.event.demo2;

import com.example.applicationeventstudy.event.demo1.Demo1App;
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
public class Demo2App implements ApplicationRunner {
    /**
     * 自定义事件 Demo2Event
     * 继承 ApplicationEvent实现对指定类型事件进行监听
     */

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.发布自定义事件
        applicationContext.publishEvent(new Demo2Event(this) {

        });

    }

    public static void main(String[] args) {
        SpringApplication.run(Demo2App.class, args);
    }
}
