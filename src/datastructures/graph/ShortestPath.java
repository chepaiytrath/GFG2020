package datastructures.graph;

import datastructures.graph.types.GraphEdgeArray;
import datastructures.graph.types.GraphEdgeArray.Edge;

import java.util.Arrays;


public class ShortestPath {

    // DIJKSTRA's ALGO : SINGLE SOURCE SHORTEST PATH
    // Works on both Directed and non directed graphs
    // For working on undirected graphs: convert it into a digraph by inserting parallel edges
    // MAY OR MAY NOT WORK FOR GRAPH WITH NEGATIVE WEIGHTED EDGE : DUE TO ITS GREEDY NATURE

    public void bellmanFord(GraphEdgeArray gea) {
        // SAMPLE INPUT
        // GraphEdgeArray gea = new GraphEdgeArray(7, 10);

        // gea.edges[0].src = 0;
        // gea.edges[0].dest = 1;
        // gea.edges[0].wt = 6;

        // gea.edges[1].src = 0;
        // gea.edges[1].dest = 2;
        // gea.edges[1].wt = 5;

        // gea.edges[2].src = 0;
        // gea.edges[2].dest = 3;
        // gea.edges[2].wt = 5;

        // gea.edges[3].src = 1;
        // gea.edges[3].dest = 4;
        // gea.edges[3].wt = -1;

        // gea.edges[4].src = 2;
        // gea.edges[4].dest = 1;
        // gea.edges[4].wt = -2;

        // gea.edges[5].src = 2;
        // gea.edges[5].dest = 4;
        // gea.edges[5].wt = 1;

        // gea.edges[6].src = 3;
        // gea.edges[6].dest = 2;
        // gea.edges[6].wt = -2;

        // gea.edges[7].src = 3;
        // gea.edges[7].dest = 5;
        // gea.edges[7].wt = -1;

        // gea.edges[8].src = 4;
        // gea.edges[8].dest = 6;
        // gea.edges[8].wt = 3;

        // gea.edges[9].src = 5;
        // gea.edges[9].dest = 6;
        // gea.edges[9].wt = 3;

        int source = 0;
        System.out.println(bellmanFordUtil(gea, source));
    }

    // SINGLE SOURCE SHORTEST PATH : SAME AS DIJKSTRA'S ALGO
    private int bellmanFordUtil(GraphEdgeArray gea, int source) {
        // Relaxation : Updation of distances to other nodes on finding a shorter path
        int relaxationCount = gea.V - 1;
        Edge[] edges = gea.edges;

        int[] dist = new int[gea.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < relaxationCount; i++) {
            for (int j = 0; j < gea.E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].wt;
                if (dist[u] == Integer.MAX_VALUE) {
                    continue;
                } else {
                    dist[v] = Math.min(dist[v], dist[u] + weight);
                }
            }
        }

        // One more iteration to check for negative weight cycle
        for (int j = 0; j < gea.E; j++) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].wt;
            if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + weight) {
                System.out.println("NEGATIVE WEIGHT CYCLE FOUND");
                return -1;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i : dist) {
            min = Math.min(min, i);
        }
        return min;
    }

    static int INF = Integer.MAX_VALUE;

    public void floydWarshall(Integer[][] graph) {
        // ALSO CALLED ALL PAIRS SHORTEST PATH
        // SAMPLE INPUT
        // int graph[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 },
        // { INF, INF, INF, 0 } };

        int V = graph.length;

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (graph[i][k] != INF && graph[k][j] != INF) {
                        int sum = 0;
                        sum += graph[i][k] + graph[k][j];
                        graph[i][j] = Math.min(graph[i][j], sum);
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (graph[i][i] < 0) {
                System.out.println("NEGATIVE WEIGHT CYCLE DETECTED");
                return;
            }
        }
        for (int i = 0; i < V; i++) {
            System.out.println(Arrays.asList(graph[i]).toString());
        }
    }
}