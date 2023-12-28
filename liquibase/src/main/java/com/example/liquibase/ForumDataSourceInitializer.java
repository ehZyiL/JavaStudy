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
import java.util.Objects;

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
     * 检测一下数据库中表是否存在，若存在则不初始化；
     *
     * @param dataSource
     * @return true 表示需要初始化； false 表示无需初始化
     */
    private boolean needInit(DataSource dataSource) {

        if (autoInitDatabase()) {
            return true; //不存在且已创建
        }

        //已存在的执行逻辑

        // 根据是否存在表来判断是否需要执行sql操作
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        if (!liquibaseEnable) {
            // 非liquibase做数据库版本管理的，根据用户来判断是否有初始化
            List list = jdbcTemplate.queryForList("SELECT table_name FROM information_schema.TABLES where table_name = 'user_info' and table_schema = '" + database + "';");
            return CollectionUtils.isEmpty(list);
        }

        // 对于liquibase做数据版本管控的场景，若使用的不是默认的pai_coding，则需要进行修订
        List<Map<String, Object>> record = jdbcTemplate.queryForList("select * from DATABASECHANGELOG where ID='00000000000020' limit 1;");
        if (CollectionUtils.isEmpty(record)) {
            // 首次启动，需要初始化库表，直接返回
            return true;
        }

        // 非首次启动时，判断记录对应的md5是否准确
        if (Objects.equals(record.get(0).get("MD5SUM"), "8:a1a2d9943b746acf58476ae612c292fc")) {
           jdbcTemplate.update("update DATABASECHANGELOG set MD5SUM='8:bb81b67a5219be64eff22e2929fed540' where ID='00000000000020'");
        }
        return false;
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
