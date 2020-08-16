package educative.gtci.p02twopointers.medium;

import java.util.Arrays;

public class A05TripletSumCloseToTarget {
    public static void main(String[] args) {
        tripletSumClose();
    }

    private static void tripletSumClose() {
//        int[] arr = new int[]{-2, 0, 1, 2};
//        int target = 2;

//        int[] arr = new int[]{-3, -1, 1, 2};
//        int target = 1;

        int[] arr = new int[]{1, 0, 1, 1};
        int target = 100;

        Arrays.sort(arr);

        int n = arr.length;
        int diff = Integer.MAX_VALUE;
        int res = 0;

        for (int i = 0; i <= n-3; i++) {
            int val = arr[i];
            int dupletSum = target - val;
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = arr[left] + arr[right];
                int tripletSum = val + sum;
                if (Math.abs(tripletSum - target) < diff) {
                    diff = Math.abs(tripletSum - target);
                    res = tripletSum;
                }
                if (sum < dupletSum) {
                    left++;
                } else if (sum > dupletSum) {
                    right--;
                } else {
                    left++;
                    right--;
                }
            }
        }
        System.out.println(res);
    }
}