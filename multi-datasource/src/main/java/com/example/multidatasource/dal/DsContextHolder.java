package com.example.multidatasource.dal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DsContextHolder {
    /**
     * 使用继承的线程上下文，支持异步时选择传递
     * 使用DsNode，支持链式的数据源切换，如最外层使用master数据源，内部某个方法使用slave数据源；但是请注意，对于事务的场景，不要交叉
     */
    private static final ThreadLocal<DsNode> CONTEXT_HOLDER = new InheritableThreadLocal<>();

    private DsContextHolder() {
    }

    public static void set(String dbType) {
        DsNode current = CONTEXT_HOLDER.get();
        CONTEXT_HOLDER.set(new DsNode(current, dbType));
    }

    public static String get() {
        DsNode dsNode = CONTEXT_HOLDER.get();
        log.info("get dbType:{}", CONTEXT_HOLDER.get());
        return dsNode==null? null:dsNode.ds;
    }

    public static void set(DS ds) {
        set(ds.name().toUpperCase());
    }

    /**
     * 移除上下文
     */
    public static void reset() {
        DsNode ds = CONTEXT_HOLDER.get();
        if (ds == null) {
            return;
        }

        if (ds.pre != null) {
            // 退出当前的数据源选择，切回去走上一次的数据源配置
            CONTEXT_HOLDER.set(ds.pre);
        } else {
            CONTEXT_HOLDER.remove();
        }
    }

    public static class DsNode {
        DsNode pre;
        String ds;

        public DsNode(DsNode parent, String ds) {
            pre = parent;
            this.ds = ds;
        }
    }
}
