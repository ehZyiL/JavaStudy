package com.example.senstive.senstive.ibatis;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.senstive.senstive.SensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Proxy;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {java.sql.Statement.class})
})
@Component
@Slf4j
public class SensitiveReadInterceptor implements Interceptor {

    private static final String MAPPED_STATEMENT = "mappedStatement";

    @Autowired
    private SensitiveService sensitiveService;

    /**
     * 获取代理对象的真实目标对象。
     *
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            //获取一个对象的元对象
            MetaObject metaObject = SystemMetaObject.forObject(target);
            //如果 target 是一个代理对象，那么 realTarget 方法会递归地获取代理对象的真实目标对象，直到找到一个不是代理对象的对象。
            return realTarget(metaObject.getValue("h.target"));
        }
        return (T) target;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final List<Object> results = (List<Object>) invocation.proceed();

        if (results.isEmpty()) {
            return results;
        }
        final ResultSetHandler statementHandler = realTarget(invocation.getTarget());
        // 获取 statementHandler 对象的元对象。
        final MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        //获取 statementHandler 对象的 MAPPED_STATEMENT 属性
        final MappedStatement mappedStatement = (MappedStatement) metaObject.getValue(MAPPED_STATEMENT);
        // 从 results 列表中找到第一个非空元素，并将其包装在一个 Optional 对象中。
        Optional firstOpt = results.stream().filter(Objects::nonNull).findFirst();
        //检查 Optional 对象是否包含值
        if (!firstOpt.isPresent()) {
            return results;
        }

        Object firstObject = firstOpt.get();

        // 找到需要进行敏感词替换的数据库实体类的成员信息
        SensitiveObjectMeta sensitiveObjectMeta = findSensitiveObjectMeta(firstObject);

        // 执行替换的敏感词替换
        replaceSensitiveResults(results, mappedStatement, sensitiveObjectMeta);

        // 找到需要进行敏感词替换的数据库实体类的成员信息
        return results;
    }

    /**
     * 执行具体的敏感词替换
     *
     * @param results             结果列表
     * @param mappedStatement     MappedStatement 对象
     * @param sensitiveObjectMeta SensitiveObjectMeta 对象
     */
    private void replaceSensitiveResults(Collection<Object> results, MappedStatement mappedStatement, SensitiveObjectMeta sensitiveObjectMeta) {
        for (Object obj : results) {
            if (sensitiveObjectMeta.getSensitiveFieldMetaList() == null) continue;

            MetaObject objMetaObject = mappedStatement.getConfiguration().newMetaObject(obj);

            sensitiveObjectMeta.getSensitiveFieldMetaList().forEach(i -> {
                Object value = objMetaObject.getValue(StringUtils.isBlank(i.getBindField()) ? i.getName() : i.getBindField());

                if (value == null) {
                    return;
                } else if (value instanceof String) {
                    String strValue = (String) value;
                    // 替换字符串中的敏感词
                    String processVal = sensitiveService.replace(strValue);
                    objMetaObject.setValue(i.getName(), processVal);
                } else if (value instanceof Collection) {
                    Collection listValue = (Collection) value;
                    if (CollectionUtils.isNotEmpty(listValue)) {
                        Optional firstValOpt = listValue.stream().filter(Objects::nonNull).findFirst();
                        if (firstValOpt.isPresent()) {
                            // 找到第一个非空元素的敏感对象成员信息;
                            SensitiveObjectMeta valSensitiveObjectMeta = findSensitiveObjectMeta(firstValOpt.get());
                            if (Boolean.TRUE.equals(valSensitiveObjectMeta.getEnabledSensitiveReplace()) && CollectionUtils.isNotEmpty(valSensitiveObjectMeta.getSensitiveFieldMetaList())) {
                                // 递归进行敏感词替换
                                replaceSensitiveResults(listValue, mappedStatement, valSensitiveObjectMeta);
                            }

                        }
                    }
                } else if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
                    // 对于非基本类型的，需要对其内部进行敏感词替换
                    SensitiveObjectMeta valSensitiveObjectMeta = findSensitiveObjectMeta(value);
                    if (Boolean.TRUE.equals(valSensitiveObjectMeta.getEnabledSensitiveReplace()) && CollectionUtils.isNotEmpty(valSensitiveObjectMeta.getSensitiveFieldMetaList())) {
                        replaceSensitiveResults(newArrayList(value), mappedStatement, valSensitiveObjectMeta);
                    }
                }

            });
        }
    }

    /**
     * 查询对象中，携带有 @SensitiveField 的成员，进行敏感词替换
     *
     * @param firstObject 待查询的对象
     * @return 返回对象的敏感词元数据
     */
    private SensitiveObjectMeta findSensitiveObjectMeta(Object firstObject) {
        // 缓存中不存在，则计算键对应的值，并缓存
        SensitiveMetaCache.computeIfAbsent(firstObject.getClass().getName(), s -> {
            Optional<SensitiveObjectMeta> sensitiveObjectMeta = SensitiveObjectMeta.buildSensitiveObjectMeta(firstObject);
            return sensitiveObjectMeta.orElse(null);
        });
        // 从缓存中获取 SensitiveObjectMeta 对象。
        return SensitiveMetaCache.get(firstObject.getClass().getName());
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }


    @Override
    public void setProperties(Properties properties) {

    }
}