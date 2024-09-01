package com.example.baseall.graphdata;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 评标常量
 */
public class Constants_pb {

    // 供应商关联关系相关常量，用于修改图谱的节点样式
    public static final String XM_QYGLGX_DX = "吊销";
    public static final String XM_QYGLGX_ZX = "注销";
    public static final String XM_QYGLGX_DX_WZX = "吊销, 未注销";
    public static final String XM_QYGLGX_DX_YZX = "吊销, 已注销";
    public static final String XM_QYGLGX_DX_HUMAN= "Human";
    public static final String XM_QYGLGX_DX_COMPANY = "Company";

    public static final String XM_QYGLGX_NODE_NONE_GREY= "#FF0000";
    public static final String XM_QYGLGX_NODE_GYS_RED= "#d62728";
    public static final String XM_QYGLGX_NODE_HUMAN_BLUE= "#d62728";
    public static final String XM_QYGLGX_NODE_GLGYS_GREEN= "#047c4a";

    public static final Map<String, String> XM_QYGLGX_MAP = new LinkedHashMap<>();

    static {
        XM_QYGLGX_MAP.put(XM_QYGLGX_DX, XM_QYGLGX_NODE_NONE_GREY);
        XM_QYGLGX_MAP.put(XM_QYGLGX_ZX, XM_QYGLGX_NODE_NONE_GREY);
        XM_QYGLGX_MAP.put(XM_QYGLGX_DX_WZX, XM_QYGLGX_NODE_NONE_GREY);
        XM_QYGLGX_MAP.put(XM_QYGLGX_DX_YZX, XM_QYGLGX_NODE_NONE_GREY);
        XM_QYGLGX_MAP.put(XM_QYGLGX_DX_HUMAN, XM_QYGLGX_NODE_GLGYS_GREEN);
        XM_QYGLGX_MAP.put(XM_QYGLGX_DX_COMPANY, XM_QYGLGX_NODE_GLGYS_GREEN);
    }

    public static String getXM_QYGLGX_MAP(String label) {
        return XM_QYGLGX_MAP.get(label);
    }

}

