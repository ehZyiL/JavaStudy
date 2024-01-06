package com.example.senstive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SenstiveApplication {
    //        @Bean
//        public SqlStateInterceptor sqlStateInterceptor() {
//            return new SqlStateInterceptor();
//        }
    public static void main(String[] args) {
        SpringApplication.run(SenstiveApplication.class, args);
    }

}
