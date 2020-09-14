package datastructures.graph;

import datastructures.graph.types.DirectedGraphAdjacencyList;

import java.util.*;

public class Backtracking {
    // FOLLOW THE SEQUENCE TO LEARN BACKTRACKING PROPERLY


    // Two Backtracking approaches can be used here for
    // 1. https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
    // PRESENT SIGHTED
    // Updates for current element if valid index i, j.
    // Calls method recursively for both children hoping one of them gives true.
    // If both calls give false, then backtracks its own updation

    // 2. FORESIGHTED : Updates sol for children and if they don't give true then backtracks the updation
    // Step 1. Reach a node in the matrix (i, j)
    // Step 2. Mark each child as 1 in sol matrix, asssuming they will give a path
    // Step 3. Then recursively call the method on each child which in turn does the same
    // Base case : Either it is blocked OR it has reached the end point
    // Step 4. Continuing from Step 2. If path is found from 1st, return true (1 in
    // the sol matrix).
    // If not, then Step 2 needs to be reverted/backtracked.
    // Same for next child

    // APPROACH1
    public void ratInMazeApproach1() {
        // REQUIREMENT : FIND IF A PATH EXISTS : CAN USE DFS TO FIND ANY PATH BETWEEN SOURCE AND DESTINATION

        // N x N maze
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        int N = maze.length;

        // SOL ALSO USED TO FUNCTION AS VISITED
        // AFTER ALL CALCULATIONS, SOL WILL CONTAIN THE PATH HIGHLIGHTED BY 1s
        Integer[][] sol = new Integer[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(sol[i], 0);
        }
        //DEFAULT SOURCE WILL BE 1
        sol[0][0] = 1;

        // DFS CALLED TO UPDATE SOL
        // ONLY ONE CALL TO DFS WITH I = 0 AND J = 0 IS REQUIRED BECAUSE WE ARE ONLY INTERESTED IN SOURCE TO DESTINATION PATH
        boolean isPathFound = ratInMazeApproach1Util(0, 0, N, maze, sol);

        //PRINTING THE SOLUTION
        if (isPathFound) {
            for (int i = 0; i < N; i++) {
                System.out.println(Arrays.asList(sol[i]));
            }
        } else {
            System.out.println("PATH DOESN'T EXIST");
        }
    }

    private boolean ratInMazeApproach1Util(int i, int j, int N, int[][] maze, Integer[][] sol) {
        // CURRENT POSITION IS DESTINATION
        if (i == N - 1 && j == N - 1 && maze[i][j] == 1) {
            sol[i][j] = 1;
            return true;
        }

        // CURRENT POSITION IS VALID IF IT IS NOT OOB AND IS NOT EQUAL TO 0
        if (isValid(i, j, maze, N)) {
            sol[i][j] = 1;

            // IF EITHER OF THE POSSIBLE ROUTES REACHES THE DESTINATION POSITION, THEN NO NEED TO BACKTRACK, JUST RETURN TRUE

            // POSSIBLE ROUTE 1
            if (ratInMazeApproach1Util(i, j + 1, N, maze, sol)) {
                return true;
            }

            // POSSIBLE ROUTE 2
            if (ratInMazeApproach1Util(i + 1, j, N, maze, sol)) {
                return true;
            }

            // BACKTRACKING
            sol[i][j] = 0;
            return false;
        }

        return false;
    }

    private boolean isValid(int i, int j, int[][] maze, int N) {
        return i >= 0 && i < N && j >= 0 && j < N && maze[i][j] != 0;
    }

    // APPROACH2 : I LIKE APPROACH1
    public void ratInMazeApproach2() {
        // N x N maze
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

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

    //https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
    public void knightsTour(int N) {
        // POSSIBLE KNIGHT MOVES : 2.5 KADAM KI CHAAL
        int arr[][] = new int[][]{
                {2, 1, -1, -2, -2, -1, 1, 2},
                {1, 2, 2, 1, -1, -2, -2, -1}
        };

        Integer[][] sol = new Integer[N][N];
        for (int x = 0; x < N; x++) {
            Arrays.fill(sol[x], -1);
        }
        sol[0][0] = 0;
        // Put this nextMoveNum for all next nodes
        int nextMoveNum = 1;
        boolean isTourPossible = knightsTourUtil(0, 0, nextMoveNum, sol, arr, N);

        // PRINTING THE SOLUTION
        if (isTourPossible) {
            for (int i = 0; i < sol.length; i++) {
                System.out.println(Arrays.asList(sol[i]));
            }
        } else {
            System.out.println("Solution does not exist");
        }
    }

    // BACKTRACKING HINT: DO SOME ACTION FOR CHILDREN BEFORE CALLING THEM RECURSIVELY
    // IF THAT RECURSIVE CALL PASSES, THEN RETURN TRUE. EX. PATH FOUND
    // IF IT FAILS, THEN REVERT THE ACTIONS YOU TOOK FOR THE CHILDREN EARLIER
    private boolean knightsTourUtil(int x, int y, int nextMoveNum, Integer[][] sol, int[][] arr, int N) {
        int k, next_x, next_y;
        // REACHED LAST POSITION WHICH WILL BE THE N*Nth POSITION
        if (nextMoveNum == N * N) {
            return true;
        }

        for (k = 0; k < 8; k++) {
            next_x = x + arr[0][k];
            next_y = y + arr[1][k];
            // SAFETY : NOT VISITED AND WITHIN BOUNDS
            // ISSAFE : CHECK IF NOT VISITED BY USING SOL AND CHECK IF IT IS WITHIN BOUNDS
            if (isSafe(next_x, next_y, sol, N)) {
                // Put nextMoveNum for sol[next_x][next_y], which is already received from parent
                sol[next_x][next_y] = nextMoveNum;

                // IF PATH IS FOUND IN THIS NEIGHBOUR next_x, next_y : THEN RETURN TRUE: SOL[][] WILL CONTAIN THE APPROPRIATE POSITION NUMBERS
                if (knightsTourUtil(next_x, next_y, nextMoveNum + 1, sol, arr, N)) {
                    return true;
                } else {
                    // IF THAT NEIGHBOUR (next_x, next_y) DOESNT RESULT IN A COMPLETE PATH, THEN BACKTRACK
                    // BACKTRACKING
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

    public static void main(String[] args) {
        nQueensProblem();
    }

    public static void nQueensProblem() {
        // Place n queens in non offensive positions in n x n chess board
        // VIDEO EXPLANATION @
        // https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
        int n = 4;
        Integer[][] sol = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(sol[i], 0);
        }

        // TO TRACK THE ROW FILLED
        int[] queenRows = new int[n];
        Arrays.fill(queenRows, -1);
        boolean isPossible = nQueensProblemUtil(0, sol, n, queenRows);

        // PRINTING THE SOLUTION
        if (isPossible) {
            for (int i = 0; i < n; i++) {
                System.out.println(Arrays.asList(sol[i]));
            }
        } else {
            System.out.println("CANNOT PLACE " + n + " QUEENS IN " + n + "x" + n + " CHESSBOARD");
        }
    }

    // col (SIMILAR TO NEXTMOVENUM IN KNIGHTSTOUR) keeps track of what column to provide Queen Qi
    // For Q0 : 0, For Q1 : 0+1=1, For Q2 : 1+1=2
    // sol : state matrix
    // queenRows : array to save row val allotted to each queen, obviously in
    // different columns because of col
    // queenRows[0]=2 : means Q0 is allotted col0 and row2
    private static boolean nQueensProblemUtil(int col, Integer[][] sol, int n, int[] queenRows) {
        // LAST QUEEN WAS PLACED AND NOW COL = N (QUEENS PLACED FROM 0 TO 3)
        if (col == n) {
            return true;
        }
        // FOR EACH QUEEN, CHECK EACH ROW FOR SAFETY. GREEDILY FILL IN THE FIRST SAFE ROW FOUND.
        // IF THAT ROW IN FUTURE DOESNT LEAD TO COMPLETE SOLUTION, THEN BACKTRACK
        for (int row = 0; row < n; row++) {
            // CHECK IF PLACING THE QUEEN IN CURRENT ROW IS NOT OFFENSIVE TO SOME OTHER QUEEN WHICH IS ALREADY PLACED
            if (isSafeMove(row, col, n, sol, queenRows)) {
                // ACTION WHICH MIGHT NEED BACKTRACKING
                sol[row][col] = 1;
                queenRows[col] = row;

                if (nQueensProblemUtil(col + 1, sol, n, queenRows)) {
                    return true;
                } else {
                    // BACKTRACKING
                    sol[row][col] = 0;
                    queenRows[col] = -1;
                }
            }
        }
        return false;
    }

    // CHECKING POSITION IS SAFE AND NOT OFFENSIVE TO ANOTHER QUEEN ALREADY PLACED BEFORE THE CURRENT QUEEN
    private static boolean isSafeMove(int row, int col, int n, Integer[][] sol, int[] queenRows) {
        // QUEEN MOVES HORIZONTALLY, VERTICALLY AND DIAGONALLY
        // STEP 0: VERTICAL CHECK: ALREADY TAKEN CARE BECAUSE EACH COLUMN IS BEING PROCESSED SEPARATELY

        // STEP 1: HORIZONTAL CHECK
        // CHECK IF CURRENT ROW IS OCCUPIED BY ANY OTHER QUEEN BEFORE THE CURRENT QUEEN
        for (int ind = 0; ind < n; ind++) {
            if (row == queenRows[ind]) {
                // THIS ROW IS ALREADY TAKEN, SO NOT SAFE, INCREMENT AND CHECK SAFETY FOR NEXT ROW
                return false;
            }
        }

        // STEP 2: UPPER LEFT DIAGONAL PATH
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

        // STEP 2: BOTTOM LEFT DIAGONAL PATH
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

    // Q: IF IT IS POSSIBLE TO COLOR A GRAPH WITH A LIMITED NUMBER OF COLORS
    // VERY SIMILAR TO N QUEENS PROBLEM: EASIER VERSION OF N QUEENS
    public void mColoringDecisionProblem(DirectedGraphAdjacencyList graph, int numberOfColors) {
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
        // int numberOfColors = 3;

        int v = graph.getV();
        List<Integer>[] adj = graph.getAdj();

        // colorVertex TRACKS WHICH COLOUR ASSIGNED TO WHICH VERTEX
        int[] colorVertex = new int[v];
        int src = 0;

        boolean isPossible = mColoringDecisionProblemUtil(numberOfColors, colorVertex, src, v, adj);

        // PRINTING THE SOLUTION
        if (isPossible) {
            System.out.println("It is possible");
            for (int i = 0; i < colorVertex.length; i++) {
                System.out.print(colorVertex[i] + " ");
            }
        } else {
            System.out.println("It is not possible");
        }
    }

    private boolean mColoringDecisionProblemUtil(int numberOfColors, int[] colorVertex, int src, int V, List<Integer>[] adj) {
        // BASE CASE
        // LIKE N QUEENS PROBLEM: ALL VERTICES 0 TO V - 1 HAVE BEEN COLOURED AND INDEX HAS REACHED V
        if (src == V) {
            return true;
        }

        for (int colorId = 1; colorId <= numberOfColors; colorId++) {
            // SAFETY : ANY CHILD OF THE CURRENT VERTEX SHOULD NOT HAVE THE SAME COLOR
            if (isSafeToPutColor(adj, colorVertex, src, colorId)) {

                // ACTION WHICH MIGHT NEED BACKTRACKING
                colorVertex[src] = colorId;

                // NOT CHECKING FOR SRC'S CHILDREN. JUST CHECKING FOR SRC + 1.
                if (mColoringDecisionProblemUtil(numberOfColors, colorVertex, src + 1, V, adj)) {
                    return true;
                } else {
                    // BACKTRACKING
                    colorVertex[src] = 0;
                }
            }
        }
        return false;
    }

    private boolean isSafeToPutColor(List<Integer>[] adj, int[] colorVertex, int src, int colorId) {
        // CHECK IF COLOR OF ANY CHILD OF THE SRC VERTEX IS SAME AS THIS COLORID
        for (int child : adj[src]) {
            if (colorVertex[child] == colorId) {
                return false;
            }
        }
        return true;
    }

    // MINIMUM NUMBER OF COLORS REQUIRED TO COLOUR GRAPH
    // SAME AS M COLOURING DECISION PROBLEM. BUT THE COLOR LIMIT IS EQUAL TO THE NUMBER OF VERTICES
    // BECAUSE WORST CASE, ALL VERTICES ARE OF DIFFERENT COLOR
    // WHILE DFSing OVER THE COLORS, THE COMBINATION WHICH RESULTS IN DECISION = TRUE, BREAKS THE DFS
    // ONCE BROKEN, TRAVERSE OVER THE COLOR ASSIGNED ARRAY AND FIND THE MAXIMUM COLOR ID ASSIGNED TO ANY ROW,
    // THAT WILL BE THE MINIMUM NUMBER OF COLORS REQUIRED
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

        // colorVertex TRACKS WHICH COLOUR ASSIGNED TO WHICH VERTEX
        int[] colorVertex = new int[V];

        // DIFFERENCE BETWEEN DECISION AND OPTIMIZATION
        // IN OPTIMIZATION THE LIMIT IS V FOR WORST CASE ALL VERTICES HAVING DIFFERENT COLOR
        int colorLimit = V;
        int src = 0;

        mColoringOptimizationProblemUtil(colorLimit, colorVertex, src, V, adj);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < V; i++) {
            max = Math.max(max, colorVertex[i]);
        }
        System.out.println("MINIMUM NUMBER OF COLORS REQUIRED TO COLOUR GRAPH IS " + max);
    }

    private boolean mColoringOptimizationProblemUtil(int numberOfColors, int[] colorVertex, int src, int V, List<Integer>[] adj) {
        // BASE CASE
        // LIKE N QUEENS PROBLEM: ALL VERTICES 0 TO V - 1 HAVE BEEN COLOURED AND INDEX HAS REACHED V
        if (src == V) {
            return true;
        }

        for (int color = 1; color <= numberOfColors; color++) {
            // SAFETY : ANY CHILD OF THE CURRENT VERTEX SHOULD NOT HAVE THE SAME COLOR
            if (isSafeToPutColor(adj, colorVertex, src, color)) {

                // ACTION WHICH MIGHT NEED BACKTRACKING
                colorVertex[src] = color;

                if (mColoringOptimizationProblemUtil(numberOfColors, colorVertex, src + 1, V, adj)) {
                    return true;
                } else {
                    // BACKTRACKING
                    colorVertex[src] = 0;
                }
            }
        }
        return false;
    }

    static int maxDiff = Integer.MAX_VALUE;

    // #REVISIT
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