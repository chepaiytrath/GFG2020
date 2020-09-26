package algorithm.longestmaximumsubstringsubsequence.others;

//#REVISIT
//https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
class NumberOfTimesStringOccursAsSubsequenceInGivenStringDistinctOccurrencesOfSubsequenceInGivenString {
    public static void main(String[] args) {
        String a = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
        String b = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxp";

        System.out.println(subsequenceCount(a, b));
    }

    public static int subsequenceCount(String string, String pattern) {
        char[] str = string.toCharArray();
        char[] pat = pattern.toCharArray();
        int mod = 1000000007;

        int strlen = str.length;
        int patlen = pat.length;

        int[][] dp = new int[patlen + 1][strlen + 1];
        int R = dp.length;
        int C = dp[0].length;

        // Initialize first column all values to 0
        for (int i = 0; i < R; i++) {
            dp[i][0] = 0;
        }

        // DOESNT MAKE SENSE BUT KEEP IT
        // Initialize first row all values to 1 : Overrides dp[0][0] to 1
        // Means : Blank pattern will occur only once as a subsequence in string of any length
        for (int i = 0; i < C; i++) {
            dp[0][i] = 1;
        }

        // CURRENT SEQUENCE OCCURRENCE COUNT IN REMAINING + COUNT OF ONE LESS CHARACTERS IN BOTH
        //EX. A B D E D E
        //    D E
        for (int i = 1; i <= patlen; i++) {
            for (int j = 1; j <= strlen; j++) {
                if (str[j - 1] == pat[i - 1]) {
                    // dp[i - 1][j - 1] :(PATTERN WITHOUT LAST CHARACTER) IN (STRING WITHOUT LAST CHARACTER) : LAST CHARACTER IS JUST ADDED TO BOTH TO FORM A SUBSEQUENCE
                    // dp[i][j - 1] : COMPLETE PATTERN IN (STRING WITHOUT LAST CHARACTER)
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i][j - 1]) % mod;
                } else {
                    // dp[i][j - 1] : COMPLETE PATTERN IN (STRING WITHOUT LAST CHARACTER)
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[R - 1][C - 1] % mod;
    }
}