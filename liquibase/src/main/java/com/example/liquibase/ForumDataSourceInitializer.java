package com.example.liquibase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @author ehyzil
 * @Description 数据库表自动初始化
 * @create 2023-12-2023/12/28-12:22
 */

@Slf4j
@Configuration
public class ForumDataSourceInitializer {

    @Value("${database.name}")
    private String database; // 从配置文件中读取数据库名称

    @Value("${spring.liquibase.enabled:true}")
    private Boolean liquibaseEnable; // 从配置文件中读取是否使用Liquibase管理数据库的配置

    @Value("${spring.liquibase.change-log}")
    private String liquibaseChangeLog; // 从配置文件中读取Liquibase的change-log配置

    /**
     * 初始化数据源，返回DataSourceInitializer Bean<br>
     * <span>用于初始化数据源，返回一个DataSourceInitializer的Bean。
     * 该方法的参数是一个DataSource对象，表示数据源。在方法内部，创建了一个DataSourceInitializer对象，
     * 设置数据源为传入的参数，并根据是否需要初始化数据库设置启用状态和数据库结构初始化的Populator。 </span>
     *
     * @param dataSource 数据源
     * @return DataSourceInitializer
     */
    @Bean
    public org.springframework.jdbc.datasource.init.DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final org.springframework.jdbc.datasource.init.DataSourceInitializer initializer = new org.springframework.jdbc.datasource.init.DataSourceInitializer();
        // 设置数据源
        initializer.setDataSource(dataSource);
        boolean enable = needInit(dataSource);
        initializer.setEnabled(enable);
        initializer.setDatabasePopulator(databasePopulator(enable));
        return initializer;
    }

    /**
     * 创建DatabasePopulator对象，用于数据库结构初始化<br>
     * <span>databasePopulator方法用于创建DatabasePopulator对象，用于数据库结构初始化。该方法的参数是一个是否需要初始化的标志，根据该标志进行不同的操作。
     * 如果不需要初始化且不使用Liquibase管理数据库，则创建一个ResourceDatabasePopulator对象，并通过addScripts方法添加需要执行的sql脚本，
     * 通过setSeparator方法设置sql语句的分隔符。</span>
     *
     * @param initEnable 是否需要初始化
     * @return DatabasePopulator
     */
    private DatabasePopulator databasePopulator(boolean initEnable) {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        if (!liquibaseEnable && initEnable) {
            // fixme: 首次启动时, 对于不支持liquibase的数据库，如mariadb，采用主动初始化
            // fixme 这种方式不支持后续动态的数据表结构更新、数据变更
            populator.addScripts(XmlParserUtils.loadDbChangeSetResources(liquibaseChangeLog).toArray(new ClassPathResource[]{}));
            populator.setSeparator(";");
            log.info("非Liquibase管理数据库，请手动执行数据库表初始化!");
        }
        return populator;
    }

    /**
     * 判断是否需要初始化数据库<br>
     * * <span>
     * needInit方法用于判断是否需要初始化数据库。该方法的参数是一个DataSource对象，表示数据源。在方法内部，先判断是否需要自动创建数据库，如果需要则返回true。
     * 然后通过JdbcTemplate查询是否存在表，如果不存在则需要初始化数据库，并打印相应日志，否则不需要初始化并打印相应日志。
     * </span>
     *
     * @param dataSource 数据源
     * @return 是否需要初始化
     */
    private boolean needInit(DataSource dataSource) {
        // 创建数据库
        if (autoInitDatabase()) {
            return true;
        }
        // 根据是否存在表来判断是否需要执行sql操作
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT table_name FROM information_schema.TABLES where table_name = 'user' and table_schema = '" + database + "'");
        boolean init = CollectionUtils.isEmpty(list);
        if (init) {
            log.info("库表不存在，即将执行建表及数据初始化");
        } else {
            log.info("表结构已存在，无需初始化");
        }
        return init;
    }

    /**
     * 数据库不存在时，尝试创建数据库<br>
     * <span>
     * autoInitDatabase方法用于自动创建数据库。在方法内部，首先从配置文件中获取数据库连接URL、用户名和密码，并通过DriverManager创建数据库连接和Statement对象。
     * 然后通过执行查询语句判断数据库是否存在，如果不存在则执行创建数据库的sql语句，并打印相应日志。
     * </span>
     *
     * @return 是否成功创建数据库
     */
    private boolean autoInitDatabase() {
        // 查询失败，可能是数据库不存在，尝试创建数据库之后再次测试
        //jdbc:mysql://127.0.0.1:3306/story?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
        //mysql://127.0.0.1:3306/story?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
        URI url = URI.create(SpringUtil.getConfig("spring.datasource.url").substring(5));
        String uname = SpringUtil.getConfig("spring.datasource.username");
        String pwd = SpringUtil.getConfig("spring.datasource.password");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + url.getHost() + ":" + url.getPort() + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false", uname, pwd); Statement statement = connection.createStatement()) {

            ResultSet set = statement.executeQuery("select  schema_name from information_schema.schemata where schema_name='" + database + "'");
            if (!set.next()) {
                String createDb = "create database if not exists " + database;
                connection.setAutoCommit(false);
                statement.execute(createDb);
                connection.commit();
                log.info("创建数据库（{}）成功", createDb);
                if (set.isClosed()) {
                    set.close();
                }
                return true;
            }
            set.close();
            log.info("数据库已存在，无需初始化");
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
