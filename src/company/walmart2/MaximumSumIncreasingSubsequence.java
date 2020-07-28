package company.walmart2;

public class MaximumSumIncreasingSubsequence {
    public static void main(String[] args) {
        // int[] arr = new int[] { 10, 5, 4, 3 };
        // int[] arr = new int[] { 1, 101, 2, 3, 100, 4, 5 };
        int[] arr = new int[] { 3, 4, 5, 10 };
        System.out.println(findMaximumSumOfIncreasingSubsequence(arr));
    }

    private static int findMaximumSumOfIncreasingSubsequence(int nums[]) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], nums[i] + dp[j]);
                }
            }
        }
        return dp[0];
    }
}