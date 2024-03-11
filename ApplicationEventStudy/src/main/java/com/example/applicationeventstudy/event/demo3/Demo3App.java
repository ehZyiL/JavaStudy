package com.example.applicationeventstudy.event.demo3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
@Slf4j
@SpringBootApplication
public class Demo3App implements ApplicationRunner {
    /**
     * 1.自定义事件 Demo3Event 添加业务参数
     * 2.忽略事件源 根据实际业务情况而定 减少参数
     * 3.使用 @EventListener 替换 implements ApplicationListener<Demo2Event> 增加监听者的可扩展性
     */

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Demo3App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.发布自定义事件
        applicationContext.publishEvent(new Demo3Event(this, "自定义参数") {

        });

    }
}

