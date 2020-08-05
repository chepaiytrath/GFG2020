package algorithm.puzzles;

public class LargestWordInDictionaryByDeletingCharacaters {
    public static void main(String[] args) {
        String[] dict = {"ale", "apple", "monkey", "plea"};
        String str = "abpcplea";
        System.out.println(findLargestWordInDictionaryByDeletingCharacters(dict, str));
    }

    private static String findLargestWordInDictionaryByDeletingCharacters(String[] dict, String str) {
        int matchedLength = 0;
        String result = "";
        for (String word : dict) {
            if (word.length() > matchedLength && isSubsequence(word, str)) {
                matchedLength = word.length();
                result = word;
            }
        }
        return result;
    }

    private static boolean isSubsequence(String word, String str) {
        int i = 0, j = 0;
        while (i < word.length() && j < str.length()) {
            if(word.charAt(i) == str.charAt(j)){
                i++;
            }
            j++;
        }
        return i == word.length();
    }
}