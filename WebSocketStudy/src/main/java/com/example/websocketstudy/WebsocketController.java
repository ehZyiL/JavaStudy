package com.example.websocketstudy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebsocketController {

    @RequestMapping("/news/sys-news-main/outerNewsForIndex")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> outerNewsForIndex() throws Exception {
        // 构建最终返回的Map
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "操作成功！");
        responseData.put("result", null);
        responseData.put("isAlert", 0);
        responseData.put("success",true);
        responseData.put("code", 200);
        responseData.put("exceptionMsg", null);
        responseData.put("exceptionMsgArgs", null);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @RequestMapping("/sync/pageEmpInfo")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getData() throws Exception {
        // 构建content的Map
        Map<String, Object> content = new HashMap<>();
        content.put("persName", "陈震山");
        content.put("operatorNo", "czs001");
        content.put("emplNo", "G00001");
        content.put("orgId", "0000000003");
        content.put("posName", "董事长");
        content.put("dutyName", "行领导");
        content.put("dutyCode", "0101");
        content.put("state", "09");
        content.put("age", 54);
        content.put("gender", "1");
        content.put("phone", "13957110886");
        content.put("orderNew", "A01010101000000011898G00001");

        List<Map<String, Object>> content1 = List.of(content);

        List<Map<String, Object>> dataList = getDataList();

        // 构建returnObject的Map
        Map<String, Object> returnObject = new HashMap<>();
        returnObject.put("current", 1);
        returnObject.put("total", 16603);
        returnObject.put("totalPages", 1107);
        returnObject.put("userData", null);
        returnObject.put("size", 15);
        returnObject.put("content", dataList);

        // 构建最终返回的Map
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("successed", true);
        responseData.put("errors", null);
        responseData.put("returnObject", returnObject);
        responseData.put("exceptionCode", 200);
        responseData.put("exceptionMsg", null);
        responseData.put("exceptionMsgArgs", null);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @RequestMapping("/sync/zzjg")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getZzjg() throws Exception {
        // 构建最终返回的Map
        Map<String, Object> responseData = new HashMap<>();

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    public static List<Map<String, Object>> getDataList() throws Exception {
        // 使用 ClassPathResource 读取文件
        ClassPathResource resource = new ClassPathResource("static/ry.json");

        // 读取文件内容并转换为字符串
        String jsonString;
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            jsonString = FileCopyUtils.copyToString(reader);
        }

        // 使用 Jackson 将 JSON 字符串转换为 Map
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(jsonString);
        JsonNode jsonNodes1 = objectMapper.readValue(jsonString, JsonNode.class);

        return objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {
        });
    }


    @GetMapping("/")
    public String init() {
        return "websocket";
    }

    @GetMapping("/chat")
    @ResponseBody
    public String chat() {
        return "chatRoom";
    }
}

