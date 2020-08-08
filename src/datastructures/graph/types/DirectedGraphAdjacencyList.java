package datastructures.graph.types;

import java.util.ArrayList;

public class DirectedGraphAdjacencyList {
    private int v;
    private ArrayList<Integer>[] adj;

    public DirectedGraphAdjacencyList(int v) {
        this.v = v;
        adj = new ArrayList[v]  ;
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int x, int y) {
        adj[x].add(y);
        // VICE VERSA NOT TO BE DONE IN DIRECTED GRAPH
        //adj[y].add(x);
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public ArrayList<Integer>[] getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Integer>[] adj) {
        this.adj = adj;
    }
}