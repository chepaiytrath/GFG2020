package educative.gtci.p01slidingwindow.medium;

import java.util.HashSet;
import java.util.Set;

public class A03LongestSubstringWithKDistinctCharacters {
    public static void main(String[] args) {
        longestSubstring();
    }

    private static void longestSubstring() {
        String str = "aaaaaaaaa";
        int k = 1;

        char[] arr = str.toCharArray();
        Set<Character> set = new HashSet<>();

        int len = Integer.MIN_VALUE;
        int n = str.length();
        int start = 0;
        int end = 0;
        while (end < n) {
            set.add(arr[end]);
            while (set.size() > k) {
                set.remove(arr[start]);
                start++;
            }
            if (set.size() == k) {
                len = Math.max(len, end + 1 - start);
            }
            end++;
        }
        System.out.println(len);
    }
}
