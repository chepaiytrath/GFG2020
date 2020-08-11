package algorithm.basictechniques.cumulativesum;

import java.util.HashMap;
import java.util.Map;

public class SubarrayWithGivenSum {
    public static void main(String[] args) {
        //int[] arr = { 10, 2, -2, -20, 10 };
        //int[] arr = { 1, 4, 20, 3, 10, 5 };
        int[] arr = {3, 1, 1};

        //findSubarrayWithGivenSumAllIntegers(arr, 2);
        findSubarrayWithGivenSumOnlyPositiveIntegers2(arr, 2);
    }

    //SINGLE ARRAY ELEMENT IS NOT A CONTINUOUS SUBARRAY : SO THAT IS NOT CONSIDERED
    private static void findSubarrayWithGivenSumOnlyPositiveIntegers2(int[] arr, int target) {
        // SOLUTION: https://www.geeksforgeeks.org/find-subarray-with-given-sum/
        // WINDOW SLIDING APPROACH : O(n)
        // ONLY HANDLES POSITIVE INTEGERS

        // SAMPLE INPUT
        // int[] arr = { 1, 4, 20, 3, 10, 5 };

        int i = 0;
        int j = 0;
        int start = -1;
        int end = -1;
        int n = arr.length;
        int sum = 0;

        // Used Less than equals '<=' (Not Less Than '<') to handle edge case of subarray being present at last index
        // Have AIOOB check separately
        while (j <= n) {
            while (sum > target && i < n) {
                sum -= arr[i];
                i++;
            }
            if (sum == target) {
                start = i;
                // Elements between 'start' and 'Current index - 1' sum up to target sum because current element has not yet been added to the sum
                end = j - 1;
                break;
            }
            if (j < n) {
                sum += arr[j];
            }
            j++;
        }

        if (end != -1) {
            System.out.println("Subarray found between index " + start + " and " + end);
            for (int idx = start; idx <= end; idx++) {
                System.out.print(arr[idx] + " ");
            }
        } else {
            System.out.print("No subarray found");
        }
    }

    //PRESUM / CUMULATIVE SUM TECHNIQUE
    private static void findSubarrayWithGivenSumAllIntegers(int[] arr, int target) {
        // SOLUTION:
        // https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
        // WINDOW SLIDING APPROACH : O(n)
        // HANDLES ALL INTEGERS

        // SAMPLE INPUT
        //-22
        // int[] arr = { 10, 2, -2, -20, 10 };


        int start = -1;
        int end = -1;
        int sum = 0;
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // To handle the case when elements from 0 to i sum up to target
            if (sum - target == 0) {
                start = 0;
                end = i;
                break;
            }
            if (sumIndexMap.containsKey(sum - target)) {
                start = sumIndexMap.get(sum - target) + 1;
                end = i;
                break;
            }
            sumIndexMap.put(sum, i);
        }
        if (end != -1) {
            System.out.println("Subarray found between index " + start + " and " + end);
            for (int i = start; i <= end; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            System.out.print("No subarray found");
        }
    }
}
