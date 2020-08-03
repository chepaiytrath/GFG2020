package algorithm.puzzles;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
        String input = "iace";
        Set<String> dict = new HashSet<>();
        dict.add("i");
        dict.add("am");
        dict.add("ace");
        dict.add("a");

        System.out.println(isWordPossibleDynamicProgramming(input, dict));
    }

    // Recursive Solution
    private static boolean isWordPossibleRecursion(String input, Set<String> dictionary) {
        if (input == null || input.length() == 0) {
            return true;
        }
        int i = 0;
        while (i < input.length()) {
            if (dictionary.contains(input.substring(0, i + 1))) {
                if (i == input.length() - 1) {
                    return true;
                } else if (i < input.length() - 1 && isWordPossibleRecursion(input.substring(i + 1), dictionary)) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    private static boolean isWordPossibleDynamicProgramming(String input, Set<String> dictionary) {
        int n = input.length();
        boolean[][] dp = new boolean[n][n];
        int diff = 0;
        while (diff < n) {
            for (int i = 0; i < n; i++) {
                int j = i + diff;
                if (j < n) {
                    String substring = input.substring(i, j + 1);
                    if (dictionary.contains(substring)) {
                        dp[i][j] = true;
                    } else {
                        int k = i;
                        while (k < j) {
                            if (dp[i][k] && dp[k + 1][j]) {
                                dp[i][j] = true;
                                break;
                            }
                            k++;
                        }
                    }
                }
            }
            diff++;
        }
        return dp[0][n - 1];
    }
}