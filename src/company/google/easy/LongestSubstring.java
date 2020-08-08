package company.google.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {
    public static void main(String[] args) {
        String input = "aaabbb";
        String res = findLongestSubstring(input, 3);
        System.out.println(res);
    }

    private static String findLongestSubstring(String input, int k) {
        Set<Character> inputSet = populateSetFromInput(input);
        if (k > inputSet.size()) {
            return "NOT POSSIBLE";
        }

        char[] arr = input.toCharArray();
        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int windowUniqueCharCount = 0;
        Map<Character, Integer> windowFrequenceyMap = new HashMap<>();
        int i = -1;
        int j = -1;

        while (end < arr.length) {
            char curr = arr[end];

            if (windowUniqueCharCount <= k) {
                if (!windowFrequenceyMap.containsKey(curr)) {
                    windowFrequenceyMap.put(curr, 1);
                    windowUniqueCharCount++;
                    // end++;
                } else {
                    windowFrequenceyMap.put(curr, windowFrequenceyMap.get(curr) + 1);
                }
            }

            if (windowUniqueCharCount == k) {
                int diff = end - start;
                if (diff > maxLen) {
                    maxLen = diff;
                    i = start;
                    j = end;
                }
                if (end + 1 < arr.length && arr[end + 1] == arr[end]) {
                    end++;
                    continue;
                }
            }


            while (windowUniqueCharCount >= k) {
                char charAtStart = arr[start];
                windowFrequenceyMap.put(charAtStart, windowFrequenceyMap.get(charAtStart) - 1);
                if (windowFrequenceyMap.get(charAtStart) == 0) {
                    windowFrequenceyMap.remove(charAtStart);
                    windowUniqueCharCount--;
                }
                start++;
            }
            end++;
        }
        return input.substring(i, j + 1);
    }

    private static Set<Character> populateSetFromInput(String input) {
        Set<Character> set = new HashSet<>();
        for(char c : input.toCharArray()){
            set.add(c);
        }
        return set;
    }
}
