package datastructures.graph;

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
        // GraphEdgeArray gea = new GraphEdgeArray(8, 9);
        BFSDFS bfs = new BFSDFS();
        TopologicalSorting ts = new TopologicalSorting();

        DirectedGraphAdjacencyList graph = new DirectedGraphAdjacencyList(6);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        
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

        gc.detectCycleInUndirectedGraphUsingDisjointSets(gea);
    }

    static int INF = Integer.MAX_VALUE;
}