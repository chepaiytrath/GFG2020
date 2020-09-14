package algorithm.puzzles;

import java.util.Arrays;

// OPPOSITE QUESTION : MAXIMUM CUT SEGMENTS WHERE YOU USE MIN_VALUE AND MATH.MAX
//https://practice.geeksforgeeks.org/problems/coin-change-minimum-number-of-coins/1/?track=amazon-dynamic-programming&batchId=192
class CoinChange {
    public static void main(String[] args) {
        int[] coins = {3, 6, 3};
        int value = 5;
        System.out.println(minimumNumberOfCoins(coins, value));
    }

    // -1 if not possible
    public static long minimumNumberOfCoins(int coins[], int value) {
        int[] dp = new int[value + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // KEEP MAX VALUE FOR A VALUE NOT ATTAINABLE
        for (int i = 1; i <= value; i++) {
            for (int coin : coins) {
                // IF PREV INDEX IS OOB OR ELSE IF ITS VALUE IS MAX_VAL, IGNORE IT
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    int val = 1 + dp[i - coin];
                    dp[i] = Math.min(dp[i], val);
                }
            }
        }
        return dp[value] == Integer.MAX_VALUE ? -1 : dp[value];
    }
}