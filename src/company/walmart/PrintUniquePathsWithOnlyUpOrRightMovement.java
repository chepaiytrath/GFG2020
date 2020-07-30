package company.walmart;

public class PrintUniquePathsWithOnlyUpOrRightMovement {
    private static void printMatrix(int mat[][], int row, int col, int[] arr, int idx) {
        arr[idx] = mat[row][col];
        if (row == 0 && col == 0) {
            printArray(arr);
            return;
        }
        if (row == 0) {
            printMatrix(mat, row, col - 1, arr, idx - 1);
            return;
        }
        if (col == 0) {
            printMatrix(mat, row - 1, col, arr, idx - 1);
            return;
        }

        printMatrix(mat, row, col - 1, arr, idx - 1);
        printMatrix(mat, row - 1, col, arr, idx - 1);
    }

    private static void printArray(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    // Driver code 
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int mat[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int row = mat.length;
        int col = mat[0].length;
        int[] arr = new int[row + col - 1];
        printMatrix(mat, row - 1, col - 1, arr, arr.length - 1);
    }
} 