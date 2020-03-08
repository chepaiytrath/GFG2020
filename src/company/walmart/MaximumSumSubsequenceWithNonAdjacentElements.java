//Dynamic Programming
//Stickler Thief
//https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
package company.walmart;

public class MaximumSumSubsequenceWithNonAdjacentElements {
    public static void main(String[] args) {
        int[] arr = {5, 5, 10, 100, 10, 5};
        System.out.println(findMaximumSumSubsequenceWithNonAdjacentElements(arr));
    }

    public static int findMaximumSumSubsequenceWithNonAdjacentElements(int[] arr) {
        int[][] dp = new int[2][arr.length];
        dp[0][0] = arr[0];
        dp[1][0] = 0;

        for (int j = 1; j < arr.length; j++) {
            dp[1][j] = dp[0][j - 1];
            dp[0][j] = Math.max(dp[0][j - 1], dp[1][j - 1] + arr[j]);
        }
        return dp[0][arr.length - 1];
    }
}