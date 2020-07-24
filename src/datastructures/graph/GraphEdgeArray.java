package datastructures.graph;

public class GraphEdgeArray {
    Edge[] edges;
    int V;
    int E;

    GraphEdgeArray(int v, int e) {
        this.V = v;
        this.E = e;

        edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            edges[i] = new Edge();
        }
    }

    static class Edge {
        int src;
        int dest;
        int wt;

        Edge() {
            src = dest = wt = 0;
        }
    }

}