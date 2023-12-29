package com.example.multidatasource.dal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DsContextHolder {
    /**
     * 使用继承的线程上下文，支持异步时选择传递
     * 使用DsNode，支持链式的数据源切换，如最外层使用master数据源，内部某个方法使用slave数据源；但是请注意，对于事务的场景，不要交叉
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new InheritableThreadLocal<>();

    private DsContextHolder() {
    }

    public static void set(String dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    public static String get() {
        log.info("get dbType:{}", CONTEXT_HOLDER.get());
        return CONTEXT_HOLDER.get();
    }

    public static void set(DS ds) {
        set(ds.name().toUpperCase());
    }

    /**
     * 移除上下文
     */
    public static void reset() {
        CONTEXT_HOLDER.remove();
    }
}
