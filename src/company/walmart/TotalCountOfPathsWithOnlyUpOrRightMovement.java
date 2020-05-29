package company.walmart;

public class TotalCountOfPathsWithOnlyUpOrRightMovement {
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] memo = new int[mat.length][mat[0].length];
        System.out.println(findTotalNumberOfPathsRecursively(mat, mat.length - 1, mat[0].length - 1, memo));
        System.out.println(findTotalNumberOfPathsWithDP(mat));

    }

    private static int findTotalNumberOfPathsRecursively(int[][] mat, int i, int j, int[][] memo) {
        //Done for reducing overlapping subwraps
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        if (i == 0 && j == 0) {
            return 0;
        }
        if (i == 0 || j == 0) {
            return 1;
        }
        memo[i][j] = findTotalNumberOfPathsRecursively(mat, i - 1, j, memo) + findTotalNumberOfPathsRecursively(mat, i, j - 1, memo);
        return memo[i][j];
    }

    private static int findTotalNumberOfPathsWithDP(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] dp = new int[row][col];
        for (int i = 1; i < row; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[row - 1][col - 1];
    }
}