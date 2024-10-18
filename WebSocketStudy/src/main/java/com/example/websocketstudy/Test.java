package com.example.websocketstudy;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringJoiner;

public class Test {
    public static void main(String[] args) throws Exception {
        String requestUrl1 = "http://localhost:8080/hzyh/api/data/ry/pullRy";
        String requestUrl2 = "http://localhost:8080/hzyh/api/data/ry/syncRy";

        String requestUrl3 = "http://localhost:8080/hzyh/api/data/zzjg/pullOrg";
        String requestUrl4 = "http://localhost:8080/hzyh/api/data/zzjg/syncOrg";

        String s = sendRequest(requestUrl2, "POST", null);
    }

    public static String sendRequest(String requestUrl, String method, Map<String, Object> params) throws Exception {
        // 创建HttpClient对象，并设置超时
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(10000)
                        .setSocketTimeout(10000)
                        .build())
                .build()) {

            CloseableHttpResponse response = null;

            try {
                if ("GET".equalsIgnoreCase(method)) {
                    // 如果是GET请求，将Map参数拼接到URL中
                    if (params != null && !params.isEmpty()) {
                        StringJoiner sj = new StringJoiner("&");
                        for (Map.Entry<String, Object> entry : params.entrySet()) {
                            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                        }
                        requestUrl += "?" + sj.toString();
                    }
                    // 创建GET请求
                    HttpGet httpGet = new HttpGet(requestUrl);
                    response = httpClient.execute(httpGet);

                } else if ("POST".equalsIgnoreCase(method)) {
                    // 如果是POST请求，将Map参数转换为JSON字符串作为请求体
                    HttpPost httpPost = new HttpPost(requestUrl);
                    httpPost.setHeader("Content-Type", "application/json");

                    if (params != null && !params.isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(params);
                        httpPost.setEntity(new StringEntity(json, "UTF-8"));
                    }
                    response = httpClient.execute(httpPost);
                } else {
                    throw new UnsupportedOperationException("Unsupported request method: " + method);
                }

                // 检查响应码
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new IOException("HTTP error code: " + statusCode);
                }

                // 获取响应字符串
                return EntityUtils.toString(response.getEntity(), "UTF-8");

            } finally {
                if (response != null) {
                    response.close();
                }
            }

        } catch (IOException e) {
            // 记录并重新抛出IO异常
            System.err.println("HTTP请求失败: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            // 记录并重新抛出通用异常
            System.err.println("发生错误: " + e.getMessage());
            throw e;
        }
    }
}
