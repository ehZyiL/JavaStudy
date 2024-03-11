package com.example.applicationeventstudy.event.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */

@Component
@Slf4j
public class Demo1Listener implements ApplicationListener<ApplicationEvent> {
    //2.监听事件
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("[onApplicationEvent]监听到事件：{}", event.toString());
    }
}
