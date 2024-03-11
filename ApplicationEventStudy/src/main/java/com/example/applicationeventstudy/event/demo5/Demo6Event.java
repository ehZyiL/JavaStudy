package com.example.applicationeventstudy.event.demo5;

import lombok.Getter;
import lombok.ToString;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */
@ToString
@Getter
/**
 * 不继承ApplicationEvent
 */
public class Demo6Event {
    private String id;

    public Demo6Event(String id) {
        this.id = id;
    }
}