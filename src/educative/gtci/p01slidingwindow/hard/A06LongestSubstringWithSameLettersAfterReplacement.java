package educative.gtci.p01slidingwindow.hard;

import java.util.HashMap;
import java.util.Map;

// #REVISIT
// #DIFFERENTPATTERN
public class A06LongestSubstringWithSameLettersAfterReplacement {
    public static void main(String[] args) {
        longestSubstr();
    }

    private static void longestSubstr() {
        String str = "abacccdef";
        int k = 2; // Can replace max k characters
        int maxLen = Integer.MIN_VALUE;

        char[] arr = str.toCharArray();
        int start = 0;
        int end = 0;
        int n = arr.length;

        Map<Character, Integer> map = new HashMap<>();
        int maxDuplicatesSoFar = 0;
        while (end < n) {
            char charAtEnd = arr[end];
            map.put(charAtEnd, map.getOrDefault(charAtEnd, 0) + 1);
            // Does adding this character update the maxDuplicatesSoFar.
            // If yes, then maxLen will update too because it will be able to accommodate one more character in window
            int currFreq = map.get(charAtEnd);
            maxDuplicatesSoFar = Math.max(maxDuplicatesSoFar, currFreq);

            int windowSize = end + 1 - start;
            // Increment start only by one step. This will break the condition
            // Not concerned with invalid values in window in some iterations.
            // Only concerned with finding the next window with a duplicate count of a character more than the maxDuplicatesSoFar
            if (windowSize - maxDuplicatesSoFar > k) {
                char charAtStart = arr[start];
                map.put(charAtStart, map.get(charAtStart) - 1);
                start++;
            }

            maxLen = Math.max(maxLen, end + 1 - start);
            end++;
        }

        System.out.println(maxLen);
    }
}
