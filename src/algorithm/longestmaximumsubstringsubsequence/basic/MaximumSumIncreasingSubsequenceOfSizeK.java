package algorithm.longestmaximumsubstringsubsequence.basic;

import java.util.Arrays;

//#REVISIT
// L.I.S BUT FROM BACK WITH LEN AND SUM DP ARRAYS
// GOING FROM FRONT DOESNT WORK BECAUSE
// SUM OF LAST THREE INCREASING VALUES WILL BE MORE THAN SUM OF FIRST THREE INCREASING VALUE
public class MaximumSumIncreasingSubsequenceOfSizeK {
    public static void main(String[] args) {
//        int[] nums = new int[]{8, 5, 9, 10, 5, 6, 21, 8};
        int[] nums = new int[]{2, 5, 3, 9, 15, 33, 6, 18, 20};
        System.out.println(findMaximumSumIncreasingSubsequenceOfSizeK(nums, 4));
    }

    private static int findMaximumSumIncreasingSubsequenceOfSizeK(int[] arr, int k) {
        int n = arr.length;
        int[] lenDP = new int[n];
        int[] sumDP = new int[n];

        Arrays.fill(lenDP, 1);
        for (int i = 0; i < n; i++) {
            sumDP[i] = arr[i];
        }
        int maxSum = 0;
        // Start i from second last to 0
        for (int i = n - 2; i >= 0; i--) {
            // For each i start j from last to i
            for (int j = n - 1; j > i; j--) {
                if (arr[j] > arr[i]) {
                    lenDP[i] = Math.max(lenDP[i], 1 + lenDP[j]);
                    // Add current num to sumDP[j]
                    sumDP[i] = Math.max(sumDP[i], arr[i] + sumDP[j]);

                    maxSum = Math.max(maxSum, sumDP[i]);

                    // Length of increasing subsequence is reached to k
                    if (lenDP[i] == k) {
                        break;
                    }
                }
            }
        }
        return maxSum;
    }
}