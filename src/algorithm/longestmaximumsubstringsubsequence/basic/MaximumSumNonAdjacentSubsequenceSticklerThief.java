package algorithm.longestmaximumsubstringsubsequence.basic;

public class MaximumSumNonAdjacentSubsequenceSticklerThief {
        //aximum sum without adjacents
    public static void main(String[] args) {
//        int[] arr = new int[]{5, 3, 4, 11, 2};
        int[] arr = new int[]{6, 7, 1, 3, 8, 2, 4};
        System.out.println(findMaxStolenValue(arr));
    }

    private static int findMaxStolenValue(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = arr[1];
        int maxSteal = 0;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], arr[i] + dp[j]);
                maxSteal = Math.max(maxSteal, dp[i]);
            }
        }
        return maxSteal;
    }
}
