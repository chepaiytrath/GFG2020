package algorithm.basictechniques.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class SubarayWithGivenSum {
    public static void main(String[] args) {
//        int[] arr = { 10, 2, -2, -20, 10 };
        int[] arr = {4,5,-2,6,-10};
//        int[] arr = {1, 4, 20, 3, 10, 5};

        //findSubarrayWithGivenSumAllIntegers
        findSubarrayWithGivenSumAllIntegers(arr, -4);
    }

    private static void findSubarrayWithGivenSumOnlyPositiveIntegers(int[] arr, int target) {
        // SOLUTION: https://www.geeksforgeeks.org/find-subarray-with-given-sum/
        // WINDOW SLIDING APPROACH : O(n)
        // ONLY HANDLES POSITIVE INTEGERS

        // SAMPLE INPUT
        // int[] arr = { 1, 4, 20, 3, 10, 5 };

        int start = 0;
        int end = -1;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            while (sum > target) {
                sum -= arr[start];
                start++;
            }
            if (sum == target) {
                end = i - 1;
                break;
            }
            sum += arr[i];
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

    private static void findSubarrayWithGivenSumAllIntegers(int[] arr, int target) {
        // SOLUTION:
        // https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
        // WINDOW SLIDING APPROACH : O(n)
        // HANDLES ALL INTEGERS

        // SAMPLE INPUT
        // int[] arr = { 10, 2, -2, -20, 10 };


        int start = -1;
        int end = -1;
        int sum = 0;
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
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
