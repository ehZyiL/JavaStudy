package com.example.baseall.graphdata;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


public class GetDataUtil {
    public static JSONObject getProblemType() throws IOException {
        // 使用 ClassPathResource 工具类获取类路径下的 problemType.json 文件
        ClassPathResource resource = new ClassPathResource("data/graphdata/gxgx.json");
        // 使用 IOUtils 工具类将文件内容读取为字符串
        String json = IOUtils.toString(resource.getInputStream(), "UTF-8");
        // 使用 fastjson 库将字符串解析为 JSONObject 对象
        JSONObject jsonObject = JSON.parseObject(json);
        // 返回解析后的 JSONObject 对象
        return jsonObject;
    }

    public static JSONObject getProblemType(String node) throws IOException {
        // 使用 ClassPathResource 工具类获取类路径下的 problemType.json 文件
        ClassPathResource resource = new ClassPathResource("data/graphdata/" + node + ".json");
        // 使用 IOUtils 工具类将文件内容读取为字符串
        String json = IOUtils.toString(resource.getInputStream(), "UTF-8");
        // 使用 fastjson 库将字符串解析为 JSONObject 对象
        JSONObject jsonObject = JSON.parseObject(json);
        // 返回解析后的 JSONObject 对象
        return jsonObject;
    }
}
