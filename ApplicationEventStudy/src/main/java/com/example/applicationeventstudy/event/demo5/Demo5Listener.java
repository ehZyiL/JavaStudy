package com.example.applicationeventstudy.event.demo5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Date 2024/3/11
 * @Description
 * @Author ehzyil
 */

@Component
@Slf4j
public class Demo5Listener {
    //2.监听事件
//    @Order(1000)
    @Async
    @EventListener()
    public void onApplicationEvent001(Demo5Event event) throws Exception {
        log.info("[onApplicationEvent](001)监听到事件：{}", event.toString());
        Thread.sleep(1000);
    }

    //    @Order(100)
    @Async
    @EventListener()
    public void onApplicationEvent002(Demo5Event event) throws Exception {
        log.info("[onApplicationEvent](002)监听到事件：{}", event.toString());

    }

    /** 使用Order()
     * 2024-03-11T21:06:25.694+08:00   c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](002)监听到事件：Demo5Event(id=001)
     * 2024-03-11T21:06:25.696+08:00   c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](001)监听到事件：Demo5Event(id=001)
     * 2024-03-11T21:06:25.696+08:00   c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](002)监听到事件：Demo5Event(id=002)
     * 2024-03-11T21:06:25.696+08:00   c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](001)监听到事件：Demo5Event(id=002)
     */

    /** 使用Async()
     * 2024-03-11T21:16:41.516+08:00             main] c.e.a.event.demo5.Demo5App               : [onApplicationEvent](666)监听到事件：Demo6Event(id=666)
     * 2024-03-11T21:16:41.515+08:00           task-1] c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](002)监听到事件：Demo5Event(id=001)
     * 2024-03-11T21:16:41.515+08:00           task-3] c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](002)监听到事件：Demo5Event(id=002)
     * 2024-03-11T21:16:41.515+08:00           task-2] c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](001)监听到事件：Demo5Event(id=001)
     * 2024-03-11T21:16:41.516+08:00           task-4] c.e.a.event.demo5.Demo5Listener          : [onApplicationEvent](001)监听到事件：Demo5Event(id=002)
     */
}
