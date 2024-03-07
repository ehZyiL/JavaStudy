package com.example.baseall.mess;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.baseall.BaseAllApplication;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2024/2/23
 * @Description
 * @Author ehzyil
 */
public class question {

    public static void main(String[] args) throws IOException {

        JSONObject getjson = getjson();
        JSONArray beansArr = getjson.getJSONArray("beans");
        List<Map<String, String>> beans = json2List(beansArr);


        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n\n\n\n");
            String str = sc.nextLine().trim();
            List<Map<String, String>> collect = beans.stream().filter(map -> {
                if (StringUtils.isBlank(str)){return false;}
                return map.get("title").toString().contains(str);
            }).collect(Collectors.toList());

            collect.forEach(item -> {

                String formattedOutput = String.format("题目：%s\n选项：%s\n答案：%s", item.get("title"), item.get("option"), item.get("answer"));
                // 打印格式化后的输出
                System.out.println(formattedOutput);
            });

        }

    }

    public static JSONObject getjson() throws IOException {
        // 使用 ClassPathResource 工具类获取类路径下的 problemType.json 文件
        ClassPathResource resource = new ClassPathResource("data/data.json");
        // 使用 IOUtils 工具类将文件内容读取为字符串
        String json = IOUtils.toString(resource.getInputStream(), "UTF-8");
        // 使用 fastjson 库将字符串解析为 JSONObject 对象
        JSONObject jsonObject = JSON.parseObject(json);
        // 返回解析后的 JSONObject 对象
        return jsonObject;
    }

    public static List<Map<String, String>> json2List(JSONArray array) {

        // 创建一个空的列表
        List<Map<String, String>> list = new ArrayList<>();
        // 遍历json数组中的每个对象
        for (Object obj : array) {
            // 将对象转换为JSONObject
            JSONObject object = (JSONObject) obj;
            // 创建一个空的HashMap
            HashMap<String, String> map = new HashMap<>();
            // 获取对象中名为"children"的数组
            // 将对象中的"name"、"id"、"value"放入map中
            map.put("title", object.getString("title"));
            map.put("option", object.getString("option"));
            map.put("answer", object.getString("answer"));
            // 将map添加到列表中
            list.add(map);
        }
        // 返回最终的列表
        return list;
    }
}
