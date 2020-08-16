package educative.gtci.p04mergeintervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A01MergeIntervals {
    public static void main(String[] args) {
        mergeIntervals();
    }

    private static void mergeIntervals() {
//        int[][] arr = new int[][]{{2, 5}, {1, 4}, {7, 9}};
        int[][] arr = new int[][]{{6, 7}, {2, 4}, {5, 9}};
//        int[][] arr = new int[][]{{1, 4}, {2, 6}, {3, 5}};
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        List<List<Integer>> intervals = new ArrayList<>();
        int lo = arr[0][0];
        int hi = arr[0][1];



        /*while(){

        }*/


        int i = 1;

        while (i < arr.length) {
            int x = arr[i][0];
            int y = arr[i][1];

            if (x <= hi) {
                lo = Math.min(lo, x);
                hi = Math.max(hi, y);
            } else {
                intervals.add(Arrays.asList(lo, hi));
                break;
            }
            i++;
        }
        while (i < arr.length) {
            intervals.add(Arrays.asList(arr[i][0], arr[i][1]));
            i++;
        }


        System.out.println(intervals);
    }
}
