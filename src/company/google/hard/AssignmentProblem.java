package company.google.hard;

import java.util.Arrays;

public class AssignmentProblem {
    public static void main(String[] args) {

    }

    public void jobAssignmentDFSBacktracking() {
        int n = 4;
//        int[][] jobs = new int[][]{{9, 2, 7, 8}, {6, 4, 3, 7}, {5, 8, 1, 8}, {7, 6, 9, 4}};
        int[][] jobs = new int[][]{{80, 40, 50, 46}, {40, 70, 20, 25}, {30, 10, 20, 30}, {35, 20, 25, 30}};
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int[] sol = new int[n];
        Arrays.fill(sol, -1);
        int index = 0;
        jobAssignmentDFSBacktrackingUtil(index, n, dp, sol, jobs);
        System.out.println(minCost);
    }

    static int minCost = Integer.MAX_VALUE;

    private void jobAssignmentDFSBacktrackingUtil(int index, int n, int[] dp, int[] sol, int[][] jobs) {
        if (index == n) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += sol[i];
            }
            minCost = Math.min(minCost, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isSafeJob(i, dp, jobs)) {
                dp[index] = i;
                sol[index] = jobs[i][index];
                jobAssignmentDFSBacktrackingUtil(index + 1, n, dp, sol, jobs);
                dp[index] = -1;
                sol[index] = -1;
            }
        }
    }

    private boolean isSafeJob(int row, int[] dp, int[][] jobs) {
        for (int rowTaken : dp) {
            if (row == rowTaken) {
                return false;
            }
        }
        return true;
    }
}
