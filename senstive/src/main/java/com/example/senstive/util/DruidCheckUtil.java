package com.example.senstive.util;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.springframework.util.ClassUtils;

public class DruidCheckUtil {

    /**
     * 判断是否包含durid相关的数据包
     *
     * @return
     */
    public static boolean hasDuridPkg() {
        return ClassUtils.isPresent("com.alibaba.druid.pool.DruidDataSource", DataSourceConfig.class.getClassLoader());
    }

}