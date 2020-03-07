package company.walmart;

public class PrintUniquePathsWithOnlyUpOrRightMovement {
    private static int printMatrix(int mat[][], int i, int j, int[] arr, int idx) {
        arr[idx] = mat[i][j];
        if (i == 0 && j == 0) {
            printArray(arr);
            return 20;
        }
        if (i == 0) {
            printMatrix(mat, i, j - 1, arr, idx - 1);
            return 20;
        }
        if (j == 0) {
            printMatrix(mat, i - 1, j, arr, idx - 1);
            return 20;
        }

        //Just so that both calls are made parallely, use int return type of the method
        return printMatrix(mat, i, j - 1, arr, idx - 1) + printMatrix(mat, i - 1, j, arr, idx - 1);
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
        int[] arr = new int[mat.length + mat[0].length - 1];
        printMatrix(mat, mat.length - 1, mat[0].length - 1, arr, arr.length - 1);
    }
} 