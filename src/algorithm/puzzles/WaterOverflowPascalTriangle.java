package algorithm.puzzles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.geeksforgeeks.org/find-water-in-a-glass/
//https://practice.geeksforgeeks.org/problems/champagne-overflow/0/?company[]=D-E-Shaw&page=1&sortBy=newest&query=company[]D-E-Shawpage1sortBynewest
public class WaterOverflowPascalTriangle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            // 6 3 3 : 0.75
            // 9 3 3 : 1.00
            double k = Double.parseDouble(br.readLine().trim());
            int r = Integer.parseInt(br.readLine().trim());
            int c = Integer.parseInt(br.readLine().trim());
            System.out.println(findVolume(k, r, c));
        }
    }

    private static String findVolume(double k, int row, int pos) {
        double[][] dp = new double[row + 1][row + 1]; //each row has same number of glasses as row
        dp[1][1] = k;  // Fill first row's first glass
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= i; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                double leftParentOverflowAmount = dp[i - 1][j - 1]; // Overflow from left parent in pascal triangle
                double rightParentOverflowAmount = dp[i - 1][j];    // Overflow from right parent in pascal triangle

                if (leftParentOverflowAmount > 1.0) {
                    leftParentOverflowAmount = (leftParentOverflowAmount - 1.0) / 2;    // If it overflowed, then get the overflow amount from left parent
                } else {
                    leftParentOverflowAmount = 0.0;                 // Otherwise, its own volume is less than 1.0, so no overflow and no contribution from left parent
                }

                if (rightParentOverflowAmount > 1.0) {
                    rightParentOverflowAmount = (rightParentOverflowAmount - 1.0) / 2;  // If it overflowed, then get the overflow amount from right parent
                } else {
                    rightParentOverflowAmount = 0.0;                // Otherwise, its own volume is less than 1.0, so no overflow and no contribution from right parent
                }

                dp[i][j] = leftParentOverflowAmount + rightParentOverflowAmount;        // Add both left parent and right parent contributions
            }
        }

        double res = dp[row][pos];
        if (res > 1.0) {
            res = 1;
        }
        return String.format("%.6f", res);
    }
}