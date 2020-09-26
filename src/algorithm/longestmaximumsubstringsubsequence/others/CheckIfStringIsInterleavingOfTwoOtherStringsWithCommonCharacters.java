package algorithm.longestmaximumsubstringsubsequence.others;

//https://www.geeksforgeeks.org/find-if-a-string-is-interleaved-of-two-other-strings-dp-33/
//https://www.techiedelight.com/check-string-interleaving-two-given-strings/
public class CheckIfStringIsInterleavingOfTwoOtherStringsWithCommonCharacters {
    public static void main(String[] args) {
        /*String parent = "abcdef";   //p
        String child1 = "ace";  //c1
        String child2 = "bdf";  //c2*/


        // BETTER EXAMPLE : RANDOMNESS AND NEED TO BACKTRACK UNLIKE THE SIMPLE EXAMPLE
        /*String parent = "aaxaby"; //p
        String child1 = "aab";    //c1
        String child2 = "axy";    //c2*/

        String parent = "XXXXZY"; //p
        String child1 = "XXY";    //c1
        String child2 = "";    //c2
        System.out.println(checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, 0, 0, 0));
        System.out.println(checkIfStringIsInterleavingOfTwoOtherStringsDP(parent, child1, child2));
    }

    private static boolean checkIfStringIsInterleavingOfTwoOtherStrings(String parent, String child1, String child2,
                                                                        int p, int c1, int c2) {
        // ALL THREE POINTERS HAVE REACHED THE END
        if (p == parent.length() && c1 == child1.length() && c2 == child2.length()) {
            return true;
        }

        //ONLY PARENT POINTER REACHED END AND NOT THE OTHER TWO
        if (p == parent.length()) {
            return false;
        }

        boolean isSubsequence = false;

        // BACKTRACKING
        // CHECK IF FIRST CHARACTERS IN PARENT AND CHILD1 MATCH
        if (c1 < child1.length() && parent.charAt(p) == child1.charAt(c1)) {
            isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, p + 1, c1 + 1, c2);
        }
        // IF THE ABOVE DOESNT MATCH, CHECK IF FIRST CHARACTERS IN PARENT AND CHILD2 MATCH
        if (!isSubsequence) {
            if (c2 < child2.length() && parent.charAt(p) == child2.charAt(c2)) {
                isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, p + 1, c1, c2 + 1);
            }
        }
        return isSubsequence;
    }

    // #REVISIT
    // Bottom Up DP
    private static boolean checkIfStringIsInterleavingOfTwoOtherStringsDP(String parent, String child1, String child2) {
        //Should be equal to sum of children size
        if (parent.length() != child1.length() + child2.length()) {
            return false;
        }

        int m = child1.length();
        int n = child2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        char[] p = parent.toCharArray();
        char[] c1 = child1.toCharArray();
        char[] c2 = child2.toCharArray();
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // ANY STRING IS AN INTERLEAVING OF BOTH EMPTY ARRAYS
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                // If c1 is empty, check if c2 char is same as p char
                if (i == 0) {
                    if (c2[j - 1] == p[i + j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
                // If c2 is empty, check if c1 char is same as p char
                else if (j == 0) {
                    if (c1[i - 1] == p[i + j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else if (i != 0 && j != 0) {
                    // If only c1 char is same as p char, copy from top to down
                    if (c1[i - 1] == p[i + j - 1] && c2[j - 1] != p[i + j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    }
                    // If only c2 char is same as p char, copy from left to right
                    else if (c1[i - 1] != p[i + j - 1] && c2[j - 1] == p[i + j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    // If both c1 and c2 char is same as p char, mark true if either top or down is true
                    else if (c1[i - 1] == p[i + j - 1] && c2[j - 1] == p[i + j - 1]) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

}
