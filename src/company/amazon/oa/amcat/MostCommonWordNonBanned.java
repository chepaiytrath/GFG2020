package company.amazon.oa.amcat;

import java.util.*;

//https://leetcode.com/problems/most-common-word/submissions/
class MostCommonWordNonBanned {
    public static void main(String[] args) {
        String paragraph = "Bob. hIt, baLl";
        String[] banned = new String[]{"bob", "hit"};
        System.out.println(mostCommonWord(paragraph, banned));
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.split("\\W");
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            if (!word.isEmpty() && !bannedSet.contains(word)) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }

        /*List<String> keys = new ArrayList(countMap.keySet());
        Collections.sort(keys, (a, b) -> countMap.get(b) - countMap.get(a));
        return keys.get(0);*/

        List<String> keys = new ArrayList(countMap.keySet());
        return Collections.max(keys, (a, b) -> countMap.get(b) - countMap.get(a));
    }
}