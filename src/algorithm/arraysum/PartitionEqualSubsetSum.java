package algorithm.arraysum;
// https://leetcode.com/problems/partition-equal-subset-sum/
class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

    // LOGIC TO FIND SUBSET SUM EQUAL TO TARGET:
    // BOOLEAN DP[TARGET+1] : TO STORE IF A PARTICULAR SUM(DP TRACKS THAT) CAN BE ACHIEVED BY COMBINATION OF NUMBERS IN WINDOW(TRAVERSE THE ARR)
    // EX. 1,3,5 : CAN GIVE SUMS OF 1 OR 3 OR 5 OR 4 OR 6 OR 8 OR 9 BY VARIOUS COMBINATIONS
    // DP[0] = TRUE : BECAUSE A BLANK SUBARRAY WILL GIVE A SUM OF 0
    // TRAVERSE NUMBERS ARRAY LEFT TO RIGHT. TRAVERSE DP RIGHT TO LEFT
    // FOR EACH INDEX IN DP CHECK IF INDEX - CURR NUM IN ARRAY = TRUE
    // EXPLANATION: EX. 1,5,11,5 AND TARGET IS 11
    // TRAVERSE DP FROM 11 TO 1 AND COMPARE TO FIRST ELEMENT (1) : 11 - 1 = 10. THIS CHECKS IF ADDING ONE CAN GIVE ME 11.
    // SO CHECK IF WITHOUT ADDING 1, THE REMAINING IS AN ACHIEVABLE SUM TILL NOW. 10 IS FALSE SO 11 WILL REMAIN FALSE FOR THIS ITERATION
    // TILL 2 EVERYTHING WILL BE FALSE
    // NOW FOR 1 IN DP COMPARED TO 1 IN ARRAY: 1 - 1 = 0 (WHICH IS TRUE). SO 1 IN DP IS ALSO MARKED TRUE: WHY? : ADDING 1 WILL GIVE ME 1 IF ITS ABSENCE GIVES ME TRUE


    // LOGIC FOR THIS PROBLEM: ODD NUMBERED SUM CANNOT BE DIVIDED INTO TWO EQUAL SUM SUBSETS
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                if (dp[i] == true) {
                    continue;
                }
                dp[i] = dp[i - num];
                if (dp[target] == true) {
                    return true;
                }
            }
        }
        return dp[target];
    }

}