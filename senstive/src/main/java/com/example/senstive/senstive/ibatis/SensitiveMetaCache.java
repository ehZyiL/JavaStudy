package com.example.senstive.senstive.ibatis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 敏感词缓存
 *
 * @author YiHui
 * @date 2023/8/9
 */
public class SensitiveMetaCache {
    // 敏感元数据缓存
    private static ConcurrentHashMap<String, SensitiveObjectMeta> CACHE = new ConcurrentHashMap<>();

    /**
     * 获取指定key对应的敏感元数据
     *
     * @param key 键
     * @return 指定key对应的敏感元数据
     */
    public static SensitiveObjectMeta get(String key) {
        return CACHE.get(key);
    }

    /**
     * 将敏感元数据放入缓存
     *
     * @param key  键
     * @param meta 敏感元数据
     */
    public static void put(String key, SensitiveObjectMeta meta) {
        CACHE.put(key, meta);
    }

    /**
     * 从缓存中移除指定key对应的敏感元数据
     *
     * @param key 键
     */
    public static void remove(String key) {
        CACHE.remove(key);
    }

    /**
     * 检查缓存中是否存在指定key对应的敏感元数据
     *
     * @param key 键
     * @return 缓存中是否存在指定key对应的敏感元数据
     */
    public static boolean contains(String key) {
        return CACHE.containsKey(key);
    }


    /**
     * 将敏感元数据放入缓存，如果键已存在，则更新对应的敏感元数据
     *
     * @param key  键
     * @param meta 敏感元数据
     * @return 新的敏感元数据
     */
    public static SensitiveObjectMeta putIfAbsent(String key, SensitiveObjectMeta meta) {
        return CACHE.putIfAbsent(key, meta);
    }

    /**
     * 从缓存中计算并获取指定key对应的敏感元数据
     *
     * @param key      键
     * @param function 计算函数
     * @return 指定key对应的敏感元数据
     */
    public static SensitiveObjectMeta computeIfAbsent(String key, Function<String, SensitiveObjectMeta> function) {
        return CACHE.computeIfAbsent(key, function);
    }
}