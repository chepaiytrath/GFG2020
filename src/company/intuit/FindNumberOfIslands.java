package company.intuit;

public class FindNumberOfIslands {
    static final int ROW = 5, COL = 5;

    public static void main(String[] args) {
        int mat[][] = {{1, 1, 0, 0, 0}, {0, 1, 0, 0, 1}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 1, 0}};
        int count = findNumberOfIslands(mat);
        System.out.println("Count = " + count);
    }

    private static int findNumberOfIslands(int[][] mat) {
        int count = 0;
        boolean visited[][] = new boolean[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < ROW; j++) {
                if (mat[i][j] == 1 && !visited[i][j]) {
                    DFS(mat, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void DFS(int[][] mat, boolean[][] visited, int i, int j) {
        int[] neighbourX = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] neighbourY = {0, 1, 1, 1, 0, -1, -1, -1};

        visited[i][j] = true;
        for (int k = 0; k < 7; k++) {
            int newRow = i + neighbourX[k];
            int newCol = j + neighbourY[k];
            if (isSafe(newRow, newCol, mat, visited)) {
                DFS(mat, visited, newRow, newCol);
            }
        }
    }

    private static boolean isSafe(int newRow, int newCol, int[][] mat, boolean[][] visited) {
        return (newRow >= 0 && newRow < ROW && newCol >= 0 && newCol < COL && mat[newRow][newCol] == 1 && !visited[newRow][newCol]);
    }
}
