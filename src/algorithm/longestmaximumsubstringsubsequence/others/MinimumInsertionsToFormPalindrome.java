package algorithm.longestmaximumsubstringsubsequence.others;

public class MinimumInsertionsToFormPalindrome {
    public static void main(String[] args) {
        String str = "abcbc";
//        System.out.println(findMinimumInsertionsToFormPalindrome(str));
        System.out.println(findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, 0, str.length() - 1));
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

    //EASY TO UNDERSTAND  : DESCRIPTION IN ONE NOTE
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
        if (hi - lo == 1) {
            return sameChars ? 0 : 1;
        }
        if (sameChars) {
            return findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, lo, hi - 1);
        }
        int min = Math.min(findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, lo + 1, hi),
                findMinimumInsertionsToFormPalindromeWithMinimumOfThreeApproach(str, lo + 1, hi - 1));
        return 1 + min;
    }
}

