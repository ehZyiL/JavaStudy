package com.example.multidatasource.dal;

import java.util.function.Supplier;

/**
 * 手动指定数据源的用法
 */
public class DsSelectExecutor {

    /**
     * 有返回结果
     *
     * @param ds       数据源
     * @param supplier 供应商
     * @param <T>      泛型类型
     * @return 返回结果
     */
    public static <T> T submit(DS ds, Supplier<T> supplier) {
        DsContextHolder.set(ds);
        try {
            return supplier.get();
        } finally {
            DsContextHolder.reset();
        }
    }

    /**
     * 无返回结果
     *
     * @param ds   数据源
     * @param call 可调用对象
     */
    public static void execute(DS ds, Runnable call) {
        DsContextHolder.set(ds);
        try {
            call.run();
        } finally {
            DsContextHolder.reset();
        }
    }
}