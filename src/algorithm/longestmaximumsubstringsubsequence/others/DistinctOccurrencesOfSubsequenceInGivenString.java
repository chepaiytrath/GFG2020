package algorithm.longestmaximumsubstringsubsequence.others;

//https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
class DistinctOccurrencesOfSubsequenceInGivenString {
    public static void main(String[] args) {
        String a = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
        String b = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxp";

        System.out.println(subsequenceCount(a, b));
    }

    public static int subsequenceCount(String S, String T) {
        char[] arr1 = S.toCharArray();
        char[] arr2 = T.toCharArray();
        int mod = 1000000007;

        int m = arr1.length;
        int n = arr2.length;

        int[][] dp = new int[n + 1][m + 1];
        int R = dp.length;
        int C = dp[0].length;

        for (int i = 0; i < R; i++) {
            dp[i][0] = 0;
        }

        // VERY IMPORTANT TO KEP THIS INIT BELOW THE ABOVE
        for (int i = 0; i < C; i++) {
            dp[0][i] = 1;
        }

        // CURRENT SEQUENCE OCCURRENCE COUNT IN REMAINING + COUNT OF ONE LESS CHARACTERS IN BOTH
        //EX. A B D E D E
        //    D E
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr1[j - 1] == arr2[i - 1]) {
                    // dp[i - 1][j - 1] :(PATTERN WITHOUT LAST CHARACTER) IN (STRING WITHOUT LAST CHARACTER) : LAST CHARACTER IS JUST ADDED TO BOTH TO FORM A SUBSEQUENCE
                    // dp[i][j - 1] : COMPLETE PATTERN IN (FIRST STRING WITHOUT LAST CHARACTER)
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i][j - 1]) % mod;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[R - 1][C - 1] % mod;
    }
}