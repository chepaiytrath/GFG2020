package algorithm.basictechniques.arraysumcumulativesum.subsetsum;

public class SubsetSum {
    public static void main(String[] args) {
        /*int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;*/

        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 30;

        System.out.println(checkIfSubsetSumPossible(set, sum));
    }

    private static boolean checkIfSubsetSumPossible(int[] set, int sum) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : set) {
            // CHECK IF EACH NUM CAN RESULT IN THE TARGET SUM FROM 1 TO SUM
            if (num <= sum) {
                for (int i = sum; i >= num; i--) {
                    dp[i] = dp[i - num] || dp[i];
                    if (dp[sum]) {
                        return true;
                    }
                }
            }
        }

        return dp[sum];
    }
}
