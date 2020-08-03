package company.google.medium;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String input = "donutsandwafflemakemehungry";
        String pattern = "flea";
        System.out.println(findMinimumWindowSubstring(input, pattern));
    }

    private static String findMinimumWindowSubstring(String input, String pattern) {
        int length = input.length();
        String minLengthSubstring = "";
        int minLength = Integer.MAX_VALUE;

        Map<Character, Integer> windowFreqMap = new HashMap<>();
        Map<Character, Integer> patternFreqMap = new HashMap<>();
        populateCharacterFrequencies(patternFreqMap, pattern);

        int totalCharactersInPattern = patternFreqMap.size();
        int totalCharactersMatchedInWindow = 0; //i.e. Frequencies of characters are equal in both maps

        int left = 0;
        int right = 0;

        while (left < length && right < length) {
            char charAtRight = input.charAt(right);
            putCharIntoFrequencyMap(windowFreqMap, charAtRight);

            if (patternFreqMap.containsKey(charAtRight)
                    && patternFreqMap.get(charAtRight).intValue() == windowFreqMap.get(charAtRight).intValue()) {
                totalCharactersMatchedInWindow++;
            }
            while (totalCharactersMatchedInWindow == totalCharactersInPattern && left <= right) {
                int currLen = right - left + 1;
                if (currLen < minLength) {
                    minLengthSubstring = input.substring(left, right + 1);
                    minLength = currLen;
                }

                char charAtLeft = input.charAt(left);
                windowFreqMap.put(charAtLeft, windowFreqMap.get(charAtLeft) - 1);
                if(patternFreqMap.containsKey(charAtLeft) && windowFreqMap.get(charAtLeft).intValue() < patternFreqMap.get(charAtLeft).intValue()){
                    totalCharactersMatchedInWindow--;
                }
                left++;
            }
            right++;
        }
        return minLengthSubstring;
    }

    private static void populateCharacterFrequencies(Map<Character, Integer> map, String pattern) {
        for (char c : pattern.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
    }

    private static void putCharIntoFrequencyMap(Map<Character, Integer> map, char charAtRight) {
        int count = map.getOrDefault(charAtRight, 0);
        map.put(charAtRight, count + 1);
    }
}
