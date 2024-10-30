package com.example.baseall.graphdata;

import java.util.*;

public class Graph {
    private static Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // 添加边的方法
    public void addEdge(String node, String neighbor) {
        adjacencyList.computeIfAbsent(node, k -> new ArrayList<>()).add(neighbor);
        adjacencyList.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(node);  // 无向图
        adjacencyList.computeIfAbsent(node, k -> new ArrayList<>()).add(node);  // 无向图
    }


    public static void main(String[] args) {
        Graph graph = new Graph();

        // 构建图
        // 添加边
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("D", "G");
        graph.addEdge("E", "H");
        graph.addEdge("F", "I");
        graph.addEdge("G", "J");
        graph.addEdge("H", "J");
        graph.addEdge("I", "J");
        HashSet<String> visit = new HashSet<>();
        List<String[]> edges = new ArrayList<>();
        // 从节点 A 开始进行 BFS
//        bfs("A", visit, adjacencyList);
        Set<Set<String>> allPaths = new HashSet<>();
        dfsFindAllPaths("A", "", new ArrayList<>(), allPaths, visit, adjacencyList);
        for (Set<String> path : allPaths) {
            System.out.println(path);
        }
        for (String[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
        for (String s : visit) {
            System.out.print(s + " -> ");
        }

    }


    public static void bfs(String currentNode, Set<String> visit, Map<String, List<String>> adjacencyList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            if (!visit.contains(poll)) {
                visit.add(poll);
                queue.addAll(adjacencyList.get(poll));
            }
        }
    }

    public static void dfsFindAllPaths(String currentNode, String parentNode, List<String> currentPath, Set<Set<String>> allPaths, Set<String> visited, Map<String, List<String>> adjacencyList) {
        currentPath.add(currentNode);
        visited.add(currentNode);

        if (currentPath.size() >= 2) {
            for (int i = 0; i < currentPath.size() - 1; i++) {
                Set<String> pathPair = new HashSet<>(currentPath.subList(i, i + 2));
                allPaths.add(pathPair);
            }
        }
        adjacencyList.getOrDefault(currentNode, new ArrayList<>()).stream()
                .filter(neighbor -> !visited.contains(neighbor) && !visited.contains(neighbor))
                .forEach(neighbor -> dfsFindAllPaths(neighbor, currentNode, currentPath, allPaths, visited, adjacencyList));

        currentPath.remove(currentPath.size() - 1);
    }

    public static void dfs(String currentNode, Set<String> visit, Map<String, List<String>> adjacencyList) {
        visit.add(currentNode);
        for (String neighbor : adjacencyList.get(currentNode)) {
            if (!visit.contains(neighbor)) {
                dfs(neighbor, visit, adjacencyList);
            }
        }
    }


}
