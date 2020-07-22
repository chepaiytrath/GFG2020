package datastructures.graph;

import java.util.List;

public class GraphCycle {
    public void detectCycleInADirectedGraphUsingDFS(DirectedGraphAdjacencyList graph) {
        int v = graph.getV();

        List<Integer>[] adj = graph.getAdj();
        boolean[] visited = new boolean[v];
        boolean[] recStack = new boolean[v];

        for(int i = 0; i < v; i++){
            if(!visited[i]){
                detectCycleInADirectedGraphUsingDFSUtil(i, adj, visited, recStack);    
            }
        }
    }

    private boolean detectCycleInADirectedGraphUsingDFSUtil(int src, List<Integer>[] adj, boolean[] visited, boolean[] recStack) {
        if(recStack[src]){
            return true;
        }
        visited[src] = true;
        recStack[src] = true;
        for(int child : adj[src]){
            if(!visited[child]){
                return detectCycleInADirectedGraphUsingDFSUtil(child, adj, visited, recStack);
            }
        }
        recStack[src] = false; 
        return false;
    }

}