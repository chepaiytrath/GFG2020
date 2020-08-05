package algorithm.longestmaximumsubstringsubsequence.others;

public class CheckIfStringIsInterleavingOfTwoOtherStringsWithCommonCharacters {
    public static void main(String[] args) {
        String parent = "abcdef";
        String child1 = "ace";
        String child2 = "bdf";
        System.out.println(checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, 0, 0, 0));
    }

    private static boolean checkIfStringIsInterleavingOfTwoOtherStrings(String parent, String child1, String child2, int i, int j, int k) {
        if (i == parent.length() && j == child1.length() && k == child2.length()) {
            return true;
        }
        if (i == parent.length()) {
            return false;
        }

        boolean isSubsequence = false;
        if (j < child2.length() && parent.charAt(i) == child1.charAt(j)) {
            isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, i + 1, j + 1, k);
        }
        if (!isSubsequence) {
            if (k < child1.length() && parent.charAt(i) == child2.charAt(k)) {
                isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, i + 1, j, k + 1);
            }
        }
        return isSubsequence;
    }
}
