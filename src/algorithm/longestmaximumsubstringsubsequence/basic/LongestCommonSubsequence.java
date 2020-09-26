package algorithm.longestmaximumsubstringsubsequence.basic;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        lcsRecursiveMemoization();
        lcsDP();
    }

    private static void lcsRecursiveMemoization() {
        String s1 = "ABCD";
        String s2 = "ACBDE";

        Map<String, Integer> map = new HashMap<>();
        System.out.println(lcsRecursiveMemoizationUtil(s1, s2, 0, 0, map));
    }

    private static int lcsRecursiveMemoizationUtil(String s1, String s2, int i, int j, Map<String, Integer> map) {
        if (i == s1.length() || j == s2.length()) {
            map.put(i + "|" + j, 0);
            return 0;
        }

        if (map.containsKey(i + "|" + j)) {
            return map.get(i + "|" + j);
        }

        // ex. A BCD
        //     A CBDE
        if (s1.charAt(i) == s2.charAt(j)) {
            int res = lcsRecursiveMemoizationUtil(s1, s2, i + 1, j + 1, map);
            map.put(i + "|" + j, 1 + res);
            return 1 + res;
        } else {
            //ex.  B CD
            //     C BDE
            // CHECK MAX RETURN VALUE FOR TWO POSSIBLE CASES:
            // 1. BCD
            //    BDE
            // 2. CD
            //    CBDE
            int res1 = lcsRecursiveMemoizationUtil(s1, s2, i + 1, j, map);
            int res2 = lcsRecursiveMemoizationUtil(s1, s2, i, j + 1, map);
            map.put((i + 1) + "|" + j, res1);
            map.put(i + "|" + (j + 1), res2);
            return Math.max(res1, res2);
        }
    }

    private static void lcsDP() {
        String s1 = "ABCD";
        String s2 = "ACBDE";

        System.out.println(lcsDPUtil(s1, s2));
    }

    private static int lcsDPUtil(String s1, String s2) {
        // ROW COLUMN COULD BE BASED ON ANY OF THE TWO STRINGS
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // Default, 0th row and 0th column will have all 0s
        // because no subsequence can be achieved if either string is empty


        // IF SAME, THEN 1 + DIAGONAL
        // IF DIFFERENT, THEN MAX OF LEFT AND UP
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    // 1 + DIAGONAL
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    // MAX OF LEFT AND UP
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
