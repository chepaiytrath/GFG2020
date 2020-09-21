package company.amazon.oa.amcat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubstringsOfSizeKWithKDistinctCharacters {
    public static void main(String[] args) {
        System.out.println(uniqueSubstringSizeKWithHashSet("awaglknagawunagwkwagl", 4));
        System.out.println(uniqueSubstringSizeKWithArr("awaglknagawunagwkwagl", 4));
    }

    //SLIDING WINDOW
    private static List<String> uniqueSubstringSizeKWithHashSet(String str, int k) {
        Set<String> res = new HashSet<>();
        Set<Character> window = new HashSet<>();
        int n = str.length();
        int start = 0;
        int end = 0;
        while (end < n) {
            while (window.contains(str.charAt(end))) {
                window.remove(str.charAt(start));
                start++;
            }
            window.add(str.charAt(end));

            if (window.size() == k) {
                // ADD TO RESPONSE
                res.add(str.substring(start, end + 1));

                // MOVE THE START ONE STEP AHEAD
                window.remove(str.charAt(start));
                start++;
            }
            end++;
        }
        return new ArrayList<>(res);
    }

    public static List<String> uniqueSubstringSizeKWithArr(String s, int k) {
        Set<String> res = new HashSet<>();
        int[] countArr = new int[26];
        int start = 0;
        int end = 0;
        while (start <= end && end < s.length()) {
            countArr[s.charAt(end) - 'a']++;
            while (countArr[s.charAt(end) - 'a'] != 1) {
                countArr[s.charAt(start) - 'a']--;
                start++;
            }

            if (end - start + 1 == k) {
                // ADD TO RESPONSE
                res.add(s.substring(start, end + 1));

                // MOVE THE START ONE STEP AHEAD
                countArr[s.charAt(start) - 'a']--;
                start++;
            }
            end++;
        }
        return new ArrayList<>(res);
    }
}

