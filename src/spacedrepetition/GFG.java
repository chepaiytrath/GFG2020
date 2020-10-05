package spacedrepetition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            double k = Double.parseDouble(br.readLine().trim());
            int r = Integer.parseInt(br.readLine().trim());
            int c = Integer.parseInt(br.readLine().trim());
            double dp[][] = new double[r][r * 2 + 1];
            dp[0][dp[0].length / 2] = k;
            for (int i = 1; i < r; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (j == 0 && dp[i - 1][j + 1] >= 1) {
                        dp[i][j] = (dp[i - 1][j + 1] - 1) / 2;
                    }
                    if (j == dp[0].length - 1 && dp[i - 1][j - 1] >= 1) {
                        dp[i][j] = (dp[i - 1][j - 1] - 1) / 2;
                    }
                    if (j != 0 && j != dp[0].length - 1) {
                        dp[i][j] = (dp[i - 1][j - 1] >= 1 ? (dp[i - 1][j - 1] - 1) / 2 : 0) + (dp[i - 1][j + 1] >= 1 ? (dp[i - 1][j + 1] - 1) / 2 : 0);
                    }
                }
            }
            double ans = dp[r - 1][(c - 1) * 2 + 1];
            if (ans > 1) {
                ans = 1;
            }
            System.out.println(String.format("%.6f", ans));
        }
    }
}