package company.google.oa;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/subarray-sum-equals-k/
public class CountSubarrayWithGivenSum {
    public static void main(String[] args) {
        //int[] arr = {1, 1, 1};
        //int[] arr = {3, 4, 7, 2, -3, 1, 4, 2, 1};
        //int[] arr = {0,0,0,0,0,0,0,0,0,0};
        int target = 7;

        int[] arr = {3, 4, 1, 2, 3, 2, 4, 1};
        System.out.println(subarraySumForPositiveIntegersWithSlidingWindow(arr, 7));
    }

    // FAILS FOR NEGATIVE NUMBERS IN LIST
    // SlidingWindow
    private static int subarraySumForPositiveIntegersWithSlidingWindow(int[] arr, int k) {
        if (arr.length == 1 && k != arr[0]) {
            return 0;
        }

        int i = 0;
        int j = 0;
        int n = arr.length;
        int count = 0;

        int windowSum = 0;
        // <= : to handle last index included sum = k
        while (j <= n) {
            while (windowSum >= k) {
                if (windowSum == k) {
                    count++;
                }
                windowSum -= arr[i];
                i++;
            }
            if (j < n) {
                windowSum += arr[j];
            }
            j++;
        }

        return count;
    }

    //DOESNT WORK FOR int[] arr = {0,0,0,0,0,0,0,0,0,0}; AND TARGET = 0
    //ONLY WORKS FOR NON - ZERO POSITIVES : WHERE CUMSUM IS ONLY INCREASING
    // HELPS TO UNDERSTAND NEXT SOLUTION
    public static int subarraySumForPositiveIntegers(int[] arr, int k) {
        int count = 0;
        int n = arr.length;
        int cumSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            cumSum += arr[i];
            if (map.containsKey(cumSum - k)) {
                count++;
            }
            map.put(cumSum, i);
        }
        return count;
    }

    // Works for all integers : Uses Cumulative Sum
    // #REVISIT
    public static int subarraySumForAllIntegers(int[] arr, int k) {
        int count = 0;
        // Rather than storing index, Value in this map is the number of times the key has satisfied the sum = k requirement
        // Default = 1, otherwise +++
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cumSum = 0;
        for (int i = 0; i < arr.length; i++) {
            cumSum += arr[i];
            if (map.containsKey(cumSum - k)) {
                count += map.get(cumSum - k);
            }
            map.put(cumSum, map.getOrDefault(cumSum, 0) + 1);
        }
        return count;
    }
}
