package algorithm.basictechniques.slidingwindow;

import java.util.HashSet;
import java.util.Set;

// ALSO SEE A02SubstringsOfSizeKWithKDistinctCharacters : SAME WINDOW MOVEMENT LOGIC
public class A01LongestUniqueSubstring {
    public static void main(String[] args) {
        /*String s = "abcabcbb";
        String s = "abba";*/
        String s = "abbacadsg";
        System.out.println(lengthOfLongestUniqueSubstring(s));

    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] arr = s.toCharArray();
        int n = arr.length;
        int start = 0;
        int end = 0;
        int maxLen = 1;
        Set<Character> set = new HashSet<>();
        while (end < n) {
            char charAtEnd = arr[end];
            while (set.contains(charAtEnd)) {
                char charAtStart = arr[start];
                set.remove(charAtStart);
                start++;
            }
            set.add(charAtEnd);
            maxLen = Math.max(maxLen, end - start + 1);
            end++;
        }
        return maxLen;
    }
}