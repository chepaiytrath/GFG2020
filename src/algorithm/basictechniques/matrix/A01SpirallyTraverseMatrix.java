package algorithm.basictechniques.matrix;

import java.util.ArrayList;

public class A01SpirallyTraverseMatrix {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println(spirallyTraverse(arr, 4, 4));
    }

    static ArrayList<Integer> spirallyTraverse(int arr[][], int r, int c) {
        // L: LEFT, R: RIGHT, U: UP, D: DOWN
        // 0: LR, 1: UD, 2: RL, 3: DU
        int dir = 0;

        // KEEP MOVING THESE VARIABLES TO DIRECT MATRIX MOVEMENT
        int top = 0;
        int down = r - 1;
        int left = 0;
        int right = c - 1;

        ArrayList<Integer> res = new ArrayList<Integer>();
        // NEEDS <= BECAUSE THEY TOP MEETS DOWN AND LEFT MEETS RIGHT IN INNERMOST POINT
        while (top <= down && left <= right) {
            if (dir == 0) {
                for (int i = left; i <= right; i++) {
                    res.add(arr[top][i]);
                }
                top++;
            } else if (dir == 1) {
                for (int i = top; i <= down; i++) {
                    res.add(arr[i][right]);
                }
                right--;
            } else if (dir == 2) {
                for (int i = right; i >= left; i--) {
                    res.add(arr[down][i]);
                }
                down--;
            } else if (dir == 3) {
                for (int i = down; i >= top; i--) {
                    res.add(arr[i][left]);
                }
                left++;
            }

            // ONCE A SET OF LR, UD, RL, DU MOVEMENTS HAVE BEEN DONE
            // IT'S TIME FOR ANOTHER SET OF THE SAME ACTIONS BUT WITH UPDATED TOP DOWN LEFT RIGHT
            // (3 + 1) % 4 = 0: BACK TO SQUARE 0
            dir = (dir + 1) % 4;
        }
        return res;
    }
}