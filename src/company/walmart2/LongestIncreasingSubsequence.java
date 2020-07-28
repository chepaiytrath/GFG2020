package company.walmart2;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr = new int[] { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
        System.out.println(lengthOfLISDPWithBinarySearch(arr));
    }

    private static int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[0];
    }

    private static int lengthOfLISDPWithBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num < dp[0]) {
                dp[0] = num;
            } else if (num > dp[len - 1]) {
                dp[len] = num;
                len++;
            } else {
                int index = binarySearch(dp, num, 0, len - 1);
                dp[index] = num;
            }
        }
        return len;
    }

    private static int binarySearch(int[] dp, int target, int i, int j) {
        //NOT ON THE SAME INDEX
        //ALSO NOT ON THE NEXT INDEX
        while(j - i > 1){
            int midInd = i + (j - 1)/2;
            int mid = dp[midInd];
            if(mid > target){
                j = mid;
            }else{
                i = mid;
            }
        }
        return j;
    }
}