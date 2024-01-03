package com.example.senstive.dal;

import com.alibaba.druid.pool.DruidPooledPreparedStatement;
import com.baomidou.mybatisplus.core.MybatisParameterHandler;
import com.example.senstive.util.DateUtil;
import com.example.senstive.util.DruidCheckUtil;
import com.mysql.cj.MysqlConnection;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import com.zaxxer.hikari.pool.HikariProxyPreparedStatement;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

// Define a custom MyBatis plugin to handle data masking.

/**
 * SQL状态拦截器
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
@Slf4j
public class SqlStateInterceptor implements Interceptor {

    /**
     * 拦截方法
     *
     * @param invocation 调用的Invocation对象
     * @return Object
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long time = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        String sql = buildSql(statementHandler);
        Object[] args = invocation.getArgs();
        String uname = "";
        if (args[0] instanceof HikariProxyPreparedStatement) {
            HikariProxyConnection connection = (HikariProxyConnection) ((HikariProxyPreparedStatement) invocation.getArgs()[0]).getConnection();
            uname = connection.getMetaData().getUserName();
        } else if (DruidCheckUtil.hasDuridPkg()) {
            if (args[0] instanceof DruidPooledPreparedStatement) {
                Connection connection = ((DruidPooledPreparedStatement) args[0]).getStatement().getConnection();
                if (connection instanceof MysqlConnection) {
                    Properties properties = ((MysqlConnection) connection).getProperties();
                    uname = properties.getProperty("user");
                }
            }
        }

        Object rs = null;
        try {
            rs = invocation.proceed();
        } catch (Throwable e) {
            log.error("error sql: " + sql, e);
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - time;
            sql = this.replaceContinueSpace(sql);
            // 这个方法的总耗时
            log.info("\n\n ============= \nsql ----> {}\n{}\nuser ----> {}\ncost ----> {}\n ============= \n", sql, getResult(rs.toString()), uname, cost);
        }

        return rs;
    }

    /**
     * 获取结果
     *
     * @param input 输入
     * @return 结果
     */
    private String getResult(String input) {
        return input.substring(input.indexOf("(") + 1, input.lastIndexOf(")"));
    }

    /**
     * 替换连续的空白
     *
     * @param str 字符串
     * @return 替换后的字符串
     */
    private String replaceContinueSpace(String str) {
        StringBuilder builder = new StringBuilder(str.length());
        boolean preSpace = false;
        for (int i = 0, len = str.length(); i < len; i++) {
            char ch = str.charAt(i);
            boolean isSpace = Character.isWhitespace(ch);
            if (preSpace && isSpace) {
                continue;
            }

            if (preSpace) {
                // 前面的是空白字符，当前的不是的
                preSpace = false;
                builder.append(ch);
            } else if (isSpace) {
                // 当前字符为空白字符，前面的那个不是的
                preSpace = true;
                builder.append(" ");
            } else {
                // 前一个和当前字符都非空白字符
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    /**
     * 构建SQL
     *
     * @param statementHandler StatementHandler对象
     * @return SQL语句
     */
    private String buildSql(StatementHandler statementHandler) {
        BoundSql boundSql = statementHandler.getBoundSql();
        Configuration configuration = null;
        if (statementHandler.getParameterHandler() instanceof DefaultParameterHandler) {
            DefaultParameterHandler handler = (DefaultParameterHandler) statementHandler.getParameterHandler();
            configuration = (Configuration) new ReflectionUtils().getFieldVal(false, handler, "configuration");
        } else if (statementHandler.getParameterHandler() instanceof MybatisParameterHandler) {
            MybatisParameterHandler paramHandler = (MybatisParameterHandler) statementHandler.getParameterHandler();
            configuration = ((MappedStatement) new ReflectionUtils().getFieldVal(false, paramHandler, "mappedStatement")).getConfiguration();
        }

        if (configuration == null) {
            return boundSql.getSql();
        }
        return buildSql(boundSql, configuration);
    }

    /**
     * 构建SQL
     *
     * @param boundSql      BoundSql对象
     * @param configuration Configuration对象
     * @return SQL语句
     */
    private String buildSql(BoundSql boundSql, Configuration configuration) {
        String sql = boundSql.getSql();
        //获取参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //没有参数直接返回
        if (CollectionUtils.isEmpty(parameterMappings) || parameterObject == null) {
            return sql;
        }

        MetaObject mo = configuration.newMetaObject(boundSql.getParameterObject());
        for (ParameterMapping parameterMapping : parameterMappings) {
            //只拦截 输出参数
            if (parameterMapping.getMode() == ParameterMode.OUT) {
                continue;
            }
            //参数值
            Object value;
            //获取参数名称
            String propertyName = parameterMapping.getProperty();
            if (boundSql.hasAdditionalParameter(propertyName)) {
                value = boundSql.getAdditionalParameter(propertyName);
            } else if (configuration.getTypeHandlerRegistry().hasTypeHandler(parameterObject.getClass())) {
                //如果是单个值则直接赋值
                value = parameterObject;
            } else {
                value = mo.getValue(propertyName);
            }
            String param = Matcher.quoteReplacement(getParameter(value));
            sql = sql.replaceFirst("\\?", param);
        }
        sql += ";";
        return sql;
    }

    /**
     * 获取参数值
     *
     * @param parameter 参数
     * @return 参数值
     */
    private String getParameter(Object parameter) {
        if (parameter instanceof String) {
            return "'" + parameter + "'";
        } else if (parameter instanceof Date) {
            return "'" + DateUtil.format(DateUtil.DB_FORMAT, ((Date) parameter).getTime()) + "'";
        } else if (parameter instanceof java.util.Date) {
            return "'" + DateUtil.format(DateUtil.DB_FORMAT, ((java.util.Date) parameter).getTime()) + "'";
        }
        return parameter.toString();
    }

    /**
     * 插件方法
     *
     * @param o 对象
     * @return 插件后的对象
     */
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    /**
     * 设置属性
     *
     * @param properties 属性
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
