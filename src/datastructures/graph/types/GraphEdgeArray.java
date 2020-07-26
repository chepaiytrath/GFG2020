package datastructures.graph.types;

public class GraphEdgeArray {
    public Edge[] edges;
    public int V;
    public int E;

    public GraphEdgeArray(int v, int e) {
        this.V = v;
        this.E = e;

        edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            edges[i] = new Edge();
        }
    }

    public static class Edge {
        public int src;
        public int dest;
        public int wt;

        public Edge() {
            src = dest = wt = 0;
        }
    }

}