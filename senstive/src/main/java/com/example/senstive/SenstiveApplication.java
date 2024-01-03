package com.example.senstive;

import com.example.senstive.dal.SqlStateInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SenstiveApplication {
        @Bean
        public SqlStateInterceptor sqlStateInterceptor() {
            return new SqlStateInterceptor();
        }
    public static void main(String[] args) {
        SpringApplication.run(SenstiveApplication.class, args);
    }

}
