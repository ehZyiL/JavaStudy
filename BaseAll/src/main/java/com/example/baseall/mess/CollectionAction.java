package com.example.baseall.mess;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * @author ehyzil
 * @Description
 * @create 2023-10-2023/10/24-15:27
 */
public class CollectionAction {


    private static void Map(HashMap map) {
        boolean ch = map.containsKey("ch");
        if (ch) {
            HashMap o = (HashMap) map.get("ch");
            boolean isOpen = o.containsKey("open");
            if (isOpen) {
                String open = o.get("open") + "";
                if ("true".equals(open)) {
                    map.put("id", "444");
                }
            }
            if (o.containsKey("ch")) {
                Map(o);
            }

        }
    }

    public static void main(String[] args) {


//        ArrayList<String> dateList = new ArrayList<String>();
//        dateList.add("2021121101");
//        dateList.add("2021121102");
//        dateList.add("2021121105");
//        dateList.add("2021061104");
//        dateList.add("2021081103");
//        dateList.add("2021071101");
//        dateList.add("2021101106");
//        dateList.add("2021121105");
//
//        Map<String, List<String>> collect = dateList.stream().collect(Collectors.groupingBy(item -> item.substring(0,6)));
//       final ArrayList<String> ids = new ArrayList<>();
//        for (Map.Entry<String, List<String>> stringListEntry : collect.entrySet()) {
//
//            System.out.println(stringListEntry.getKey());
//
//            stringListEntry.getValue().forEach(item->ids.add(item));
//            System.out.println(ids);
//
//            ids.clear();
//        }
//        System.out.println(LocalDate.now());
//
//
//        System.out.println("--------------------------------------");


//        System.out.println(collect);

//        System.out.println("时间列表:");
//        for (String date : dateList) {
//            System.out.println(date);
//        }


//        List<Map<String, String>> uploadDatas = new ArrayList<>();
//
//
//        Map<String, String> singleRow;
//        Map<String, String> hashMap;
//        for (int i = 0; i < 5; i++) {
//            singleRow = new HashMap<>();
//            singleRow = new HashMap<>();
//            hashMap = new HashMap<>();
//
//            singleRow.put("rowNum", String.valueOf(i));
//            String province1 = getProvinceId();
//            String province2 = getProvinceId();
//            String provinceId1 = getUUID();
//            String provinceId2 = getUUID();
//            singleRow.put("province1", province1);
//            singleRow.put("province2", province2);
//            singleRow.put("provinceId1", provinceId1);
//            singleRow.put("provinceId2", provinceId2);
//            singleRow.put("message1", "");
//            singleRow.put("message2", "");
//            singleRow.put("message2", "");
//
//            uploadDatas.add(singleRow);
//        }
//        System.out.println(uploadDatas);
//        System.out.println();
//        System.out.println();
//
//        Map<String, Map<String, String>> map = new HashMap<>();
//
//        for (Map<String, String> item : uploadDatas) {
//            if (map.containsKey(item.get("province1"))) {
//                Map<String, String> province1 = map.get(item.get("province1"));
//                province1.put(item.get("rowNum") + "~" + item.get("provinceId1"), item.get("provinceId1"));
//            } else {
//                HashMap<String, String> stringStringHashMap = new HashMap<>();
//                stringStringHashMap.put(item.get("rowNum") + "~" + item.get("provinceId1"), item.get("provinceId1"));
//                map.put(item.get("province1"), stringStringHashMap);
//            }
//
//            if (map.containsKey(item.get("province2"))) {
//                Map<String, String> province1 = map.get(item.get("province2"));
//                province1.put(item.get("rowNum") + "~" + item.get("provinceId2"), item.get("provinceId2"));
//            } else {
//                HashMap<String, String> stringStringHashMap = new HashMap<>();
//                stringStringHashMap.put(item.get("rowNum") + "~" + item.get("provinceId2"), item.get("provinceId2"));
//                map.put(item.get("province2"), stringStringHashMap);
//            }
//
//        }
//
//        System.out.println();
//        System.out.println("map:" + map);
//        System.out.println();
//
//        StringBuffer callIds;
//        Map<String, String> queryMap = new HashMap<>();
//        for (Map.Entry<String, Map<String, String>> stringMapEntry : map.entrySet()) {
//            String key = stringMapEntry.getKey();
//            Map<String, String> value = stringMapEntry.getValue();
//
//            callIds = new StringBuffer();
//
//            for (String s : value.values()) {
//                callIds.append(s + ",");
//            }
//            queryMap.put(key, callIds.toString().substring(0, callIds.lastIndexOf(",")));
//        }
//        System.out.println("====================queryMap=====================");
//        System.out.println(queryMap);
//
//        System.out.println();
//        for (Map<String, String> uploadData : uploadDatas) {
////            System.out.println(uploadData);
//
//            String message = uploadData.get("message1");
//            if (message != null && !message.equalsIgnoreCase("")) continue;
//            String province1 = uploadData.get("province1");
//
//            Map<String, String> stringStringMap = map.get(province1);
//            String s = stringStringMap.get(uploadData.get("rowNum") + "~" + uploadData.get("provinceId1"));
//            if (s != null) {
//                uploadData.put("message1", "1");
//            }
//
//        }
//        System.out.println(uploadDatas);
//        System.out.println();   System.out.println();   System.out.println();
//
//        ArrayList< String> ids;
//        for (String provinceId : queryMap.keySet()) {
//            String[] touchIdArr = queryMap.get(provinceId).split(",");
//            ids = new ArrayList<String>(touchIdArr.length);
//
//            Collections.addAll(ids, touchIdArr);
//            System.out.println(ids);
//        }
////        List<Map<String, String>> uploadDatas = new ArrayList<>();
//
//        Collections.sort(uploadDatas,new Comparator<Map<String, String>>() {
//            @Override
//            public int compare(Map<String, String> o1, Map<String, String> o2) {
//                return o1.get("rowNum").compareTo(o2.get("rowNum"));
//            }
//        });
//
//        System.out.println(uploadDatas);
//
//        for (Map<String, String> uploadData : uploadDatas) {
//            Map<String, String> collect = uploadData.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
//        }
//
//        for (Map<String, String> uploadData : uploadDatas) {
//             uploadData.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()));
//        }

    }


    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String getProvinceId() {
        int i = new Random().nextInt(10);
        if (i / 2 == 1) {
            return "99810086";
        } else if (i / 2 == 2) {
            return "99710086";
        } else if (i / 2 == 3) {
            return "99610086";
        }

        return "99910086";
    }

}
