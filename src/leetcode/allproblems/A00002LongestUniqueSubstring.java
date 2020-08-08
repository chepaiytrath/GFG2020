package leetcode.allproblems;

import java.util.HashSet;
import java.util.Set;

public class A00002LongestUniqueSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(lengthOfLongestSubstring("abba"));

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // Sliding window
        int start = 0;
        int end = 0;
        int n = s.length();
        int maxLength = Integer.MIN_VALUE;
        char[] arr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        while (end < n){
            char charAtEnd = arr[end];
            if(!set.contains(charAtEnd)){
                set.add(charAtEnd);
                maxLength = Math.max(maxLength, end - start + 1);
                end++;
            }else{
                char charAtStart = arr[start];
                set.remove(charAtStart);
                start++;
            }
        }

        return maxLength;
    }
}
