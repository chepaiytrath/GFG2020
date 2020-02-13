//https://www.geeksforgeeks.org/count-occurences-of-anagrams/
package company.intuit;

public class CountOccurencesOfAnagrams {
    private final static int MAX_CHAR = 256;

    public static void main(String[] args) {
        String input = "FORXXORFXDOFR";
        String pattern = "FOR";

        int count = countOccurencesOfAnagrams(input, pattern);
        System.out.println("Count = " + count);
    }

    private static int countOccurencesOfAnagrams(String input, String pattern) {
        int count = 0;
        int patternLen = pattern.length();
        int inputLen = input.length();

        int charArr[] = new int[MAX_CHAR];

        for (int i = 0; i < patternLen; i++) {
            charArr[pattern.charAt(i)]++;
        }

        for (int i = 0; i < patternLen; i++) {
            charArr[input.charAt(i)]--;
        }

        if (isCharArrZero(charArr)) count++;

        for (int i = patternLen; i < inputLen; i++) {
            charArr[input.charAt(i)]--;
            charArr[input.charAt(i - patternLen)]++;
            if (isCharArrZero(charArr)) count++;
        }

        return count;
    }

    private static boolean isCharArrZero(int[] charArr) {
        for (int x : charArr) {
            if (x != 0) return false;
        }
        return true;
    }
}
