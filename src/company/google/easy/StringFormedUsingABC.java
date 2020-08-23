package company.google.easy;

//https://www.geeksforgeeks.org/count-strings-can-formed-using-b-c-given-constraints/
public class StringFormedUsingABC {
    public static void main(String[] args) {
        // CAN USE ONLY A B C
        // CONSTRAINTS: at most 1 b and 2 c are allowed
        // ANY NUMBER OF As ARE ALLOWED
        int n = 3;
        int bCount = 1;
        int cCount = 2;

        int[][][] dp = new int[n + 1][bCount + 1][cCount + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        int count = findStringFormedUsingABC(n, bCount, cCount, dp);
        System.out.println(count);
    }

    private static int findStringFormedUsingABC(int n, int bCount, int cCount, int[][][] dp) {
        // RETURNING 1 MEANS WORD FOUND: WORD FOUND IS POSSIBLE ONLY IF N = 0 OR BOTH B AND C ARE CONSUMED

        // IF ALL POSITIONS HAVE BEEN EXHAUSTED, RETURNED 1 : WORD FOUND
        // EX. IF N = 3 -> AAA, IF N = 4 -> AAAA
        if (n == 0) {
            return 1;
        }

        //IF BOTH B AND C COUNTS ARE 0 : WORD FOUND : RETURN 1 : DOESNT MATTER WHAT N IS
        //EX. IF N = 3 -> BCC, IF N = 4 -> BCC
        if (bCount == 0 && cCount == 0) {
            return 1;
        }

        //IF EITHER OF B OR C COUNTS IS NEGATIVE : RETURN 0: INVALID COMBINATION
        //PREVENTING AN INVALID POSSIBILITY
        if (bCount < 0 || cCount < 0) {
            return 0;
        }

        if (dp[n][bCount][cCount] != -1) {
            return dp[n][bCount][cCount];
        }

        //SELECT 1 a : n DECREASES BY 1
        int res = findStringFormedUsingABC(n - 1, bCount, cCount, dp);

        //SELECT 1 b : n DECREASES BY 1
        res += findStringFormedUsingABC(n - 1, bCount - 1, cCount, dp);

        //SELECT 1 c : n DECREASES BY 1
        res += findStringFormedUsingABC(n - 1, bCount, cCount - 1, dp);

        dp[n][bCount][cCount] = res;
        return res;
    }
}
