package com.example.applicationeventstudy.event.demo4;

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
public class Demo4Listener {
    //2.监听事件
    @EventListener(condition = "#event.id == '001'")
    public void onApplicationEvent001(Demo4Event event) {
        log.info("[onApplicationEvent](001)监听到事件：{}", event.toString());
    }

    @EventListener(condition = "#event.id == '002'")
    public void onApplicationEvent002(Demo4Event event) {
        log.info("[onApplicationEvent](002)监听到事件：{}", event.toString());
    }
    /**
     * [onApplicationEvent](001)监听到事件：Demo4Event(id=001)
     * [onApplicationEvent](002)监听到事件：Demo4Event(id=002)
     */
    @EventListener({Demo4Event.class, ApplicationEvent.class}) //在注解中也可以使用event来置顶监听的事件类
    public void onApplicationEvent003(Object event) {
        log.info("[onApplicationEvent](003)监听到事件：{}", event.toString());
    }

/**
 * 2024-03-11T20:58:55.022+08:00  : [onApplicationEvent](003)监听到事件：org.springframework.boot.availability.AvailabilityChangeEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@dbd8e44, started on Mon Mar 11 20:58:54 CST 2024]
 * 2024-03-11T20:58:55.039+08:00  : [onApplicationEvent](001)监听到事件：Demo4Event(id=001)
 * 2024-03-11T20:58:55.039+08:00  : [onApplicationEvent](003)监听到事件：Demo4Event(id=001)
 * 2024-03-11T20:58:55.040+08:00  : [onApplicationEvent](003)监听到事件：Demo4Event(id=002)
 * 2024-03-11T20:58:55.040+08:00  : [onApplicationEvent](002)监听到事件：Demo4Event(id=002)
 * 2024-03-11T20:58:55.040+08:00  : [onApplicationEvent](003)监听到事件：org.springframework.boot.context.event.ApplicationReadyEvent[source=org.springframework.boot.SpringApplication@43bdaa1b]
 * 2024-03-11T20:58:55.040+08:00  : [onApplicationEvent](003)监听到事件：org.springframework.boot.availability.AvailabilityChangeEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@dbd8e44, started on Mon Mar 11 20:58:54 CST 2024]
 */

}
