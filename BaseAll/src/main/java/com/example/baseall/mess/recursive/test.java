package com.example.baseall.mess.recursive;

import java.util.*;

/**
 * @Date 2024/2/23
 * @Description
 * @Author ehzyil
 */
public class test {
    public static void main(String[] args) {

        List<Map<String,Object>> test = new ArrayList<Map<String,Object>>();

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("name", "May");
        map.put("age", "22");
        map.put("from", "Wuhan");
        map.put("job", "programmer");
        test.add(map);

        Map<String,Object> map1 = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        Map<String,Object> map3 = new HashMap<String,Object>();
        Map<String,Object> map4 = new HashMap<String,Object>();
        List<Map<String,Object>> test1 = new ArrayList<Map<String,Object>>();
        test1.add(map1);
        test.add(map1);
        test.add(map2);
        test.add(map3);
        test.add(map4);


        System.out.println(test);
        test.removeAll(test1);

        System.out.println(test);

    }
}
