package company.google.easy;

public class StringFormedUsingABC {
    public static void main(String[] args) {
        // CONSTRAINTS: at most 1 b and 2 c are allowed
        int n = 3;
        int bCount = 1;
        int cCount = 2;
        int[][][] dp = new int[n + 1][bCount + 1][cCount + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        int count = findStringFormedUsingABC(n, bCount, cCount, dp);
        System.out.println(count);
    }

    private static int findStringFormedUsingABC(int n, int bCount, int cCount, int[][][] dp) {
        if (bCount < 0 || cCount < 0) {
            return 0;
        }
        if (n == 0 || (bCount == 0 && cCount == 0)) {
            return 1;
        }

        if (dp[n][bCount][cCount] != -1) {
            return dp[n][bCount][cCount];
        }

        int res = findStringFormedUsingABC(n - 1, bCount, cCount, dp);
        res += findStringFormedUsingABC(n - 1, bCount - 1, cCount, dp);
        res += findStringFormedUsingABC(n - 1, bCount, cCount - 1, dp);

        dp[n][bCount][cCount] = res;
        return res;
    }
}
