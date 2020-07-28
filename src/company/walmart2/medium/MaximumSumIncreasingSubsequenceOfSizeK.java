package company.walmart2.medium;

public class MaximumSumIncreasingSubsequenceOfSizeK {
    public static void main(String[] args) {
        int[] nums = new int[] { 8, 5, 9, 10, 5, 6, 21, 8 };
        System.out.println(findMaximumSumIncreasingSubsequenceOfSizeK(nums, 3));
    }

    private static int findMaximumSumIncreasingSubsequenceOfSizeK(int[] nums, int k) {
        int n = nums.length;
        int[] sumdp = new int[n];
        int[] lendp = new int[n];
        for (int i = 0; i < n; i++) {
            sumdp[i] = nums[i];
        }
        int maxSum = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    lendp[i] = Math.max(lendp[i], 1 + lendp[j]);
                    if (lendp[i] == k) {
                        break;
                    }
                    sumdp[i] = Math.max(sumdp[i], nums[i] + sumdp[j]);
                }
            }
            maxSum = Math.max(maxSum, sumdp[i]);
        }
        return maxSum;
    }
}