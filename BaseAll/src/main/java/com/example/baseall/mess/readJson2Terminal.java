package com.example.baseall.mess;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * @Date 2024/1/13
 * @Description
 * @Author ehzyil
 */
public class readJson2Terminal {


    public static void main(String[] args) throws IOException {


    }

    public static JSONObject getProblemType() throws IOException {
        // 使用 ClassPathResource 工具类获取类路径下的 problemType.json 文件
        ClassPathResource resource = new ClassPathResource("data/problemType.json");
        // 使用 IOUtils 工具类将文件内容读取为字符串
        String json = IOUtils.toString(resource.getInputStream(), "UTF-8");
        // 使用 fastjson 库将字符串解析为 JSONObject 对象
        JSONObject jsonObject = JSON.parseObject(json);
        // 返回解析后的 JSONObject 对象
        return jsonObject;
    }


    public static List<Map<String, Object>> json2List(JSONArray array) {
        // 创建一个空的列表
        List<Map<String, Object>> list = new ArrayList<>();
        // 遍历json数组中的每个对象
        for (Object obj : array) {
            // 将对象转换为JSONObject
            JSONObject object = (JSONObject) obj;
            // 创建一个空的HashMap
            HashMap<String, Object> map = new HashMap<>();
            // 获取对象中名为"children"的数组
            JSONArray children = object.getJSONArray("children");
            // 如果"children"不为空，则将其转换为列表
            if (children != null) {
                List<Map<String, Object>> childrenList = json2List(children);
                map.put("children", childrenList);
            }
            // 将对象中的"name"、"id"、"value"放入map中
            map.put("name", object.getString("name"));
            map.put("id", object.getString("id"));
            map.put("value", object.getString("value"));
            map.put("mark", object.getString("mark"));
            // 将map添加到列表中
            list.add(map);
        }
        // 返回最终的列表
        return list;
    }

    public static StringBuffer recursiveForAllName(List<Map<String, Object>> list, String target, String sign, StringBuffer result) {
        if (CollectionUtils.isEmpty(list)) {
            return result.append(target);
        }
        for (Map<String, Object> map : list) {
            List<Map<String, Object>> children = (List<Map<String, Object>>) map.get("children");
            if (map.get("children") != null) {
                recursiveForAllName(children, target, sign, result);
            }
            if (StringUtils.isNotBlank(result)) {
                result.insert(0, map.get("name") + "_");
                break;
            } else {
                String name = map.get("name").toString();
                String mark = Optional.ofNullable(map.get("mark")).orElse("").toString();
                if (target.contains(name)&&sign.contains(mark)) {
                    result.insert(0, name);
                    break;
                }
            }
        }
        return result;
    }

}
