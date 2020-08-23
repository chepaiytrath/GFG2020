package datastructures.graph;

public class HardProblems {

    public static void main(String[] args) {
        HardProblems hp = new HardProblems();
        hp.travellingSalesmanProblemMinCostWithBacktracking();
    }

    // INSPIRED BY : https://www.geeksforgeeks.org/travelling-salesman-problem-implementation-using-backtracking/
    int startTSP = 2;

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
        int minCost = travellingSalesmanProblemMinCostWithBacktrackingUtil(startTSP, adj, visited, 0, V);
        System.out.println(minCost);
    }

    private int travellingSalesmanProblemMinCostWithBacktrackingUtil(int src, int[][] adj, boolean[] visited, int count, int V) {
        if (count == V - 1) {
            return adj[src][startTSP];
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            if (!visited[i] && adj[src][i] != 0) {
                int edgeWeight = adj[src][i];
                visited[i] = true;
                minCost = Math.min(minCost, edgeWeight + travellingSalesmanProblemMinCostWithBacktrackingUtil(i, adj, visited, count + 1, V));
                visited[i] = false;
            }
        }
        return minCost;
    }
}
