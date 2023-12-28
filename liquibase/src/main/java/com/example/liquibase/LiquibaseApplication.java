package com.example.liquibase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
@SpringBootApplication
public class LiquibaseApplication implements ApplicationRunner {

//    @Value("${spring.liquibase.change-log}")
//    private String liquibaseChangeLog;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApplication.class);
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
            //schemaInit
//        List list = jdbcTemplate.queryForList("select * from user limit 2");
//        log.info("启动成功，初始化数据: {}\n{}", list.size(), list);



    }
}
