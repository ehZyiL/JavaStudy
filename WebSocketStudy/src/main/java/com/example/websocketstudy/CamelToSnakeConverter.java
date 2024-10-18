package com.example.websocketstudy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelToSnakeConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertJsonArray(String jsonArray) throws Exception {
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonArray);
        ArrayNode convertedArray = convertArrayNode(arrayNode);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(convertedArray);
    }

    private static ArrayNode convertArrayNode(ArrayNode arrayNode) {
        ArrayNode convertedArray = objectMapper.createArrayNode();
        for (int i = 0; i < arrayNode.size(); i++) {
            if (arrayNode.get(i).isObject()) {
                convertedArray.add(convertObjectNode((ObjectNode) arrayNode.get(i)));
            } else if (arrayNode.get(i).isArray()) {
                convertedArray.add(convertArrayNode((ArrayNode) arrayNode.get(i)));
            } else {
                convertedArray.add(arrayNode.get(i));
            }
        }
        return convertedArray;
    }

    private static ObjectNode convertObjectNode(ObjectNode objectNode) {
        ObjectNode convertedObject = objectMapper.createObjectNode();
        Iterator<String> fieldNames = objectNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            String convertedFieldName = camelToSnakeCase(fieldName);
            if (objectNode.get(fieldName).isObject()) {
                convertedObject.set(convertedFieldName, convertObjectNode((ObjectNode) objectNode.get(fieldName)));
            } else if (objectNode.get(fieldName).isArray()) {
                convertedObject.set(convertedFieldName, convertArrayNode((ArrayNode) objectNode.get(fieldName)));
            } else {
                convertedObject.set(convertedFieldName, objectNode.get(fieldName));
            }
        }
        return convertedObject;
    }

    private static String camelToSnakeCase(String str) {
        Pattern pattern = Pattern.compile("(?<!^)(?=[A-Z])");
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("_").toLowerCase();
    }

    public static void main(String[] args) {
        try {
            String jsonArray = "[{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30},{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"age\":25}]";
            String result = convertJsonArray(jsonArray);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}