package com.example.applicationeventstudy.event.demo3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */

@Component
@Slf4j
public class Demo3Listener {
    //2.监听事件
    //使用注解可以不用实现ApplicationListener<>
    @EventListener()
    public void onApplicationEvent001(ApplicationEvent event) {
        log.info("[onApplicationEvent](001)监听到事件：{}", event.toString());
    }

    @EventListener()
    public void onApplicationEvent002(Demo3Event event) {
        log.info("[onApplicationEvent](002)监听到事件：{}", event.toString());
        //[onApplicationEvent]监听到事件：Demo3Event(id=自定义参数)
    }

/**
 *  [onApplicationEvent](001)监听到事件：org.springframework.boot.context.event.***
 *  [onApplicationEvent](001)监听到事件：org.springframework.boot.availability.***
 *  [onApplicationEvent](001)监听到事件：Demo3Event(id=自定义参数)
 *  [onApplicationEvent](002)监听到事件：Demo3Event(id=自定义参数)
 *  [onApplicationEvent](001)监听到事件：org.springframework.boot.context.event.***
 *  [onApplicationEvent](001)监听到事件：org.springframework
 */
}
