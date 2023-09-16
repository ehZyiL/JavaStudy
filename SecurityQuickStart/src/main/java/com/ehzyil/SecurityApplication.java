package com.ehzyil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ehyzil
 * @Description
 * @create 2023-09-2023/9/15-20:37
 */
@SpringBootApplication
@MapperScan("com.ehzyil.mapper")
public class SecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SecurityApplication.class);
        System.out.println(run);
    }
}
