package algorithm.arraysum;

import java.util.Arrays;
import java.util.HashSet;

public class TripletWithGivenSum {
    public static void main(String[] args) {
        int[] arr = new int[]{12, 3, 4, 1, 6, 9};
        int k = 24;
        if (!printTripletSortedArrayTwoPointer(arr, k)) {
            System.out.println("Triplets not found");
        }
    }

    //Sorted array + Two pointer technique
    private static boolean printTripletSortedArrayTwoPointer(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            int lo = i + 1;
            int hi = n - 1;
            while (lo < hi) {
                int sum = arr[lo] + arr[hi] + arr[i];
                if (sum == k) {
                    System.out.println(arr[i] + " " + arr[lo] + " " + arr[hi]);
                    return true;
                } else if (sum < k) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return false;
    }

    //Unsorted Array + Hashing technique
    private static boolean printTripletUnsortedArrayHashing(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            int dupletSum = k - arr[i];
            int j = i + 1;
            HashSet<Integer> set = new HashSet<>();
            while (j < n) {
                if (set.contains(dupletSum - arr[j])) {
                    System.out.println(arr[i] + " " + arr[j] + " " + (dupletSum - arr[j]));
                    return true;
                }
                set.add(arr[j++]);
            }
        }
        return false;
    }
}
