package company.google.oa;

class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath(arr));
    }

    public static int longestIncreasingPath(int[][] arr) {
        if(arr.length == 0){
            return 0;
        }
        int R = arr.length;
        int C = arr[0].length;

        int[][] dp = new int[R][C];
        int max = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int len = dfs(i, j, arr, dp, R, C);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    static final int[][] pos = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static int dfs(int i, int j, int[][] arr, int[][] dp, int R, int C) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int max = 1;
        for (int ind = 0; ind < 4; ind++) {
            int x = i + pos[ind][0];
            int y = j + pos[ind][1];

            if (x < 0 || x >= R || y < 0 || y >= C || arr[x][y] <= arr[i][j]) {
                continue;
            }
            int len = 1 + dfs(x, y, arr, dp, R, C);
            max = Math.max(max, len);
        }
        dp[i][j] = max;
        return max;
    }
}