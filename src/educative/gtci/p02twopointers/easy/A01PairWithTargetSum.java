package educative.gtci.p02twopointers.easy;

import java.util.Arrays;
import java.util.List;

public class A01PairWithTargetSum {
    public static void main(String[] args) {
        pair();
    }

    // SORTED ARRAY
    private static void pair() {
        List<Integer> res = null;
        int[] arr = new int[]{2, 5, 9, 11};
        int target = 11;

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                res = Arrays.asList(left, right);
                break;
            }
        }
        System.out.println(res);
    }
}
