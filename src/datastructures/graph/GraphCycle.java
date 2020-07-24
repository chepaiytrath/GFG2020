package datastructures.graph;

import java.util.List;

public class GraphCycle {
    public void detectCycleInDirectedGraphUsingDFS(DirectedGraphAdjacencyList graph) {
        int v = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        // visited[] is only used to not go to some previosuly traversed path
        boolean[] visited = new boolean[v];
        // To keep track of vertices on recursion stack
        boolean[] recStack = new boolean[v];

        boolean isCyclic = false;
        for (int i = 0; i < v; i++) {
            if (detectCycleInDirectedGraphUsingDFSUtil(i, adj, visited, recStack)) {
                isCyclic = true;
                break;
            }
        }
        System.out.println(isCyclic);
    }

    private boolean detectCycleInDirectedGraphUsingDFSUtil(int src, List<Integer>[] adj, boolean[] visited,
            boolean[] recStack) {
        if (recStack[src]) {
            return true;
        }
        if (visited[src]) {
            return false;
        }

        visited[src] = true;
        recStack[src] = true;
        for (int child : adj[src]) {
            if (detectCycleInDirectedGraphUsingDFSUtil(child, adj, visited, recStack)) {
                return true;
            }
        }
        recStack[src] = false;
        return false;
    }

    public void detectCycleInUndirectedGraphUsingDFS(DirectedGraphAdjacencyList graph) {
        int v = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        boolean[] visited = new boolean[v];

        boolean isCyclic = false;
        int parent = -1;
        for (int i = 0; i < v; i++) {
            // In case of a connected graph without cycle exists, visted becomes true for
            // all vertices in one pass
            if (!visited[i] && detectCycleInUndirectedGraphUsingDFSUtil(i, adj, visited, parent)) {
                isCyclic = true;
                break;
            }
        }

        System.out.println(isCyclic);

    }

    private boolean detectCycleInUndirectedGraphUsingDFSUtil(int src, List<Integer>[] adj, boolean[] visited,
            int parent) {
        visited[src] = true;
        for (int c : adj[src]) {
            if (!visited[c]) {
                if (detectCycleInUndirectedGraphUsingDFSUtil(c, adj, visited, src)) {
                    return true;
                }
            } else if (c != parent) {
                // HINT: If an adjacent vertex is visited but not parent of current vertex, then
                // there is a cycle
                return true;
            }
        }
        return false;
    }

    public void magicalIndices(int[] input) {
        int n = input.length;
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = input[i];
        }

        int[] children = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = ((i + arr[i]) % n) + 1;
        }
        boolean[] vis = new boolean[n + 1];
        int end = 1;
        int prev = 0;
        while (end <= n) {
            if (vis[end]) {
                break;
            }
            vis[end] = true;
            prev = end;
            end = children[end];
        }
        for (int i = end; i <= prev; i++) {
            System.out.print(i + " ");
        }
    }
}