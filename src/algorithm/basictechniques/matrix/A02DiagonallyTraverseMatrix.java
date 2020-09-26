package algorithm.basictechniques.matrix;

import java.util.ArrayList;
import java.util.List;

class A02DiagonallyTraverseMatrix {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,  2,  3,  4,  5},
                {6,  7,  8,  9,  10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        printMatrixDiagonal(arr, 5);
    }

    static void printMatrixDiagonal(int arr[][], int n) {
        // 0: top right diagonal,  1: bottom left diagonal
        int dir = 0;

        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;


        list.add(arr[i][j]);
        while (true) {
            // top right diagonal
            if (dir == 0) {
                // go to right or down
                if (j + 1 < n) {
                    j++;    //go right
                } else if (i + 1 < n) {
                    i++;    //go down
                }
                dir = 1 - dir; // change dir to 1 : bottom left diagonal
                // traverse bottom left diagonal
                while (i >= 0 && i < n && j >= 0 && j < n) {
                    list.add(arr[i][j]);
                    i++;
                    j--;
                }
                i--;
                j++;
            }
            // bottom left diagonal
            else if (dir == 1) {
                if (i + 1 < n) {
                    i++;
                } else if (j + 1 < n) {
                    j++;
                }
                dir = 1 - dir; // change dir to 0: top right diagonal
                // traverse top right diagonal
                while (i >= 0 && i < n && j >= 0 && j < n) {
                    list.add(arr[i][j]);
                    i--;
                    j++;
                }
                i++;
                j--;
            }
            if (i == n - 1 && j == n - 1) {
                break;
            }
        }
        for (int num : list) {
            System.out.print(num + " ");
        }
    }

}