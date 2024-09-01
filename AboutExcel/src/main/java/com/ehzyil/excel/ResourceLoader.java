package com.ehzyil.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceLoader {

    public static InputStream getResourceAsStream(String resourceName) {
        // 获取当前线程的 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 如果当前线程的 ClassLoader 为空，则使用当前类的 ClassLoader
        if (classLoader == null) {
            classLoader = ResourceLoader.class.getClassLoader();
        }
        // 使用 ClassLoader 加载资源文件
        return classLoader.getResourceAsStream(resourceName);
    }

    public static void main(String[] args) throws IOException {
        // 读取 resources 目录下的 test.txt 文件
        InputStream inputStream = getResourceAsStream("新建文本文档.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        inputStream.close();
    }
}
