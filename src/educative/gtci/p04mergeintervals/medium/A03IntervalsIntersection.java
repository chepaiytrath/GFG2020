package educative.gtci.p04mergeintervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A03IntervalsIntersection {
    public static void main(String[] args) {
        intervalsIntersect();
    }

    // Disjoint intervals sorted on their start time.
    private static void intervalsIntersect() {
        int[][] arr1 = new int[][]{{1, 3}, {5, 6}, {7, 9}};
        int[][] arr2 = new int[][]{{2, 3}, {5, 7}};

        int i = 0;
        int j = 0;

        List<List<Integer>> res = new ArrayList<>();
        while (i < arr1.length && j < arr2.length) {
            // First interval lies between second or second lies between first
            if ((arr1[i][1] >= arr2[j][0] && arr1[i][1] <= arr2[j][1])
                    ||
                    (arr2[j][1] >= arr1[i][0] && arr2[j][1] <= arr1[i][1])) {
                int s1 = arr1[i][0];
                int e1 = arr1[i][1];
                int s2 = arr2[j][0];
                int e2 = arr2[j][1];

                res.add(Arrays.asList(Math.max(s1, s2), Math.min(e1, e2)));
            }

            // Move next from the pointer that finishes first
            if (arr1[i][1] < arr2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println(res);
    }
}
