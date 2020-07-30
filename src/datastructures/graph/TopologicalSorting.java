package datastructures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import datastructures.graph.types.DirectedGraphAdjacencyList;

public class TopologicalSorting {
    public void findOnePossibleTopologicalSortingForDAG(DirectedGraphAdjacencyList graph) {
        int V = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                findOnePossibleTopologicalSortingForDAGUtil(i, adj, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private void findOnePossibleTopologicalSortingForDAGUtil(int src, List<Integer>[] adj, boolean[] visited,
            Stack<Integer> stack) {
        visited[src] = true;
        for (int c : adj[src]) {
            if (!visited[c]) {
                findOnePossibleTopologicalSortingForDAGUtil(c, adj, visited, stack);
            }
        }
        stack.add(src);
    }

    public void findAllPossibleTopologicalSortingForDAG(DirectedGraphAdjacencyList graph) {
        int V = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        boolean[] visited = new boolean[V];
        List<Integer> list = new ArrayList<>();

        int[] indeg = new int[V];
        for (int i = 0; i < V; i++) {
            for (int c : adj[i]) {
                indeg[c]++;
            }
        }
        findAllPossibleTopologicalSortingForDAGUtil(indeg, adj, V, visited, list);
    }

    private void findAllPossibleTopologicalSortingForDAGUtil(int[] indeg, List<Integer>[] adj, int V, boolean[] visited,
            List<Integer> list) {
        boolean flag = false;
        for (int i = 0; i < V; i++) {
            if (!visited[i] && indeg[i] == 0) {
                visited[i] = true;
                list.add(i);
                for (int c : adj[i]) {
                    indeg[c]--;
                }
                findAllPossibleTopologicalSortingForDAGUtil(indeg, adj, V, visited, list);
                visited[i] = false;
                list.remove(list.size() - 1);
                for (int c : adj[i]) {
                    indeg[c]++;
                }
                flag = true;
            }            
        }
        // For last element flag = false
        if (!flag) {
            System.out.println(list);
        }
    }

}