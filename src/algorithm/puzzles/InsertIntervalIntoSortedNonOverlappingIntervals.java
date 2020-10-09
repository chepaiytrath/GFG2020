package algorithm.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InsertIntervalIntoSortedNonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = new int[]{4, 8};
        /*int[][] intervals = new int[][]{{1, 5}};
        int[] newInterval = new int[]{2, 7};*/
        int[][] arr = insert(intervals, newInterval);
        for (int[] row : arr) {
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }

        int i = 0;
        int n = intervals.length;
        List<List<Integer>> res = new ArrayList<>();

        int lo = newInterval[0];
        int hi = newInterval[1];

        // Skip all NON OVERLAPPING intervals who end before the new interval starts and add them to result directly
        while (i < n && intervals[i][1] < lo) {
            res.add(Arrays.asList(intervals[i][0], intervals[i][1]));
            i++;
        }

        // Merge all intervals and get a lo and hi value covering all overlapping intervals due to insertion of new interval
        // Merge all intervals whose who start before the hi index (updated every step)
        while (i < n && intervals[i][0] <= hi) {    // hi keeps getting updated and if the current interval falls under the updated lo, hi then merge it
            lo = Math.min(lo, intervals[i][0]);
            hi = Math.max(hi, intervals[i][1]);
            i++;
        }

        //X,Y NOW CONTAIN MERGED VALUE OF ALL SUCH INDICES
        res.add(Arrays.asList(lo, hi));

        while (i < n) {
            res.add(Arrays.asList(intervals[i][0], intervals[i][1]));
            i++;
        }
        int[][] arr = new int[res.size()][2];
        for (int idx = 0; idx < res.size(); idx++) {
            arr[idx][0] = res.get(idx).get(0);
            arr[idx][1] = res.get(idx).get(1);
        }
        return arr;
    }
}