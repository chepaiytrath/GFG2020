package company.walmart2;

import java.util.*;

public class Rough {

    public static void main(String[] args) {
        System.out.println(programmer());
    }

    private static int programmer(){
        String input = "progxrammerrxproxgrammer";
        char[] arr = input.toCharArray();

        Map<Character, Integer> programmerFrequencyMap = populateFrequency("programmer");
        Map<Character, Integer> windowFrequencyMap = new HashMap<>();
        int matchedCharacterCount = 0;
        int totalCharacterCount = programmerFrequencyMap.size();

        int n = input.length();
        int x = -1;
        int end = 0;

        while (end < n) {
            char charAtEnd = input.charAt(end);
            if (programmerFrequencyMap.containsKey(charAtEnd)) {
                windowFrequencyMap.put(charAtEnd, windowFrequencyMap.getOrDefault(charAtEnd, 0) + 1);
                if (windowFrequencyMap.get(charAtEnd).intValue() == programmerFrequencyMap.get(charAtEnd).intValue()) {
                    matchedCharacterCount++;
                }
            }
            if(matchedCharacterCount == totalCharacterCount){
                x = end;
                break;
            }
            end++;
        }

        int y = -1;
        end = arr.length - 1;
        windowFrequencyMap = new HashMap<>();
        matchedCharacterCount = 0;
        totalCharacterCount = programmerFrequencyMap.size();
        while (end > x) {
            char charAtEnd = input.charAt(end);
            if (programmerFrequencyMap.containsKey(charAtEnd)) {
                windowFrequencyMap.put(charAtEnd, windowFrequencyMap.getOrDefault(charAtEnd, 0) + 1);
                if (windowFrequencyMap.get(charAtEnd).intValue() == programmerFrequencyMap.get(charAtEnd).intValue()) {
                    matchedCharacterCount++;
                }
            }
            if(matchedCharacterCount == totalCharacterCount){
                y = end;
                break;
            }
            end--;
        }

        return (y - x) - 1 ;
    }

    public static Map<Character, Integer> populateFrequency(String programmer) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : programmer.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}