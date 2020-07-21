package datastructures.graph;

public class GraphDemo {
    public static void main(String[] args) {
        BFSDFS bd = new BFSDFS();

        DirectedGraphAdjacencyList g = new DirectedGraphAdjacencyList(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 4);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(2, 5);
        g.addEdge(3, 4);
        g.addEdge(4, 0);
        g.addEdge(4, 3);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 2);
        g.addEdge(5, 4);
        g.addEdge(6, 4);

        /* int[][] mat = {
            { 3,3,1,0 }, 
            { 3,0,3,3 }, 
            { 2,3,0,3 },
            { 0,3,3,3 }
        }; */

        int[][] mat = {{ 3 , 3 , 1 , 0 },
        { 3 , 0 , 3 , 3 },
        { 2 , 3 , 0 , 3 },
        { 0 , 3 , 3 , 3 }};
        bd.findTheMinimumNumberOfMovesNeededToMoveFromOneCellOfMatrixToAnother(mat);
    }
}