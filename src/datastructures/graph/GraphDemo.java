package datastructures.graph;

public class GraphDemo {
    public static void main(String[] args) {
        // DirectedGraphAdjacencyList g = new DirectedGraphAdjacencyList(6);
        // g.addEdge(0, 2);
        // g.addEdge(1, 4);
        // g.addEdge(2, 1);
        // g.addEdge(3, 2);
        // g.addEdge(3, 1);
        // g.addEdge(4, 5);

        DirectedGraphAdjacencyList g = new DirectedGraphAdjacencyList(5);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(3, 1);
        g.addEdge(3, 2);
        g.addEdge(4, 3);

        g.dfsIterative();
    }
}