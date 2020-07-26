package datastructures.graph;

import datastructures.graph.types.GraphEdgeArray;

public class GraphDemo {
    /*
     * public static void main(String[] args) { BFSDFS bd = new BFSDFS(); GraphCycle
     * gc = new GraphCycle();
     * 
     * DirectedGraphAdjacencyList graph = new DirectedGraphAdjacencyList(7);
     * graph.addEdge(0, 1); graph.addEdge(0, 2); graph.addEdge(1, 2);
     * graph.addEdge(2, 0); graph.addEdge(2, 3); graph.addEdge(3, 3);
     * 
     * gc.detectCycleInADirectedGraphUsingDFS(graph); }
     */

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();
        GraphCycle gc = new GraphCycle();
        GraphEdgeArray gea = new GraphEdgeArray(8, 9);
        BFSDFS bfs = new BFSDFS();
        TopologicalSorting ts = new TopologicalSorting();
        MinimumSpanningTree mst = new MinimumSpanningTree();
        Backtracking bc = new Backtracking();
        Problems pr = new Problems();

        // int[][] mat = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };
        int[][] mat = { { 2, 1, 0, 2, 1 }, { 0, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };
        // pr.rottenOranges(mat);
        pr.snakesAndLadders();

    }

    static int INF = Integer.MAX_VALUE;
}