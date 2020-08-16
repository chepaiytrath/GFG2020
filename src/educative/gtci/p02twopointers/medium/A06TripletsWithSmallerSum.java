package educative.gtci.p02twopointers.medium;

import java.util.Arrays;

public class A06TripletsWithSmallerSum {
    public static void main(String[] args) {
        countTriplets();
    }

    private static void countTriplets() {
        int[] arr = new int[]{-1, 4, 2, 1, 3};
        int target = 5;
        Arrays.sort(arr);

        int n = arr.length;
        int count = 0;
        for (int i = 0; i <= n - 3; i++) {
            int val = arr[i];
            int dupletSum = target - val;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum >= dupletSum) {
                    right--;
                } else if (sum < dupletSum) {
                    // Tricky part
                    // For this combination of i, left, right: arr[right] can be replaced by anything between left and right
                    count += right - left;
                    left++;
                }
            }
        }
        System.out.println(count);
    }
}
