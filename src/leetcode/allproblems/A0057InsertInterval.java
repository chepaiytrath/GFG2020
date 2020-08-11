package leetcode.allproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class A0057InsertInterval {
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
        if(intervals == null || intervals.length == 0){
            return new int[][]{{newInterval[0], newInterval[1]}};
        }

        int x = newInterval[0];
        int y = newInterval[1];

        int i = 0;
        List<List<Integer>> res = new ArrayList<>();

        //ADD INTERVALS TO RESULT WHOSE END INDEX IS LESS THAN START INDEX OF NEW INTERVAL
        while(i < intervals.length && intervals[i][1] < x){
            res.add(Arrays.asList(intervals[i][0], intervals[i][1]));
            i++;
        }

        //START MERGING INTERVALS WHOSE START INDEX IS LESS THAN END INDEX OF NEW INTERVAL
        while(i < intervals.length && intervals[i][0] <= y){
            x = Math.min(x, intervals[i][0]);
            y = Math.max(y, intervals[i][1]);
            i++;
        }

        //X,Y NOW CONTAIN MERGED VALUE OF ALL SUCH INDICES
        res.add(Arrays.asList(x, y));

        while(i < intervals.length){
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