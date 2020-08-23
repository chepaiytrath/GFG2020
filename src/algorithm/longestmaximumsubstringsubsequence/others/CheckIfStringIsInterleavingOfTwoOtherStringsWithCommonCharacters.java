package algorithm.longestmaximumsubstringsubsequence.others;

//https://www.geeksforgeeks.org/find-if-a-string-is-interleaved-of-two-other-strings-dp-33/
public class CheckIfStringIsInterleavingOfTwoOtherStringsWithCommonCharacters {
    public static void main(String[] args) {
        String parent = "abcdef";   //i
        String child1 = "ace";  //j
        String child2 = "bdf";  //k
        System.out.println(checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, 0, 0, 0));
    }

    private static boolean checkIfStringIsInterleavingOfTwoOtherStrings(String parent, String child1, String child2,
                                                                        int i, int j, int k) {
        // ALL THREE POINTERS HAVE REACHED THE END
        if (i == parent.length() && j == child1.length() && k == child2.length()) {
            return true;
        }

        //ONLY PARENT POINTER REACHED END AND NOT THE OTHER TWO
        if (i == parent.length()) {
            return false;
        }

        boolean isSubsequence = false;

        // CHECK IF FIRST CHARACTERS IN PARENT AND CHILD1 MATCH
        if (j < child1.length() && parent.charAt(i) == child1.charAt(j)) {
            isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, i + 1, j + 1, k);
        }
        // IF THE ABOVE DOESNT MATCH, CHECK IF FIRST CHARACTERS IN PARENT AND CHILD2 MATCH
        if (!isSubsequence) {
            if (k < child2.length() && parent.charAt(i) == child2.charAt(k)) {
                isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, i + 1, j, k + 1);
            }
        }
        return isSubsequence;
    }
}
