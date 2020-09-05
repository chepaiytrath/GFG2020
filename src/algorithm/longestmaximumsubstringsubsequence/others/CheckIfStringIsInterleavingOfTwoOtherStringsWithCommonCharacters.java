package algorithm.longestmaximumsubstringsubsequence.others;

//https://www.geeksforgeeks.org/find-if-a-string-is-interleaved-of-two-other-strings-dp-33/
public class CheckIfStringIsInterleavingOfTwoOtherStringsWithCommonCharacters {
    public static void main(String[] args) {
        /*String parent = "abcdef";   //p
        String child1 = "ace";  //c1
        String child2 = "bdf";  //c2*/


        // BETTER EXAMPLE : RANDOMNESS AND NEED TO BACKTRACK UNLIKE THE SIMPLE EXAMPLE
        String parent = "aaxaby"; //p
        String child1 = "aab";    //c1
        String child2 = "axy";    //c2
        System.out.println(checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, 0, 0, 0));
    }

    private static boolean checkIfStringIsInterleavingOfTwoOtherStrings(String parent, String child1, String child2,
                                                                        int p, int c1, int c2) {
        // ALL THREE POINTERS HAVE REACHED THE END
        if (p == parent.length() && c1 == child1.length() && c2 == child2.length()) {
            return true;
        }

        //ONLY PARENT POINTER REACHED END AND NOT THE OTHER TWO
        if (p == parent.length()) {
            return false;
        }

        boolean isSubsequence = false;

        //BACKTRACKING
        // CHECK IF FIRST CHARACTERS IN PARENT AND CHILD1 MATCH
        if (c1 < child1.length() && parent.charAt(p) == child1.charAt(c1)) {
            isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, p + 1, c1 + 1, c2);
        }
        // IF THE ABOVE DOESNT MATCH, CHECK IF FIRST CHARACTERS IN PARENT AND CHILD2 MATCH
        if (!isSubsequence) {
            if (c2 < child2.length() && parent.charAt(p) == child2.charAt(c2)) {
                isSubsequence = checkIfStringIsInterleavingOfTwoOtherStrings(parent, child1, child2, p + 1, c1, c2 + 1);
            }
        }
        return isSubsequence;
    }
}
