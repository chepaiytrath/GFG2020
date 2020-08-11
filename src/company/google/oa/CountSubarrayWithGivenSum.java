package company.google.oa;

import java.util.HashMap;
import java.util.Map;

public class CountSubarrayWithGivenSum {
    public static void main(String[] args) {
//        int[] arr = {1, 1, 1};
        int[] arr = {3, 4, 7, 2, -3, 1, 4, 2};

//        int[] arr = {0,0,0,0,0,0,0,0,0,0};

        System.out.println(subarraySumForAllIntegers(arr, 7));
    }

    // FAILS FOR NEGATIVE NUMBERS IN LIST
    private static int subarraySumForPositiveIntegersWithSlidingWindow(int[] arr, int k) {
        if (arr.length == 1 && k != arr[0]) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int count = 0;
        int n = arr.length;
        int sum = 0;
        while (j <= n) {
            if (sum >= k && i < n) {
                while (sum >= k) {
                    if (sum == k) {
                        count++;
                    }
                    if (i < n) {
                        sum = sum - arr[i];
                    }
                    i++;
                }
            }
            if (j < n) {
                sum += arr[j];
            }
            j++;
        }
        return count;
    }

    //DOESNT WORK FOR int[] arr = {0,0,0,0,0,0,0,0,0,0}; AND TARGET = 0
    //ONLY WORKS FOR NON - ZERO POSITIVES : HELPS TO UNDERSTAND NEXT SOLUTION
    public static int subarraySumForPositiveIntegers(int[] arr, int k) {
        int count = 0;
        int n = arr.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                count++;
            }
            map.put(sum, i);
        }
        return count;
    }

    public static int subarraySumForAllIntegers(int[] arr, int k) {
        int count = 0;
        // Value in this map is the number of times it has satisfied the sum = k requirement
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int cumSum = 0;
        for(int i = 0; i < arr.length; i++){
            cumSum += arr[i];
            if(map.containsKey(cumSum - k)){
                count += map.get(cumSum - k);
            }
            map.put(cumSum, map.getOrDefault(cumSum, 0) + 1);
        }
        return count;
    }
}
