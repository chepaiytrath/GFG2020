package datastructures.graph;

public class HardProblems {

    public static void main(String[] args) {
        HardProblems hp = new HardProblems();
        hp.travellingSalesmanProblemMinCostWithBacktracking();
    }

    // INSPIRED BY BUT SOMEWHAT DIFFERENT FROM : https://www.geeksforgeeks.org/travelling-salesman-problem-implementation-using-backtracking/
    // NEED TO KEEP STATICALLY OUTSIDE SO THAT LAST NODE CAN RETURN THE DISTANCE BACK IN THE BASE CAE
    int startTSP = 2;

    // BRUTE FORCE : O(n!) : BECAUSE IT TRAVERSES ACROSS ALL PERMUTATIONS OF GRAPH
    public void travellingSalesmanProblemMinCostWithBacktracking() {
        /*int[][] adj = new int[][]{
                {0, 20, 42, 25},
                {20, 0, 30, 34},
                {42, 30, 0, 10},
                {25, 34, 10, 0}
        };*/

        int[][] adj = {
                {0, 3, 30, 2},
                {3, 0, 4, 20},
                {30, 4, 0, 1},
                {2, 20, 1, 0}};

        int V = 4;
        boolean[] visited = new boolean[V];
        visited[startTSP] = true;
        int minCost = travellingSalesmanProblemMinCostWithBacktrackingUtil(startTSP, 0, adj, visited, V);
        System.out.println(minCost);
    }

    // SIMILAR TO N QUEENS BACKTRACKING TECHNIQUE
    // THIS SOLUTION IS FOR ALL PAIRS CONNECTED ADJACENCY MATRIX
    private int travellingSalesmanProblemMinCostWithBacktrackingUtil(int src, int count, int[][] adj, boolean[] visited, int V) {
        // ALL PAIRS ARE CONNECTED SO RETURN WEIGHT OF LAST NODE WITH SOURCE OF TSP (startTSP) KEPT STATICALLY OUTSIDE
        if (count == V - 1) {
            return adj[src][startTSP];
        }
        int minCost = Integer.MAX_VALUE;

        // ALL POSSIBLE VERTICES TO CHOOSE
        for (int i = 0; i < V; i++) {
            // CHOOSE ONE BY ONE UNVISITED ADJACENT NODES/NEIGHBOR: I.E. THERE EXISTS A WEIGHTED EDGE BETWEEN I AND SRC, I.E. ADJ[SRC][I] != 0
            // BECAUSE IT IS A COMPLETE GRAPH: ALL OTHER NODES WILL BE ADJACENT NODES
            if (!visited[i] && adj[src][i] != 0) {
                int edgeWeight = adj[src][i];
                // ACTION WHICH MIGHT NEED BACKTRACKING
                // MARK CURRENT NODE VISITED SO THAT RECURSIVE DFS CALL CAN'T CHOOSE IT
                visited[i] = true;

                // CALL DFS RECURSIVELY WITH ONE LESS NUMBER OF UNVISITED NODES : SO POSSIBLE VERTICES TO CHOOSE ARE ONE LESS
                // WILL NOT OVERFLOW ON ADDING EDGEWEIGHT BECAUSE ALL PAIRS ARE CONNECTED SO LAST PAIR GIVES A VALID WEIGHT
                minCost = Math.min(minCost, edgeWeight + travellingSalesmanProblemMinCostWithBacktrackingUtil(i, count + 1, adj, visited, V));

                // BACKTRACKING
                // REVERT BACK THE VISITED FLAG, SO THAT IT IS FREE TO BE CHOSEN
                visited[i] = false;
            }
        }
        return minCost;
    }
}
