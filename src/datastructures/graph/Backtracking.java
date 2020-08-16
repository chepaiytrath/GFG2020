package datastructures.graph;

import datastructures.graph.types.DirectedGraphAdjacencyList;

import java.util.*;

public class Backtracking {
    // FOLLOW THE SEQUENCE TO LEARN
    // Two Backtracking approaches can be used here for
    // 1. https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
    // PRESENT SIGHTED
    // Updates for current element if valid index i, j.
    // Calls method recursively for both children hoping one of them gives true.
    // If both calls give false, then backtracks its own updation

    // 2. FORESIGHTED : Updates sol for children and if they don't give true then
    // backtracks the updation
    // Step 1. Reach a node in the matrix (i, j)
    // Step 2. Mark each child as 1 in sol matrix, asssuming they will give a path
    // Step 3. Then recursively call the method on each child which in turn does the
    // same
    // Base case : Either it is blocked OR it has reached the end point
    // Step 4. Continuing from Step 2. If path is found from 1st, return true (1 in
    // the sol matrix).
    // If not, then Step 2 needs to be reverted/backtracked.
    // Same for next child

    // APPROACH1
    public void ratInMazeApproach1() {
        // N x N maze
        int[][] maze = {{1, 0, 0, 0}, {1, 1, 0, 1}, {0, 1, 0, 0}, {1, 1, 1, 1}};
        int N = maze.length;
        Integer[][] sol = new Integer[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(sol[i], 0);
        }
        sol[0][0] = 1;
        if (ratInMazeApproach1Util(0, 0, N, maze, sol)) {
            for (int i = 0; i < N; i++) {
                System.out.println(Arrays.asList(sol[i]));
            }
        } else {
            System.out.println("PATH DOESN'T EXIST");
        }
    }

    private boolean ratInMazeApproach1Util(int i, int j, int N, int[][] maze, Integer[][] sol) {
        if (i == N - 1 && j == N - 1 && maze[i][j] == 1) {
            sol[i][j] = 1;
            return true;
        }

        if (isValid(i, j, maze, N)) {
            sol[i][j] = 1;
            if (ratInMazeApproach1Util(i, j + 1, N, maze, sol)) {
                return true;
            }
            if (ratInMazeApproach1Util(i + 1, j, N, maze, sol)) {
                return true;
            }
            sol[i][j] = 0;
            return false;
        }
        return false;
    }

    // APPROACH2
    public void ratInMazeApproach2() {
        // N x N maze
        int[][] maze = {{1, 0, 0, 0}, {1, 1, 0, 1}, {0, 1, 0, 0}, {1, 1, 1, 1}};
        int N = maze.length;
        Integer[][] sol = new Integer[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(sol[i], 0);
        }
        sol[0][0] = 1;
        if (ratInMazeApproach2Util(0, 0, N, maze, sol)) {
            for (int i = 0; i < N; i++) {
                System.out.println(Arrays.asList(sol[i]));
            }
        } else {
            System.out.println("PATH DOESN'T EXIST");
        }
    }

    private boolean ratInMazeApproach2Util(int i, int j, int N, int[][] maze, Integer[][] sol) {
        if (i == N - 1 && j == N - 1) {
            return true;
        }

        if (isValid(i, j + 1, maze, N)) {
            sol[i][j + 1] = 1;
            if (ratInMazeApproach2Util(i, j + 1, N, maze, sol)) {
                return true;
            } else {
                sol[i][j + 1] = 0;
            }
        }

        if (isValid(i + 1, j, maze, N)) {
            sol[i + 1][j] = 1;
            if (ratInMazeApproach2Util(i + 1, j, N, maze, sol)) {
                return true;
            } else {
                sol[i + 1][j] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int i, int j, int[][] maze, int N) {
        return i >= 0 && i < N && j >= 0 && j < N && maze[i][j] != 0;
    }

    public void knightsTour(int N) {
        int arr[][] = new int[][]{{2, 1, -1, -2, -2, -1, 1, 2}, {1, 2, 2, 1, -1, -2, -2, -1}};
        Integer[][] sol = new Integer[N][N];
        for (int x = 0; x < N; x++) {
            Arrays.fill(sol[x], -1);
        }
        sol[0][0] = 0;
        // Put this movenum for all next nodes
        int nextMoveNum = 1;
        if (knightsTourUtil(0, 0, nextMoveNum, sol, arr, N)) {
            for (int i = 0; i < sol.length; i++) {
                System.out.println(Arrays.asList(sol[i]));
            }
        } else {
            System.out.println("Solution does not exist");
        }
    }

    // BACKTRACKING HINT: DO SOME ACTION FOR CHILDREN BEFORE CALLING THEM
    // RECURSIVELY
    // IF THAT RECURSIVE CALL PASSES, THEN RETURN TRUE. EX. PATH FOUND
    // IF IT FAILS, THEN REVERT THE ACTIONS YOU TOOK FOR THE CHILDREN EARLIER
    private boolean knightsTourUtil(int x, int y, int nextMoveNum, Integer[][] sol, int[][] arr, int N) {
        int k, next_x, next_y;
        if (nextMoveNum == N * N) {
            return true;
        }

        for (k = 0; k < 8; k++) {
            next_x = x + arr[0][k];
            next_y = y + arr[1][k];
            // Also checks if visited
            if (isSafe(next_x, next_y, sol, N)) {
                // Put nextMoveNum for sol[next_x][next_y], which is already received from
                // parent
                sol[next_x][next_y] = nextMoveNum;
                if (knightsTourUtil(next_x, next_y, nextMoveNum + 1, sol, arr, N)) {
                    return true;
                } else {
                    sol[next_x][next_y] = -1;
                }
            }
        }
        return false;
    }

    private boolean isSafe(int x, int y, Integer[][] sol, int N) {
        // sol[x][y] == -1 : Not visited
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    public void nQueensProblem() {
        // Place n queens in non offensive positions in n x n chess board
        // VIDEO EXPLANATION @
        // https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
        int n = 4;
        Integer[][] sol = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(sol[i], 0);
        }
        int[] queenRows = new int[n];
        Arrays.fill(queenRows, -1);
        if (nQueensProblemUtil(0, sol, n, queenRows)) {
            for (int i = 0; i < n; i++) {
                System.out.println(Arrays.asList(sol[i]));
            }
        } else {
            System.out.println("CANNOT PLACE " + n + " QUEENS IN " + n + "x" + n + " CHESSBOARD");
        }
    }

    // col keeps track of what column to provide Queen Qi: For Q0 : 0, For Q1 :
    // 0+1=1, For Q2 : 1+1=2
    // sol : state matrix
    // queenRows : array to save row val allotted to each queen, obviously in
    // different columns because of col
    // queenRows[0]=2 : means Q0 is allotted col0 and row2
    private boolean nQueensProblemUtil(int col, Integer[][] sol, int n, int[] queenRows) {
        if (col == n) {
            return true;
        }
        for (int row = 0; row < n; row++) {
            if (isSafeMove(row, col, n, sol, queenRows)) {
                sol[row][col] = 1;
                queenRows[col] = row;
                if (nQueensProblemUtil(col + 1, sol, n, queenRows)) {
                    return true;
                } else {
                    sol[row][col] = 0;
                    queenRows[col] = -1;
                }
            }
        }
        return false;
    }

    private boolean isSafeMove(int row, int col, int n, Integer[][] sol, int[] queenRows) {
        for (int ind = 0; ind < n; ind++) {
            if (row == queenRows[ind]) {
                return false;
            }
        }

        boolean isSafe = true;
        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0 && i < n && j < n) {
            if (sol[i][j] == 1) {
                isSafe = false;
                break;
            }
            i = i - 1;
            j = j - 1;
        }
        if (isSafe) {
            i = row + 1;
            j = col - 1;
            while (i >= 0 && j >= 0 && i < n && j < n) {
                if (sol[j][i] == 1) {
                    isSafe = false;
                    break;
                }
                i = i + 1;
                j = j - 1;
            }
        }
        return isSafe;
    }

    public void mColoringDecisionProblem(DirectedGraphAdjacencyList graph, int colors) {
        // DirectedGraphAdjacencyList graph = new DirectedGraphAdjacencyList(4);
        // graph.addEdge(0, 1);
        // graph.addEdge(0, 3);
        // graph.addEdge(0, 2);
        // graph.addEdge(1, 0);
        // graph.addEdge(1, 3);
        // graph.addEdge(2, 0);
        // graph.addEdge(2, 3);
        // graph.addEdge(3, 2);
        // graph.addEdge(3, 0);
        // graph.addEdge(3, 1);
        // int colors = 3;

        int v = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        int[] sol = new int[v];
        int index = 0;

        if (mColoringDecisionProblemUtil(colors, sol, index, v, adj)) {
            System.out.println("It is possible");
            for (int i = 0; i < sol.length; i++) {
                System.out.print(sol[i] + " ");
            }
        } else {
            System.out.println("It is not possible");
        }
    }

    private boolean mColoringDecisionProblemUtil(int colors, int[] sol, int index, int v, List<Integer>[] adj) {
        if (index == v) {
            return true;
        }

        for (int color = 1; color <= colors; color++) {
            if (isSafeToPutColor(adj, sol, index, color)) {
                sol[index] = color;
                if (mColoringDecisionProblemUtil(colors, sol, index + 1, v, adj)) {
                    return true;
                } else {
                    sol[index] = 0;
                }
            }
        }
        return false;
    }

    private boolean isSafeToPutColor(List<Integer>[] adj, int[] sol, int vertex, int color) {
        for (int child : adj[vertex]) {
            if (sol[child] == color) {
                return false;
            }
        }
        return true;
    }

    public void mColoringOptimizationProblem(DirectedGraphAdjacencyList graph) {
        /*
         * DirectedGraphAdjacencyList graph = new DirectedGraphAdjacencyList(5);
         * graph.addEdge(0, 2); graph.addEdge(0, 3); graph.addEdge(1, 2);
         * graph.addEdge(1, 4); graph.addEdge(2, 0); graph.addEdge(2, 1);
         * graph.addEdge(2, 4); graph.addEdge(2, 3); graph.addEdge(3, 0);
         * graph.addEdge(3, 2); graph.addEdge(4, 1); graph.addEdge(4, 2);
         */

        int V = graph.getV();
        List<Integer>[] adj = graph.getAdj();

        int[] sol = new int[V];
        int colorLimit = V;
        int index = 0;
        mColoringOptimizationProblemUtil(V, colorLimit, adj, sol, index);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < V; i++) {
            max = Math.max(max, sol[i]);
        }
        System.out.println("MINIMUM NUMBER OF COLORS REQUIRED TO COLOUR GRAPH IS " + max);
    }

    private boolean mColoringOptimizationProblemUtil(int V, int colorLimit, List<Integer>[] adj, int[] sol, int index) {
        if (index == V) {
            return true;
        }

        for (int color = 1; color <= colorLimit; color++) {
            if (isSafeToPutColor(adj, sol, index, color)) {
                sol[index] = color;
                if (mColoringOptimizationProblemUtil(V, colorLimit, adj, sol, index + 1)) {
                    return true;
                } else {
                    sol[index] = 0;
                }
            }
        }
        return false;
    }

    static int maxDiff = Integer.MAX_VALUE;

    public void tugOfWar(int[] arr) {
        // int[] arr = new int[] { 23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4 }; //ODD
        //int[] arr = new int[] { 3, 4, 5, -3, 100, 1, 89, 54, 23, 20 }; //EVEN
        int n = arr.length;

        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }

        Integer[] sol = new Integer[n];
        Arrays.fill(sol, 0);
        int index = 0;
        int selectedCount = 0;
        int sumSelected = 0;
        Integer[] finalSol = new Integer[n];
        tugOfWarUtil(index, totalSum, n, sol, finalSol, arr, selectedCount, sumSelected);

        for (int i = 0; i < n; i++) {
            if (finalSol[i] == 1) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            if (finalSol[i] == 0) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    private void tugOfWarUtil(int index, int totalSum, int n, Integer[] sol, Integer[] finalSol, int[] arr,
                              int selectedCount, int sumSelected) {
        if (index >= n) {
            return;
        }
        if ((n % 2 == 0 && selectedCount == (n / 2)) || (n % 2 != 0 && selectedCount == (n / 2) + 1)) {
            int restSum = totalSum - sumSelected;
            int diff = Math.abs(restSum - sumSelected);
            if (diff < maxDiff) {
                maxDiff = diff;
                for (int i = 0; i < n; i++) {
                    finalSol[i] = sol[i];
                }
            }
            return;
        } else {
            sol[index] = 1;
            tugOfWarUtil(index + 1, totalSum, n, sol, finalSol, arr, selectedCount + 1, sumSelected + arr[index]);
            sol[index] = 0;
            tugOfWarUtil(index + 1, totalSum, n, sol, finalSol, arr, selectedCount, sumSelected);
            sol[index] = 0;
        }
    }

    public void printOneHamiltonianCycle(DirectedGraphAdjacencyList graph) {
        int v = graph.getV();
        List<Integer>[] adj = graph.getAdj();
        boolean[] visited = new boolean[v];
        int start = 0;
        List<Integer> list = new ArrayList<>();
        if (!printOneHamiltonianCycleUtil(start, start, visited, adj, list, 0)) {
            System.out.println("NO HAMILTONIAN CYCLE FOUND");
        }
    }

    private boolean printOneHamiltonianCycleUtil(int start, int src, boolean[] visited, List<Integer>[] adj, List<Integer> list, int visitedVerticesCount) {
        visited[src] = true;
        list.add(src);
        visitedVerticesCount++;
        boolean flag = false;
        Set<Integer> children = new HashSet<>();
        for (int c : adj[src]) {
            children.add(c);
            if (!visited[c]) {
                flag = true;
                if (printOneHamiltonianCycleUtil(start, c, visited, adj, list, visitedVerticesCount)) {
                    return true;
                } else {
                    visited[c] = false;
                    list.remove(list.size() - 1);
                }
            }
        }
        if (!flag && children.contains(start) && visitedVerticesCount == adj.length) {
            list.add(start);
            System.out.println(list);
            return true;
        }
        return false;
    }
}