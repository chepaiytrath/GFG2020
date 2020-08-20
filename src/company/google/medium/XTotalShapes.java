package company.google.medium;

//https://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/
public class XTotalShapes {
    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'O', 'O', 'X'},
                {'O', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'O', 'O', 'O'},
        };
        fill(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // BASED ON FLOOD FILL ALGO
    private static void fill(char[][] arr) {
        int n = arr.length;
        replaceCharactersWithEachOther(arr, 'O', '-');

        // FOUR EDGE BOUNDARIES
        for (int i = 0; i < n; i++) {
            if (arr[0][i] == '-') {
                floodFillUtil(arr, 0, i, 'O');
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[i][0] == '-') {
                floodFillUtil(arr, i, 0, 'O');
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[n - 1][i] == '-') {
                floodFillUtil(arr, n - 1, i, 'O');
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[i][n - 1] == '-') {
                floodFillUtil(arr, i, n - 1, 'O');
            }
        }

        replaceCharactersWithEachOther(arr, '-', 'X');
    }

    private static void floodFillUtil(char[][] arr, int row, int col, char newCh) {
        if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] == newCh || arr[row][col] == 'X') {
            return;
        }
        arr[row][col] = newCh;

        // FOUR NEIGHBORS
        floodFillUtil(arr, row, col - 1, newCh);
        floodFillUtil(arr, row, col + 1, newCh);
        floodFillUtil(arr, row - 1, col, newCh);
        floodFillUtil(arr, row + 1, col, newCh);
    }

    // FIRST SWAP O WITH - AND THEN - WITH X
    private static void replaceCharactersWithEachOther(char[][] arr, char a, char b) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == a) {
                    arr[i][j] = b;
                }
            }
        }
    }
}
