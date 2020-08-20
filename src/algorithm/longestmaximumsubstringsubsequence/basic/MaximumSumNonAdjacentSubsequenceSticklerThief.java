package algorithm.longestmaximumsubstringsubsequence.basic;

public class MaximumSumNonAdjacentSubsequenceSticklerThief {
    // Maximum sum without adjacents
    public static void main(String[] args) {
//        int[] arr = new int[]{5, 3, 4, 11, 2};
//        int[] arr = new int[]{6, 7, 1, 3, 8, 2, 4};
        int[] arr = {5, 5, 10, 100, 10, 5};

        System.out.println(findMaxStolenValueBetter(arr));
    }

    private static int findMaxStolenValueBetter(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxAmt = 0;

        if (n == 1) {
            return arr[0];
        }
        if (n == 2) {
            return Math.max(arr[0], arr[1]);
        }
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
            maxAmt = Math.max(maxAmt, dp[i]);
        }
        return maxAmt;
    }
}
