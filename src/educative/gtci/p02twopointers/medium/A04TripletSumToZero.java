package educative.gtci.p02twopointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A04TripletSumToZero {
    public static void main(String[] args) {
        tripletSumToZero();
    }

    private static void tripletSumToZero() {
        int[] arr = new int[]{-5, 2, -1, -2, 3};
        List<List<Integer>> res = new ArrayList<>();
        int n = arr.length;
        Arrays.sort(arr);
        for (int i = 0; i <= n - 3 && arr[i] <= 0; i++) {
            int val = arr[i];

            int dupletSum = 0 - val;
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum > dupletSum) {
                    right--;
                } else if (sum < dupletSum) {
                    left++;
                } else {
                    res.add(Arrays.asList(val, arr[left], arr[right]));
                    right--;
                    left++;
                }
            }
        }

        System.out.println(res);
    }
}
