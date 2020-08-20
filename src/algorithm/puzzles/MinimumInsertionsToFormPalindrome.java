package algorithm.puzzles;

public class MinimumInsertionsToFormPalindrome {
    public static void main(String[] args) {
        String str = "abcba";
//        System.out.println(findMinimumInsertionsToFormPalindrome(str));
//        System.out.println(findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, 0, str.length() - 1));
        System.out.println(findMinimumInsertionsToFormPalindromeWithDP(str.toCharArray(), str.length()));
    }

    private static int findMinimumInsertionsToFormPalindrome(String str) {
        if (isPalindrome(str)) {
            return 0;
        }

        return 1 + findMinimumInsertionsToFormPalindrome(str.substring(1));
    }

    private static boolean isPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // #REVISIT
    // EASY TO UNDERSTAND  : DESCRIPTION IN ONE NOTE
    private static int findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(String str, int lo, int hi) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        if (lo > hi) {
            return Integer.MAX_VALUE;
        }
        if (lo == hi) {
            return 0;
        }
        boolean sameChars = str.charAt(lo) == str.charAt(hi);

        // Right next to each other
        if (hi - lo == 1) {
            return sameChars ? 0 : 1;
        }
        if (sameChars) {
            // Shrink from both sides : lo and hi
            return findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, lo + 1, hi - 1);
        }
        int min = Math.min(findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, lo + 1, hi),
                findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, lo + 1, hi - 1));
        return 1 + min;
    }


    // #REVISIT
    // #COMPLICATED
    static int findMinimumInsertionsToFormPalindromeWithDP(char str[], int n)
    {
        // Create a table of size n*n. table[i][j]
        // will store minumum number of insertions
        // needed to convert str[i..j] to a palindrome.
        int table[][] = new int[n][n];
        int l, h, gap;

        // Fill the table
        for (gap = 1; gap < n; ++gap)
            for (l = 0, h = gap; h < n; ++l, ++h)
                table[l][h] = (str[l] == str[h])?
                        table[l+1][h-1] :
                        (Integer.min(table[l][h-1],
                                table[l+1][h]) + 1);

        // Return minimum number of insertions
        // for str[0..n-1]
        return table[0][n-1];
    }

}

