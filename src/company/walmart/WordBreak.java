package company.walmart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public static void main(String[] args) {
        String[] dict = {"i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango"};
        String str = "ilikesamsung";

        String output = wordBreak(dict, str);
        System.out.println(output);
    }

    private static String wordBreak(String[] dict, String str) {
        List<String> result = new ArrayList<>();
        wordBreakUtil(str, result, Arrays.asList(dict));
        return result.isEmpty() ? "NO" : "YES";
    }

    private static void wordBreakUtil(String str, List<String> result, List<String> dict) {
        if (str.isEmpty()) {
            return;
        }
        int i = 1;
        String substring = "";
        while (i <= str.length()) {
            substring = str.substring(0, i);
            if (dict.contains(substring)) {
                result.add(substring);
                //dict.remove(substring);
                break;
            }
            i++;
        }
        wordBreakUtil(str.substring(i), result, dict);
    }
}
