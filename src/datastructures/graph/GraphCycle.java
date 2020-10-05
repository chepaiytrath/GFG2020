package datastructures.graph;

import datastructures.graph.types.DirectedGraphAdjacencyList;
import datastructures.graph.types.GraphEdgeArray;
import datastructures.graph.types.GraphEdgeArray.Edge;

import java.util.Arrays;
import java.util.List;

public class GraphCycle {
    public void detectCycleInDirectedGraphUsingDFS(DirectedGraphAdjacencyList graph) {
        int v = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        // visited[] is only used to not go to some previously traversed path
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
        // VERTICES ON PREVIOUSLY VISITED BRANCHES ARE ALREADY MADE FALSE
        // THIS WILL CONTAIN THE VERTICES ONLY ON THE CURRENT SUBTREE.
        // IF WE REVISIT A VERTEX ALREADY PRESENT ON THE CURRENT RECURSION STACK,
        // THERE'S A CYCLE
        if (recStack[src]) {
            return true;
        }

        // SO THAT IT DOES NOT VISIT A VERTEX FROM A PREVIOUSLY VISITED BRANCH
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

        // MADE FALSE SO THAT THE NEXT BRANCH DOESNT CONSIDER THIS AS A VERTEX ON ITS
        // OWN RECSTACK
        recStack[src] = false;
        return false;
    }

    // Use this to check if a Graph is tree or not : No cycles and connected (all visited)
    public void detectCycleInUndirectedGraphUsingDFS(DirectedGraphAdjacencyList graph) {
        int v = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        boolean[] visited = new boolean[v];

        boolean isCyclic = false;
        int parent = -1;
        for (int i = 0; i < v; i++) {
            // TO CHECK CYCLES FOR A DISONNECTED GRAPH
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

    public static void main(String[] args) {
        detectCycleInUndirectedGraphUsingDisjointSets();
    }
    public static void detectCycleInUndirectedGraphUsingDisjointSets() {
        // GFG : https://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
        // ABDUL BARI : https://www.youtube.com/watch?v=wU6udHRIkcc
        // SAMPLE INPUT FOR CYCLE PRESENT
         GraphEdgeArray gea = new GraphEdgeArray(8, 9);
         gea.edges[0].src = 0;
         gea.edges[0].dest = 1;
         gea.edges[0].wt = 0;

         gea.edges[1].src = 2;
         gea.edges[1].dest = 3;
         gea.edges[1].wt = 1;

         gea.edges[2].src = 4;
         gea.edges[2].dest = 5;
         gea.edges[2].wt = 2;

         gea.edges[3].src = 6;
         gea.edges[3].dest = 7;
         gea.edges[3].wt = 3;

         gea.edges[4].src = 1;
         gea.edges[4].dest = 3;
         gea.edges[4].wt = 4;

         gea.edges[5].src = 1;
         gea.edges[5].dest = 4;
         gea.edges[5].wt = 5;

         gea.edges[6].src = 0;
         gea.edges[6].dest = 2;
         gea.edges[6].wt = 6;

         gea.edges[7].src = 5;
         gea.edges[7].dest = 7;
         gea.edges[7].wt = 7;

         gea.edges[8].src = 4;
         gea.edges[8].dest = 6;
         gea.edges[8].wt = 8;

        // SAMPLE INPUT FOR CYCLE ABSENT
        // GraphEdgeArray gea = new GraphEdgeArray(8, 7);
        // gea.edges[0].src = 0;
        // gea.edges[0].dest = 1;
        // gea.edges[0].wt = 0;

        // gea.edges[1].src = 2;
        // gea.edges[1].dest = 3;
        // gea.edges[1].wt = 1;

        // gea.edges[2].src = 4;
        // gea.edges[2].dest = 5;
        // gea.edges[2].wt = 2;

        // gea.edges[3].src = 6;
        // gea.edges[3].dest = 7;
        // gea.edges[3].wt = 3;

        // gea.edges[4].src = 1;
        // gea.edges[4].dest = 4;
        // gea.edges[4].wt = 4;

        // gea.edges[5].src = 0;
        // gea.edges[5].dest = 2;
        // gea.edges[5].wt = 5;

        // gea.edges[6].src = 5;
        // gea.edges[6].dest = 7;
        // gea.edges[6].wt = 6;

        int V = gea.V;
        GraphEdgeArray.Edge[] edges = gea.edges;

        // PARENT ARRAY FOR DISJOINT SET
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        for (Edge e : edges) {
            int u = find(parent, e.src);
            int v = find(parent, e.dest);
            if (u == v) {
                System.out.println("CYCLE DETECTED");
                return;
            }
            union(parent, u, v);
        }
        System.out.println("NO CYCLE DETECTED");
    }

    private static int find(int parent[], int ind) {
        if (parent[ind] < 0) {
            return ind;
        }

        // PATH COMPRESSION DONE ALONG THE WAY
        parent[ind] = find(parent, parent[ind]);

        return parent[ind];
    }

    // Example of Union by weight / weighted union
    // Maintains wt at root level instead of in a separate array
    private static void union(int[] parent, int u, int v) {
        //parent[] will get negative values : -2, -3, etc
        // - means it is a parent, 2 means it has a weight of 2 (also known as rank)
        if (parent[u] <= parent[v]) {
            int temp = parent[v];
            parent[v] = u;
            parent[u] += temp;
        } else {
            int temp = parent[u];
            parent[u] = v;
            parent[v] += temp;
        }
    }
}