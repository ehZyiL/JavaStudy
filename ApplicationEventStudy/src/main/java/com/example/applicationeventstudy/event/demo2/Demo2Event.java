package com.example.applicationeventstudy.event.demo2;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
public class Demo2Event extends ApplicationEvent {
    public Demo2Event(Object source) {
        super(source);
    }

}
