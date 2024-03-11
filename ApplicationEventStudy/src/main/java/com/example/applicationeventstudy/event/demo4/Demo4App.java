package com.example.applicationeventstudy.event.demo4;

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
public class Demo4App implements ApplicationRunner {
    /**
     * @EventListener'用法讲解
     * 1.监听自定义事件
     * 2. 注解中指定监听事件类型，可指定多个监听事件类型
     * 3.注解中使用condition 根据特定条件进行监听 event的属性需要提供getter方法来访问它
     * 4.根据特定条件进行监听 对事件进行修改后返回
     */

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Demo4App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.发布自定义事件
        applicationContext.publishEvent(new Demo4Event(this, "001") {

        });
        applicationContext.publishEvent(new Demo4Event(this, "002") {

        });

    }
}
