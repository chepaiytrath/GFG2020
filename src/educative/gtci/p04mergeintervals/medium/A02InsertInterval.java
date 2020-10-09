package educative.gtci.p04mergeintervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A02InsertInterval {
    public static void main(String[] args) {
        insertInterval();
    }

    private static void insertInterval() {
//        int[][] arr = new int[][]{{1, 3}, {5, 7}, {8, 12}};
//        int[] newInt = new int[]{4, 6};
//
//        int[][] arr = new int[][]{{1, 3}, {5, 7}, {8, 12}};
//        int[] newInt = new int[]{4, 10};
        int[][] arr = new int[][]{{2, 3}, {5, 7}};
        int[] newInt = new int[]{1, 4};

        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        while (i < n && arr[i][1] < newInt[0]) {
            res.add(Arrays.asList(arr[i][0], arr[i][1]));
            i++;
        }

        int lo = newInt[0];
        int hi = newInt[1];
        while (i < n && arr[i][0] <= hi) {
            lo = Math.min(lo, arr[i][0]);
            hi = Math.max(hi, arr[i][1]);
            i++;
        }
        res.add(Arrays.asList(lo, hi));
        while (i < n) {
            res.add(Arrays.asList(arr[i][0], arr[i][1]));
            i++;
        }
        System.out.println(res);
    }
}
