package rough;
//https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
public class maxji {
    public static void main(String[] args) {
        int[] arr = new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1};
        System.out.println(findMaxji(arr));
    }

    private static int findMaxji(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[2][n];

        java.util.Arrays.fill(dp[0], 1);
        for(int i = 0; i < n; i++){
            dp[1][i] = i;
        }

        dp[0][0] = 1; // Length of LIS
        dp[1][0] = 0; // First index of LIS

        int maxLen = -1;
        int lastIndex = -1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (1 + dp[0][j] > dp[0][i]) {
                        dp[0][i] = dp[0][j] + 1;
                        dp[1][i] = dp[1][j];
                    }
                }
            }
            if (maxLen < dp[0][i]) {
                maxLen = dp[0][i];
                lastIndex = i;
            }
        }

        return lastIndex - dp[1][lastIndex];
    }
}
