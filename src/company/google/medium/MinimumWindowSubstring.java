package company.google.medium;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String input = "donutsandwafflemakemehungry";
        String pattern = "flea";
        System.out.println(minWindow(input, pattern));
    }

    public static String minWindow(String input, String pattern) {
        Map<Character, Integer> patternFrequencyMap = populateFrequency(pattern);
        Map<Character, Integer> windowFrequencyMap = new HashMap<>();
        int matchedCharacterCount = 0;
        int totalCharacterCount = patternFrequencyMap.size();

        int n = input.length();
        int start = 0;
        int end = 0;

        String res = "";
        int minLength = Integer.MAX_VALUE;

        while (start < n && end < n) {
            char charAtEnd = input.charAt(end);
            if (patternFrequencyMap.containsKey(charAtEnd)) {
                windowFrequencyMap.put(charAtEnd, windowFrequencyMap.getOrDefault(charAtEnd, 0) + 1);
                if (windowFrequencyMap.get(charAtEnd).intValue() == patternFrequencyMap.get(charAtEnd).intValue()) {
                    matchedCharacterCount++;
                }
            }
            while (matchedCharacterCount == totalCharacterCount && start <= end) {
                int currentWindowSize = end - start + 1;
                if (currentWindowSize < minLength) {
                    res = input.substring(start, end + 1);
                    minLength = currentWindowSize;
                }

                char charAtStart = input.charAt(start);
                if (patternFrequencyMap.containsKey(charAtStart)) {
                    windowFrequencyMap.put(charAtStart, windowFrequencyMap.get(charAtStart) - 1);
                    if (windowFrequencyMap.get(charAtStart).intValue() < patternFrequencyMap.get(charAtStart).intValue()) {
                        matchedCharacterCount--;
                    }
                }
                start++;
            }
            end++;
        }

        return res;
    }

    public static Map<Character, Integer> populateFrequency(String pattern) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : pattern.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}
