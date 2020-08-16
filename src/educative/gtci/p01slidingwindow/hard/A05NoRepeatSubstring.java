package educative.gtci.p01slidingwindow.hard;

import java.util.Arrays;

public class A05NoRepeatSubstring {
    public static void main(String[] args) {
        noRepeatSubstr();
    }

    // Track the previous indexes and move start pointer based on them
    private static void noRepeatSubstr() {
//        String str = "aabccbb";
        String str = "abcbdae";
        char[] arr = str.toCharArray();
        int n = arr.length;

        int start = 0;
        int end = 0;
        int[] dp = new int[26];
        Arrays.fill(dp, -1);
        int maxLen = Integer.MIN_VALUE;

        while (end < n) {
            char charAtEnd = arr[end];
            if (dp[charAtEnd - 'a'] == -1) {
                dp[charAtEnd - 'a'] = end;
                maxLen = Math.max(maxLen, end + 1 - start);
            } else {
                //duplicate found
                int newStart = dp[charAtEnd - 'a'] + 1;
                while (start < newStart) {
                    char charAtStart = arr[start];
                    dp[charAtStart - 'a'] = -1;
                    start++;
                }
            }
            end++;
        }
        System.out.println(maxLen);
    }
}
