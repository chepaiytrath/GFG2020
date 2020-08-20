package educative.gtci.p04mergeintervals.medium;

import java.util.Arrays;

public class A04ConflictingAppointments {
    public static void main(String[] args) {
        conflictingAppoint();
    }

    private static void conflictingAppoint() {
//        int[][] arr = new int[][]{{1, 4}, {2, 5}, {7, 9}};
//        int[][] arr = new int[][]{{6, 7}, {2, 4}, {8, 12}};
        int[][] arr = new int[][]{{4, 5}, {2, 3}, {3, 6}};
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int n = arr.length;

        int lo = arr[0][0];
        int hi = arr[0][1];

        boolean flag = true;
        int i = 1;
        while (i < n) {
            if (arr[i][0] > hi) {
                lo = arr[i][0];
                hi = arr[i][1];
            } else {
                flag = false;
                break;
            }
            i++;
        }

        if (!flag) {
            System.out.println("CANT MAKE ALL APPOINTMENTS");
        } else {
            System.out.println("CAN MAKE ALL APPOINTMENTS");
        }
    }
}
