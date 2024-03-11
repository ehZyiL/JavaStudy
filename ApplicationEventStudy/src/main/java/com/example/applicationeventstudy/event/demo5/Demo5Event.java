package com.example.applicationeventstudy.event.demo5;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
@ToString
@Getter
public class Demo5Event extends ApplicationEvent {
    private String id;

    public Demo5Event(Object source) {
        super(source);
    }

    public Demo5Event(Object source, String id) {
        super(source);
        this.id = id;
    }
}
