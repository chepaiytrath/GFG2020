package algorithm.puzzles;

class PaperCutProblem {

    static int dp[][] = new int[300][300];

    public static void main(String[] args) {
        int m = 30;
        int n = 35;
        System.out.println(findMinimumNumberOfSquares(m, n));
    }

    private static int findMinimumNumberOfSquares(int m, int n) {
        int horizontalMin = Integer.MAX_VALUE;
        int verticalMin = Integer.MAX_VALUE;

        if (m == n) {
            return 1;
        }
        if(dp[m][n] != 0){
            return dp[m][n];
        }

        for (int i = 1; i <= m / 2; i++) {
            horizontalMin = Math.min(findMinimumNumberOfSquares(i, n) + findMinimumNumberOfSquares(m - i, n), horizontalMin);
        }
        for (int j = 1; j <= n / 2; j++) {
            verticalMin = Math.min(findMinimumNumberOfSquares(m, j) + findMinimumNumberOfSquares(m, n - j), verticalMin);
        }
        dp[m][n] = Math.min(horizontalMin, verticalMin);;
        return dp[m][n];
    }
}