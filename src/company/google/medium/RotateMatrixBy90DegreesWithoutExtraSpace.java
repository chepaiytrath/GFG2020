package company.google.medium;

public class RotateMatrixBy90DegreesWithoutExtraSpace {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        rotateMatrix(mat);

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < col; j++) {
                swap(mat, i, j);
            }
        }

        for (int j = 0; j < col; j++) {
            int lo = 0;
            int hi = row - 1;
            while (lo < hi) {
                int temp = mat[lo][j];
                mat[lo][j] = mat[hi][j];
                mat[hi][j] = temp;
                lo++;
                hi--;
            }
        }
    }

    private static void swap(int[][] mat, int i, int j) {
        int temp = mat[i][j];
        mat[i][j] = mat[j][i];
        mat[j][i] = temp;
    }
}
