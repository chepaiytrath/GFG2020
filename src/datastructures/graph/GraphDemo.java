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
        GraphEdgeArray gea = new GraphEdgeArray(7, 10);
        BFSDFS bfs = new BFSDFS();
        TopologicalSorting ts = new TopologicalSorting();

        DirectedGraphAdjacencyList graph = new DirectedGraphAdjacencyList(6);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        
        ts.findAllPossibleTopologicalSortingForDAG(graph);
    }

    static int INF = Integer.MAX_VALUE;
}