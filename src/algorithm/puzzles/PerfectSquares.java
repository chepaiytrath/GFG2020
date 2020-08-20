package algorithm.puzzles;

//https://leetcode.com/problems/perfect-squares/
// SIMILAR TO COIN CHANGE
public class PerfectSquares {
    public static void main(String[] args) {
        int num = 16;
        System.out.println(findNumberOfPerfectSquaresThatAddToNum(num));
    }

    private static int findNumberOfPerfectSquaresThatAddToNum(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 2; i <= num; i++) {
            int minCount = Integer.MAX_VALUE;
            // Each j will give a perfect square
            for (int j = 1; (j * j) <= i; j++) {
                int ps = j * j;
                minCount = Math.min(minCount, 1 + dp[i - ps]);
            }
            dp[i] = minCount;
        }
        return dp[num];
    }
}
