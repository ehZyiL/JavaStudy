package com.example.applicationeventstudy.event.demo3;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
@ToString
public class Demo3Event extends ApplicationEvent {
    private String id;

    public Demo3Event(Object source) {
        super(source);
    }

    public Demo3Event(Object source, String id) {
        super(source);
        this.id = id;
    }
}
