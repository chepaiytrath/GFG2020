package company.walmart2.medium;

import java.util.Arrays;

public class MaximumSumRectangleSubmatrix {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };

        System.out.println(findMaximumSum(mat));
    }

    private static int findMaximumSum(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        int left = -1, right = -1, up = -1, down = -1;

        int maxSum2D = Integer.MIN_VALUE;
        int[] sumArr = new int[row];
        for (int L = 0; L < col; L++) {
            Arrays.fill(sumArr, 0);
            for (int R = L; R < col; R++) {
                addCurrentColumn(sumArr, R, mat);

                int currSum = 0;
                int maxSum1D = Integer.MIN_VALUE;
                int start = -1, end = -1;
                for (int i = 0; i < row; i++) {
                    currSum = Math.max(currSum, currSum + sumArr[i]);
                    if (currSum == sumArr[i]) {
                        start = i;
                    }
                    maxSum1D = Math.max(maxSum1D, currSum);
                    if (maxSum1D == currSum) {
                        end = i;
                    }
                }

                if (maxSum2D < maxSum1D) {
                    left = L;
                    right = R;
                    up = start;
                    down = end;
                    maxSum2D = maxSum1D;
                }
            }
        }

        if (left != -1) {
            System.out.println("THE MATRIX IS");
            for (int i = up; i <= down; i++) {
                for (int j = left; j <= right; j++) {
                    System.out.print(mat[i][j] + " ");
                }
                System.out.println();
            }
        }

        return maxSum2D;
    }

    private static void addCurrentColumn(int[] sumArr, int col, int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            sumArr[i] += mat[i][col];
        }
    }
}