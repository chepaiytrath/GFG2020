package company.walmart2.easy;

public class LongestWordInDictionaryThatCanBeFormedByDeletingCharactersInInputString {
    // Max of Longest isSubsequence

    public static void main(String[] args) {
        String[] dictionary = new String[]{"ale", "apple", "monkey", "plea"};
        String input = "abpcplea";
        System.out.println(findLongestWordInDictionaryThatCanBeFormedByDeletingCharactersInInputString(dictionary, input));
    }

    private static String findLongestWordInDictionaryThatCanBeFormedByDeletingCharactersInInputString(String[] dictionary, String input) {
        String result = "";
        int maxLength = 0;
        for (String word : dictionary) {
            if (word.length() > maxLength && isSubsequence(word, input)) {
                maxLength = word.length();
                result = word;
            }
        }
        return result;
    }

    private static boolean isSubsequence(String word, String input) {
        int j = 0;
        for (int i = 0; i < input.length() && j < word.length(); i++) {
            if (word.charAt(j) == input.charAt(i)) {
                j++;
            }
        }
        return j == word.length();
    }
}