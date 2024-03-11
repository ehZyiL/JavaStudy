package com.example.applicationeventstudy.event.demo4;

import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
@ToString
public class Demo4Event extends ApplicationEvent {
    private String id;

    public Demo4Event(Object source) {
        super(source);
    }

    public Demo4Event(Object source, String id) {
        super(source);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
