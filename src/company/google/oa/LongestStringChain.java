package company.google.oa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LongestStringChain {
    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println();
        longestStrChain(arr);
    }

    // LOGIC: KINDA SIMILAR TO LONGEST INCREASING SUBSEQUENCE
    // MAP IS USED AS DP ARRAY
    // FOR EACH WORD: FIND ITS PERMUTATIONS WHEREIN ONE BY ONE CHARACTERS ARE REMOVED AND THE REST IS CHECKED
    // FOR THE PERMUTATIONS FIND THE ONE WITH THE MAX VALUE IN ITS COUNT
    // SAVE MAX COUNT + 1 FOR THE CURRENT WORD : ONE CHARACTER WILL BE ADDED BECAUSE YOU REMOVED ONE CHARACTER TO ACHIEVE THIS STRING EARLIER
    public static int longestStrChain(String[] words) {
        int res = 0;

        //MEMOIZATION
        Map<String, Integer> map = new HashMap<>();

        // SORT THEM FIRST : THIS WILL GET YOU AN ASCENDING LIST BASED ON THEIR LENGTH : WHICH YOU NEED FOR FINDING A SUBARRAY IN WHICH STRINGS FOLLOW AN INCREASING LENGTH ORDER
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        for (String word : words) {
            int max = 0;
            for (int i = 0; i < word.length(); i++) {
                // REMOVE CHAR AT i AND CREATE A REMAINING SUBSTRING. CHECK THE SUBSTRING'S COUNT
                String rem = word.substring(0, i) + word.substring(i + 1);
                int remCount = map.getOrDefault(rem, 0);
                max = Math.max(max, remCount);
            }
            // 1 + MAX OF WHATEVER YOU GOT FROM THE DIFFERENT PERMUTATIONS
            // BECAUSE: YOU REMOVED ONE CHARACTER EARLIER
            max++;
            map.put(word, max);

            // AT EACH STEP KEEP TAKING THE MAXIMUM VALUE OF MAX THAT IS FOUND FOR EACH WORD
            res = Math.max(res, max);
        }
        return res;
    }
}