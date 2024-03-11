package com.example.applicationeventstudy.event.demo5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
@Slf4j
@SpringBootApplication
@EnableAsync
public class Demo5App implements ApplicationRunner {
    /**
     * 异步监听
     * 1.@0rder 指定执行顺序 在同步的情况下生效
     * 2.@Async 异步执行 需要@EnableAsync 开启异步
     * 自定义事件 不继承 ApplicationEvent[根据特定情况自行设计，由仅依赖 ApplicationEvent 转变为 依赖自定义事件类]
     */

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Demo5App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.发布自定义事件
        applicationContext.publishEvent(new Demo5Event(this, "001") {

        });
        applicationContext.publishEvent(new Demo5Event(this, "002") {

        });
        applicationContext.publishEvent(new Demo6Event("666") {

        });

    }

    @EventListener()
    public void onApplicationEvent002(Demo6Event event) {
        log.info("[onApplicationEvent](666)监听到事件：{}", event.toString());
    }
/**
 * public interface ApplicationEventPublisher {
 *     default void publishEvent(ApplicationEvent event) {
 *         this.publishEvent((Object)event);
 *     }
 *
 *     void publishEvent(Object event);
 * }
 *
 * log：[onApplicationEvent](666)监听到事件：Demo6Event(id=666)
 */
}
