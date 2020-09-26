package algorithm.basictechniques.arraysumcumulativesum.duplettripletsum;

import java.util.Arrays;

public class A05TripletSumClosestToAGivenSum {
    public static void main(String[] args) {
//        int[] arr = new int[]{-1, 2, 1, -4};
//        int[] arr = new int[]{1, 2, 3, 4, -5};
//        int[] arr = new int[]{-7, 9, 8, 3, 1, 1};
        int[] arr = new int[]{-7, 9, 8, 3, 1, 1};
        Arrays.sort(arr);
        System.out.println(threeSumClosest(arr, 2));
    }

    private static int findSumOfTripletWithSumClosestTo(int[] arr, int tripletTarget) {
        int n = arr.length;
        int bestTripletSum = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int tripletSum = 0;
            int lo = i + 1;
            int hi = n - 1;
            int bestDupletSum = Integer.MAX_VALUE;
            int dupletTarget = tripletTarget - arr[i];
            while (hi > lo) {
                int left = arr[lo];
                int right = arr[hi];
                int dupletSum = left + right;
                if (Math.abs(dupletSum - dupletTarget) < Math.abs(bestDupletSum - dupletTarget)) {
                    bestDupletSum = dupletSum;
                }
                if (dupletSum > dupletTarget) {
                    hi--;
                } else if (dupletSum < dupletTarget) {
                    lo++;
                }
            }
            tripletSum = arr[i] + bestDupletSum;
            if (Math.abs(tripletSum - tripletTarget) < Math.abs(bestTripletSum - tripletTarget)) {
                bestTripletSum = tripletSum;
            }
        }
        return bestTripletSum;
    }


    private static int threeSumClosest(int[] arr, int target) {
        int n = arr.length;
        int bestsum = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            int curr = arr[i];
            int start = i + 1;
            int end = n - 1;

            while (start < end) {
                int tripletsum = curr + arr[start] + arr[end];
                if (Math.abs(tripletsum - target) < Math.abs(bestsum - target)) {
                    bestsum = tripletsum;
                }

                if (tripletsum > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return bestsum;
    }
}
