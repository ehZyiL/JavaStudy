//package com.example.liquibase.schemaInit;
//
//import com.example.liquibase.SpringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.init.DataSourceInitializer;
//import org.springframework.jdbc.datasource.init.DatabasePopulator;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//import org.springframework.util.CollectionUtils;
//
//import javax.sql.DataSource;
//import java.net.URI;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Configuration
//public class DataSourceInit {
//    //需要初始化的表结构
//    @Value("classpath:init_data/init-schema.sql")
//    private Resource initDataSource;
//    //初始化数据
//    @Value("classpath:init_data/init-data.sql")
//    private Resource initData;
//    //数据库名
//    @Value("${database.name}")
//    private String dataBaseName;
//
//    @Bean
//    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
//        final DataSourceInitializer initializer = new DataSourceInitializer();
//        // 设置数据源
//        initializer.setDataSource(dataSource);
//        // 设置数据库Populator
//        initializer.setDatabasePopulator(databasePopulator());
//        // true表示需要执行，false表示不需要初始化
//        initializer.setEnabled(needInit(dataSource));
//        return initializer;
//    }
//
//    /**
//     * 判断是否需要初始化
//     *
//     * @param dataSource
//     * @return
//     */
//    private boolean needInit(DataSource dataSource) {
//        if (autoInitDatabase()) {
//            return true;
//        }
//        // 根据是否存在表来判断是否需要执行sql操作
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT table_name FROM information_schema.TABLES where table_name = 'user' and table_schema = '" + dataBaseName + "'");
//        boolean init = CollectionUtils.isEmpty(list);
//        if (init) {
//            log.info("库表不存在，执行建表及数据初始化");
//        } else {
//            log.info("表结构已存在，无需初始化");
//        }
//        return init;
//    }
//
//    /**
//     * 数据库不存在时，尝试创建数据库
//     *
//     * @return
//     */
//    private boolean autoInitDatabase() {
//        // 查询失败，可能是数据库不存在，尝试创建数据库之后再次测试
//        //jdbc:mysql://127.0.0.1:3306/story?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
//        //mysql://127.0.0.1:3306/story?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
//        URI url = URI.create(SpringUtil.getConfig("spring.datasource.url").substring(5));
//        String uname = SpringUtil.getConfig("spring.datasource.username");
//        String pwd = SpringUtil.getConfig("spring.datasource.password");
//
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + url.getHost() + ":" + url.getPort() +
//                "?useUnicode=true&characterEncoding=UTF-8&useSSL=false", uname, pwd);
//             Statement statement = connection.createStatement()) {
//
//            ResultSet set = statement.executeQuery("select  schema_name from information_schema.schemata where schema_name='" + dataBaseName + "'");
//            if (!set.next()) {
//                String createDb = "create database if not exists " + dataBaseName;
//                connection.setAutoCommit(false);
//                statement.execute(createDb);
//                connection.commit();
//                log.info("创建数据库（{}）成功", createDb);
//                if (set.isClosed()) {
//                    set.close();
//                }
//                return true;
//            }
//            set.close();
//            log.info("数据库已存在，无需初始化");
//            return false;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 设置数据源
//     * <span>创建了一个ResourceDatabasePopulator对象，并添加了两个SQL脚本文件到其中，用分号作为分隔符。最后将这个populator对象返回。</span>
//     *
//     * @return
//     */
//    private DatabasePopulator databasePopulator() {
//        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScripts(initDataSource);
//        populator.addScripts(initData);
//        populator.setSeparator(";");
//        return populator;
//    }
//
//
//}