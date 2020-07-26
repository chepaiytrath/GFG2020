package datastructures.graph;

import java.util.Arrays;

public class MinimumSpanningTree {
    public void primsAlgorithm(int[][] graph) {
        int V = graph.length;
        boolean[] visited = new boolean[V];
        Integer[] mst = new Integer[V];
        Arrays.fill(mst, Integer.MAX_VALUE);
        visited[0] = true;
        mst[0] = 0;
        primsAlgorithmUtil(graph, V, visited, mst);
        System.out.println(Arrays.asList(mst));
    }

    private void primsAlgorithmUtil(int[][] graph, int v, boolean[] visited, Integer[] mst) {
        while (mst[v - 1] == Integer.MAX_VALUE) {
            Pair indices = findMin(graph, visited, mst);
            visited[indices.y] = true;
            mst[indices.y] = mst[indices.x] + 1;
        }
    }

    static class Pair {
        int x;
        int y;
    }

    private Pair findMin(int[][] graph, boolean[] visited, Integer[] mst) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        Pair indices = new Pair();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                for (int j = 0; j < graph[i].length; j++) {
                    if(graph[i][j] == Integer.MAX_VALUE){
                        continue;
                    }else if (graph[i][j] < min) {
                        indices.x = i;
                        indices.y = j;
                        index = j;
                        min = graph[i][j];
                    }
                }
            }
        }
        return indices;
    }
}