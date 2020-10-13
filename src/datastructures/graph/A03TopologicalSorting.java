package datastructures.graph;

import datastructures.graph.types.DirectedGraphAdjacencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class A03TopologicalSorting {
    // RULE
    // TOPOLOGICAL SORTING IN A DAG ALWAYS STARTS WITH ONE OF THE NODES WITH NO INCOMING EDGES

    public void findOnePossibleTopologicalSortingForDAG(DirectedGraphAdjacencyList graph) {
        int V = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        // FINALLY, THE STACK WILL HAVE A NODE WITH NO INCOMING EDGES AT THE TOP
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

        // POPULATE INDEG FOR EACH VERTEX : EITHER THOUGH ADJ LIST OR EDGES LIST
        int[] indeg = new int[V];
        for (int i = 0; i < V; i++) {
            for (int c : adj[i]) {
                indeg[c]++;
            }
        }
        findAllPossibleTopologicalSortingForDAGUtil(indeg, adj, V, visited, list);
    }

    // BACKTRACKING
    private void findAllPossibleTopologicalSortingForDAGUtil(int[] indeg, List<Integer>[] adj, int V, boolean[] visited,
                                                             List<Integer> list) {
        boolean flag = false;                                       // For last element flag will remain false
        for (int i = 0; i < V; i++) {
            if (!visited[i] && indeg[i] == 0) {                     // Keep storing unvisited vertices with indeg = 0 into list and DFS for rest

                // BACKTRACKING ACTION
                visited[i] = true;                                  // Mark this as visited
                list.add(i);                                        // Add to Topological sort list : Keeps storing indeg = 0 vertices
                for (int c : adj[i]) {                              // As if you're removing this vertex from graph
                    indeg[c]--;                                     // Decrement indeg of all child nodes of this vertex
                }
                // BACKTRACKING ACTION

                findAllPossibleTopologicalSortingForDAGUtil(indeg, adj, V, visited, list);

                // BACKTRACKING
                visited[i] = false;
                list.remove(list.size() - 1);
                for (int c : adj[i]) {
                    indeg[c]++;
                }
                // BACKTRACKING
                flag = true;
            }
        }
        // IF NO UNVISITED NODE WITH INDEG - 0 IS FOUND : REACHED LAST
        // For last element flag = false
        if (!flag) {
            System.out.println(list);
        }
    }
}