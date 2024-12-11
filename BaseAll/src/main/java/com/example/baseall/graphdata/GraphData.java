package com.example.baseall.graphdata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class GraphData {

    /**
     * 根据给定的起始节点，在邻接列表中找到所有可能的路径。
     *
     * @param startNode     起始节点的ID
     * @param adjacencyList 邻接列表，表示节点之间的关系
     * @return 包含所有路径的列表，每条路径是一个节点ID的列表
     */
    public static List<List<String>> findAllPaths(String startNode, Map<String, Set<String>> adjacencyList) {
        Set<List<String>> allPaths = new HashSet<>();
        dfs(startNode, startNode, new ArrayList<String>(), allPaths, new HashSet<String>(), adjacencyList);
        return new ArrayList<>(allPaths);
    }

    /**
     * 深度优先搜索算法，用于递归查找所有可能的路径。
     *
     * @param currentNode   当前节点ID
     * @param currentPath   当前路径
     * @param allPaths      保存所有路径的列表
     * @param visited       已访问的节点集合，防止循环
     * @param adjacencyList 邻接列表
     */
    private static void dfs(String currentNode, String parentNode, List<String> currentPath, Set<List<String>> allPaths, Set<String> visited, Map<String, Set<String>> adjacencyList) {
        currentPath.add(currentNode);
        visited.add(currentNode);

        // 将当前路径的长度大于2的部分分解成长度为2的子路径并添加到allPaths
        if (currentPath.size() >= 2) {
            for (int i = 0; i < currentPath.size() - 1; i++) {
                List<String> pathPair = new ArrayList<>(currentPath.subList(i, i + 2));
                allPaths.add(pathPair);
            }
        }

        // 遍历当前节点的邻居节点，如果未访问且不是父节点则继续递归
        adjacencyList.getOrDefault(currentNode, Collections.emptySet()).stream()
                .filter(neighbor -> !neighbor.equals(parentNode) && !visited.contains(neighbor))
                .forEach(neighbor -> dfs(neighbor, currentNode, currentPath, allPaths, visited, adjacencyList));

        // 回溯：移除当前路径中的最后一个节点（但不要移除visited中的节点）
        currentPath.remove(currentPath.size() - 1);
        // 注意：这里不移除visited集合中的currentNode
    }


    /**
     * 找出两组路径中具有相同起点的路径。
     * <p>
     * 该方法接收两组路径（每组路径为一个节点ID的列表的列表），并返回所有具有相同起点的路径。
     * 具体实现方式如下：
     * 1. 首先，提取每组路径中每个路径的起点（即路径列表中的第一个节点）。
     * 2. 找出两个路径集合中起点相同的路径（公共起点）。
     * 3. 最后，返回所有起点相同的路径，包含来自两组路径中的路径。
     *
     * @param paths1 第一组路径，路径以节点ID列表的形式表示。
     * @param paths2 第二组路径，路径以节点ID列表的形式表示。
     * @return 具有相同起点的路径列表，其中每个路径为节点ID的列表。
     */
    public static List<List<String>> findCommonPaths(List<List<String>> paths1, List<List<String>> paths2) {
        // 提取paths1中所有路径的起点，并存储到一个Set集合中
        Set<String> commonStartPoints = paths1.stream()
                .map(path -> path.get(0)) // 获取每条路径的第一个节点，即起点
                .collect(Collectors.toSet());

        // 取paths2中路径的起点，并与paths1的起点集合取交集，保留共同的起点
        commonStartPoints.retainAll(paths2.stream()
                .map(path -> path.get(0)) // 获取paths2中每条路径的起点
                .collect(Collectors.toSet()));

        // 从两个路径集合中筛选出起点属于commonStartPoints的路径，并合并成一个列表返回
        return Stream.concat(paths1.stream(), paths2.stream())
                .filter(path -> commonStartPoints.contains(path.get(0))) // 过滤出起点在共同起点集合中的路径
                .collect(Collectors.toList());
    }

    /**
     * 将路径拆分成节点对的列表。
     *
     * @param paths 要拆分的路径列表
     * @return 拆分后的节点对列表
     */
    public static List<List<String>> splitPaths(List<List<String>> paths) {
        return paths.stream()
                .filter(path -> path.size() > 1)
                .flatMap(path -> IntStream.range(0, path.size() - 1)
                        .mapToObj(i -> Arrays.asList(path.get(i), path.get(i + 1))))
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 获取所有路径中唯一的节点ID。
     *
     * @param allCommonPaths 所有路径的列表
     * @return 唯一节点ID的列表
     */
    public static List<String> getUniqueNodeIds(List<List<List<String>>> allCommonPaths) {
        return allCommonPaths.stream()
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 从数据集中删除与过滤节点无关的数据。
     *
     * @param filterNodes         需要保留的节点ID列表
     * @param relationshipsArrays 关系数据的JSON数组
     * @param nodesArrays         节点数据的JSON数组
     */
    private static void deleteUnvailableData(List<String> filterNodes, JSONArray relationshipsArrays, JSONArray nodesArrays) {

        // 从relationships中删除与要删除节点相关的关联关系
        for (int i = relationshipsArrays.size() - 1; i >= 0; i--) {
            JSONObject relationship = relationshipsArrays.getJSONObject(i);
            String startNode = relationship.getString("startNode");
            String endNode = relationship.getString("endNode");
            if (!filterNodes.contains(startNode) || !filterNodes.contains(endNode)) {
                relationshipsArrays.remove(i);
            }
        }

        // 从nodes中删除要删除的节点
        for (int i = nodesArrays.size() - 1; i >= 0; i--) {
            JSONObject node = nodesArrays.getJSONObject(i);
            String nodeId = node.getString("id");
            if (!filterNodes.contains(nodeId)) {
                nodesArrays.remove(i);
            }
        }
    }



    public static void main(String[] args) throws IOException {
        JSONArray relationshipsArrays = new JSONArray();
        JSONArray nodesArrays = new JSONArray();
        // 指定节点
        List<String> targetNodes = Arrays.asList("18692064", "571195367", "7199625");

        for (int i = 0; i < targetNodes.size(); i++) {
            JSONObject result = GetDataUtil.getProblemType(String.valueOf(i + 1));
            relationshipsArrays.addAll( result.getJSONArray("relationships"));
            nodesArrays.addAll(result.getJSONArray("nodes"));
        }

        List<JSONObject> nodeList = new ArrayList<>();
        List<JSONObject> relationshipList = new ArrayList<>();

        handleRawShowData(relationshipsArrays, nodesArrays, targetNodes);

        nodeList.addAll(removeDuplicates(nodesArrays, "id"));
        relationshipList.addAll(removeDuplicates(relationshipsArrays, "startNode", "endNode", "type"));

        deleteSingleData(nodeList, relationshipList);
        handleShowData(targetNodes, nodeList, relationshipList);


        System.out.println(JSON.toJSONString(nodeList));
        System.out.println();
        System.out.println(JSON.toJSONString(relationshipList));
    }

    /**
     * 删除单个节点的供
     *
     * @param nodeList
     * @param relationshipList
     */
    private static void deleteSingleData(List<JSONObject> nodeList, List<JSONObject> relationshipList) {
        // 使用HashMap统计每个节点的关联关系数量
        Map<String, Integer> nodeRelationshipCount = new HashMap<>();
        for (JSONObject object : relationshipList) {
            String startNode = object.getString("startNode");
            String endNode = object.getString("endNode");
            // 统计startNode的关联关系数量
            nodeRelationshipCount.put(startNode, nodeRelationshipCount.getOrDefault(startNode, 0) + 1);
            // 统计endNode的关联关系数量
            nodeRelationshipCount.put(endNode, nodeRelationshipCount.getOrDefault(endNode, 0) + 1);
        }

        // 找出关联关系小于2的节点
        Set<String> nodesToDelete = new HashSet<>();
        for (Map.Entry<String, Integer> entry : nodeRelationshipCount.entrySet()) {
            if (entry.getValue() < 2) {
                nodesToDelete.add(entry.getKey());
            }
        }

        // 使用Iterator安全地删除不需要的元素
        Iterator<JSONObject> iterator = relationshipList.iterator();
        while (iterator.hasNext()) {
            JSONObject object = iterator.next();
            String startNode = object.getString("startNode");
            String endNode = object.getString("endNode");
            if (nodesToDelete.contains(startNode) || nodesToDelete.contains(endNode)) {
                iterator.remove();
            }
        }
        iterator = nodeList.iterator();
        while (iterator.hasNext()) {
            JSONObject object = iterator.next();
            String id = object.getString("id");
            if (nodesToDelete.contains(id)) {
                iterator.remove();
            }
        }
    }

    /**
     * 移除重复的数据
     *
     * @param jsonArray
     * @param keys
     * @return
     */
    private static List<JSONObject> removeDuplicates(JSONArray jsonArray, String... keys) {
        Set<String> uniqueSet = new HashSet<>();
        List<JSONObject> resultList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            StringBuilder uniqueKey = new StringBuilder();
            for (String key : keys) {
                uniqueKey.append(obj.getString(key)).append("_");
            }

            if (uniqueSet.add(uniqueKey.toString())) {
                resultList.add(obj);
            }
        }

        return resultList;
    }


    /**
     * 处理无关联数据的情况，向节点列表中添加一个表示无关联关系的默认节点。
     *
     * @param nodeList 节点列表，存储图表中的所有节点信息。
     */
    private static void handleNoneData(List<JSONObject> nodeList) {
        JSONObject node = new JSONObject();
        node.put("symbolSize", 100);
        node.put("value", 60);
        node.put("category", 0);
        node.put("id", UUID.randomUUID(false));
        node.put("name", "无关联关系！");
        HashMap<String, Object> colorMap = new HashMap<>();
        colorMap.put("color", Constants_pb.XM_QYGLGX_NODE_GYS_RED);
        node.put("itemStyle", colorMap);
        nodeList.add(node);
    }

    /**
     * 处理展示节点和关系的配置信息，将原始数据转换为适合图形展示的格式。
     *
     * @param gysmcs           供应商名称列表，用于标识哪些节点属于供应商，从而对这些节点进行特殊处理。
     * @param nodeList         节点列表，每个节点表示图形中的一个实体。该列表将被修改以包含图形展示所需的属性。
     * @param relationshipList 关系列表，每个关系表示图形中两个节点之间的连接。该列表将被修改以包含图形展示所需的属性。
     */
    private static void handleShowData(List<String> gysmcs, List<JSONObject> nodeList, List<JSONObject> relationshipList) {
        // 检查节点列表或关系列表是否为空，如果为空则添加一个默认的“无关联关系”节点
        if (CollectionUtil.isEmpty(nodeList) || CollectionUtil.isEmpty(relationshipList)) {
            handleNoneData(nodeList);
            return;
        }
        // 处理节点列表，配置每个节点的图形属性
        if (nodeList != null && nodeList.size() > 0) {
            // nodes
            for (JSONObject node : nodeList) {
                Long id = node.getLongValue("id");
                String labels = node.getJSONArray("labels").getString(0);
                JSONObject properties = node.getJSONObject("properties");
                String name = ConvertUtil.createString(properties.get("name"));
                properties.put("id", id);
                node.clear();
                //对应图形
                node.put("id", id);
                node.put("name", name);
                //字体颜色
                if (Constants_pb.XM_QYGLGX_MAP.get(labels) != null) {
                    HashMap<String, Object> colorMap = new HashMap<>();
                    if (gysmcs.contains(String.valueOf(id))) {
                        colorMap.put("color", Constants_pb.XM_QYGLGX_NODE_GYS_RED);
                    } else {
                        colorMap.put("color", Constants_pb.getXM_QYGLGX_MAP(labels));
                    }
                    node.put("itemStyle", colorMap);
                }
                node.put("value", 60);
                if (gysmcs.contains(String.valueOf(id))) {
                    node.put("symbolSize", 70);
                    node.put("category", 0); // 本供应商
                } else {
                    node.put("symbolSize", 60);
                    node.put("category", 1); // 关联供应商，可以分出关联供应商和人员。
                }

            }
        }

        if (relationshipList != null && relationshipList.size() > 0) {
            // relationships
            // 合并相同startNode和endNode的展示数据
            Map<String, JSONObject> mergedRelationships = new HashMap<>();

            for (JSONObject node : relationshipList) {
                String id = node.getString("id");
                String startNode = node.getString("startNode");
                String endNode = node.getString("endNode");
                String type = node.getString("type");
                JSONObject properties = node.getJSONObject("properties");

                String cgbl = Optional.ofNullable(properties.getString("percent"))
                        .map(percent -> {
                            BigDecimal percentValue = new BigDecimal(percent);
                            if (percentValue.compareTo(BigDecimal.ZERO) == 0) {
                                return "";
                            }
                            return percentValue.multiply(new BigDecimal(100))
                                    .setScale(4, BigDecimal.ROUND_HALF_UP)
                                    .toString() + "%";
                        })
                        .orElse("");

                String gxmc = properties.getJSONArray("labels").getString(0);
                String value = gxmc + cgbl;

                String key = startNode + "_" + endNode;

                if (mergedRelationships.containsKey(key)) {
                    JSONObject existingNode = mergedRelationships.get(key);
                    String existingValue = existingNode.getString("value");
                    existingNode.put("value", existingValue + "," + value);
                } else {
                    JSONObject newNode = new JSONObject();
                    newNode.put("source", startNode);
                    newNode.put("target", endNode);
                    newNode.put("value", value);
                    mergedRelationships.put(key, newNode);
                }
            }
            relationshipList.clear();
            relationshipList.addAll(mergedRelationships.values());
        }
    }


    /**
     * 处理原始数据显示。
     *
     * @param relationshipsArrays 关系数据的JSON数组
     * @param nodesArrays         节点数据的JSON数组
     * @param targetNodes         目标节点ID列表
     */
    private static void handleRawShowData(JSONArray relationshipsArrays,JSONArray nodesArrays, List<String> targetNodes) {
        // 初始化局部的节点和邻接列表
        Map<String, Map<String, String>> nodes = new HashMap<>();
        Map<String, Set<String>> adjacencyList = new HashMap<>();

        // 处理关系数据并构建邻接列表
        processRelationships(relationshipsArrays, adjacencyList);
        // 处理节点数据并构建节点信息
        processNodes(nodesArrays, nodes);

        // 查找所有目标节点之间的公共路径
        List<List<List<String>>> allCommonPaths = findAllCommonPaths(targetNodes, adjacencyList);

        // 获取所有路径中唯一的节点ID
        List<String> uniqueNodeIds = getUniqueNodeIds(allCommonPaths);

        // 删除无关的数据
        deleteUnvailableData(uniqueNodeIds, relationshipsArrays, nodesArrays);
    }

    /**
     * 处理关系数据并更新邻接列表。
     *
     * @param relationshipsArrays 关系数据的JSON数组
     * @param adjacencyList       邻接列表，表示节点之间的关系
     */
    private static void processRelationships(JSONArray relationshipsArrays, Map<String, Set<String>> adjacencyList) {
        IntStream.range(0, relationshipsArrays.size())
                .mapToObj(relationshipsArrays::getJSONObject)
                .forEach(relationship -> addEdge(relationship.getString("startNode"), relationship.getString("endNode"), adjacencyList));
    }

    /**
     * 处理节点数据并更新节点信息。
     *
     * @param nodesArrays 节点数据的JSON数组
     * @param nodes       节点信息的映射
     */
    private static void processNodes(JSONArray nodesArrays, Map<String, Map<String, String>> nodes) {
        IntStream.range(0, nodesArrays.size())
                .mapToObj(nodesArrays::getJSONObject)
                .forEach(node -> addNode(node.getString("id"),
                        Collections.singletonMap("name", node.getJSONObject("properties").getString("name")), nodes));
    }



    /**
     * 查找目标节点之间的所有公共路径。
     *
     * @param targetNodes   目标节点ID列表
     * @param adjacencyList 邻接列表，表示节点之间的关系
     * @return 所有公共路径的列表，每个路径是一个节点ID列表的列表
     */
    private static List<List<List<String>>> findAllCommonPaths(List<String> targetNodes, Map<String, Set<String>> adjacencyList) {

        List<List<List<String>>> allCommonPaths = new ArrayList<>();

        // 外层循环遍历每一个节点
        for (int i = 0; i < targetNodes.size(); i++) {
            List<List<String>> paths1 = findAllPaths(targetNodes.get(i), adjacencyList);
            // 内层循环从下一个节点开始，避免重复和自比较
            for (int j = i + 1; j < targetNodes.size(); j++) {
                // 查找两个节点之间的所有路径并找出公共路径
                List<List<String>> paths2 = findAllPaths(targetNodes.get(j), adjacencyList);
                List<List<String>> commonPaths = findCommonPaths(paths1, paths2);
                // 将公共路径添加到结果列表中
                allCommonPaths.add(commonPaths);
            }
        }

        return allCommonPaths;
    }

    /**
     * 将节点信息添加到节点映射中。
     *
     * @param id         节点ID
     * @param properties 节点属性
     * @param nodes      节点信息的映射
     */
    private static void addNode(String id, Map<String, String> properties, Map<String, Map<String, String>> nodes) {
        nodes.put(id, new HashMap<>(properties));
    }

    /**
     * 将边添加到邻接列表中。
     *
     * @param startNode     起始节点ID
     * @param endNode       终点节点ID
     * @param adjacencyList 邻接列表，表示节点之间的关系
     */
    private static void addEdge(String startNode, String endNode, Map<String, Set<String>> adjacencyList) {
        // 如果起始节点在邻接列表中不存在，则创建一个新的空集合
        adjacencyList.computeIfAbsent(startNode, k -> new HashSet<>()).add(startNode);
        adjacencyList.computeIfAbsent(endNode, k -> new HashSet<>()).add(endNode);
        adjacencyList.computeIfAbsent(endNode, k -> new HashSet<>()).add(startNode);
        // 测试回退 2
    }
// 测试回退 1
}
