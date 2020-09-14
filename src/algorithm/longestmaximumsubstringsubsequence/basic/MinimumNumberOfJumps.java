package algorithm.longestmaximumsubstringsubsequence.basic;

import java.util.Arrays;

public class MinimumNumberOfJumps {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(findMinimumJumps(arr));
    }

    private static int findMinimumJumps(int[] arr) {
        if (arr[0] == 0) {
            return Integer.MAX_VALUE;
        }
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n - 1; i++) {
            for (int j = 0; j < i; j++) {
                if (j + arr[j] >= i && dp[j] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[n - 1];
    }
}
