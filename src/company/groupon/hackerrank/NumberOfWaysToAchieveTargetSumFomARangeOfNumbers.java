package company.groupon.hackerrank;

public class NumberOfWaysToAchieveTargetSumFomARangeOfNumbers {
    public static void main(String[] args) {
        int n = 5, k = 3;
        System.out.println(ways2(n, k));
    }

    private static int ways(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - i];
                }
            }
        }
        return dp[k][n];
    }

    static int count = 0;

    private static int ways2(int n, int k) {
        getWays(n, k);
        return count;
    }

    private static void getWays(int n, int k) {
        if (n == 0) {
            count++;
            return;
        }

        for (int i = Math.min(n, k); i >= 1; i--) {
            getWays(n - i, i);
        }
    }
}
