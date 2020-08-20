package educative.gtci.p04mergeintervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A01MergeIntervals {
    public static void main(String[] args) {
        mergeIntervals2();
    }

    private static void mergeIntervals() {
        int[][] arr = new int[][]{{2, 5}, {1, 4}, {7, 9}};
//        int[][] arr = new int[][]{{6, 7}, {2, 4}, {5, 9}};
//        int[][] arr = new int[][]{{1, 4}, {2, 6}, {3, 5}};
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        List<List<Integer>> res = new ArrayList<>();

        int i = 0;
        while (arr[i][1] < arr[i + 1][0]) {
            res.add(Arrays.asList(arr[i][0], arr[i][1]));
            i++;
        }

        int lo = arr[i][0];
        int hi = arr[i][1];
        i++;
        while (i < arr.length && arr[i][0] <= hi) {
            lo = Math.min(lo, arr[i][0]);
            hi = Math.max(hi, arr[i][1]);
            i++;
        }
        res.add(Arrays.asList(lo, hi));

        while (i < arr.length) {
            res.add(Arrays.asList(arr[i][0], arr[i][1]));
            i++;
            ;
        }
        System.out.println(res);
    }

    private static void mergeIntervals2() {
        int[][] arr = new int[][]{{2, 5}, {1, 4}, {7, 9}, {10, 15}, {14,16}, {13,17}, {20,25}};
//        int[][] arr = new int[][]{{6, 7}, {2, 4}, {5, 9}};
//        int[][] arr = new int[][]{{1, 4}, {2, 6}, {3, 5}};
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        List<List<Integer>> res = new ArrayList<>();

        int n = arr.length;
        int i = 0;


        int lo = arr[i][0];
        int hi = arr[i][1];

        i++;
        // Edge case: Handle merging when required at multiple places within the intervals
        // Can't just have skip the initial ones till a merge worthy interval is found and then merge the subsequent intervals and leave it there.
        // What if after your first merge is completed, some other overlapping intervals are encountered
        while (i < n) {
            int x = arr[i][0];
            int y = arr[i][1];

            if (x > hi) {
                res.add(Arrays.asList(lo, hi));
                lo = x;
                hi = y;
            } else {
                hi = Math.max(hi, y);
            }
            i++;
        }

        res.add(Arrays.asList(lo, hi));

        System.out.println(res);
    }
}
