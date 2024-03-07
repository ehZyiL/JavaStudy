package com.example.baseall;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class BaseAllApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BaseAllApplication.class, args);

    }
}