package com.example.applicationeventstudy.event.demo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */

@Component
@Slf4j
public class Demo2Listener implements ApplicationListener<Demo2Event> {
    //2.监听事件
    @Override
    public void onApplicationEvent(Demo2Event event) {
        log.info("[onApplicationEvent]监听到事件：{}", event.toString());
        //[onApplicationEvent]监听到事件：com.example.applicationeventstudy.event.demo2.Demo2App$xxxxxx
    }
}
