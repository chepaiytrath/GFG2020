//https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/
package company.intuit;

import java.util.Stack;

public class MaximumRectangleBinarySubMatrix {
    static final int ROW = 4, COL = 4;

    int getMaxRectangle(int[][] mat) {
        countMat(mat);
        int maxRectArea = 0;
        for (int i = 0; i < ROW; i++) {
            maxRectArea = Math.max(maxRectArea, calculateMaxAreaPerRow(mat[i]));
        }
        return maxRectArea;
    }

    private int calculateMaxAreaPerRow(int[] row) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < row.length) {
            if (stack.isEmpty() || row[stack.peek()] <= row[i]) {
                stack.push(i++);
            } else {
                int topIndex = stack.pop();
                //RIGHT INDEX = i && LEFT INDEX = topIndex
                int area = row[topIndex] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(area, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            //RIGHT INDEX = i && LEFT INDEX = topIndex
            int area = row[topIndex] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    private void countMat(int[][] mat) {
        for (int i = 1; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] += mat[i - 1][j];
                }
            }
        }
    }


    public static void main(String[] args) {
        int[][] mat = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}};
        MaximumRectangleBinarySubMatrix obj = new MaximumRectangleBinarySubMatrix();
        System.out.println("Maximum rectangle is " + obj.getMaxRectangle(mat));
    }
}
