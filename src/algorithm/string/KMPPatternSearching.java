package algorithm.string;

// #REVISIT
//https://www.youtube.com/watch?v=BXCEFAzhxGY
public class KMPPatternSearching {
    public static void main(String[] args) {
        patternSearch();
    }


    public static void patternSearch() {
        String s1 = "lrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmcoq";
        String s2 = "hnwnkuewhsqmgbbuqcljjivswmdkqtbxixmvtrrbljptnsn";

        if (s2 == null || s2.length() == 0) {
            System.out.println("FOUND AT 0");
        }
        if (s1 == null || s1.length() == 0) {
            System.out.println("FOUND AT -1");
        }

        char[] text = s1.toCharArray();
        char[] pat = s2.toCharArray();

        int[] lps = new int[s2.length()];
        populateLPS(pat, lps);

        int textptr = 0;
        int patptr = 0;
        int index = -1;
        while (textptr < text.length) {
            // IF ELEMENTS ON THE INDICES MATCH, INCREMENT BOTH INDICES
            if (text[textptr] == pat[patptr]) {
                textptr++;
                patptr++;
            }

            // IF PATTERN INDEX HAS REACHED ITS FINAL POSITION, IT MEANS WHOLE PATTERN MATCHED
            if (patptr == pat.length) {
                index = textptr - patptr;
                break;
            } else if (textptr < text.length && text[textptr] != pat[patptr]) {
                // SIMILAR TO BELOW BACKTRACKING OF LEFT
                if (patptr == 0) {
                    textptr++;
                } else {
                    patptr = lps[patptr - 1];
                }
            }
        }
        System.out.println("FOUND AT " + index);
    }

    // PREPOPULATE LPS
    private static void populateLPS(char[] arr, int[] lps) {
        // TWO INDICES TO POPULATE WHICH INDEX THE SUFFIX WAS ALSO A PREFIX AND STORES THAT INDEX +
        // EX. ABCABC WILL SAVE 000123 : TO TELL WHICH INDEX J SHOULD JUMP TO IN ACTUAL PATTERN COMPARISON MISMATCH ABOVE
        int left = 0;
        int right = 1;

        while (right < arr.length) {
            // SIMILAR TO ACTUAL PATTERN MATCHING COMPARISON

            // IF MATCHES, THEN INCREMENT BOTH INDICES
            if (arr[left] == arr[right]) {
                lps[right] = left + 1;
                left++;
                right++;
            } else {
                // WHEN NOT MATCHED

                // IF LEFT IS STILL ZERO, SO NO FURTHER BACK TO GO, JUST SAVE LPS AS 0 AND INCREMENT RIGHT
                if (left == 0) {
                    lps[right] = 0;
                    right++;
                } else {
                    // OTHERWISE, JUMP LEFT TO WHATEVER INDEX IS STORED IN ONE STEP BACK.
                    // THAT INDEX DENOTES MATCHED TILL THAT POSITION
                    // LOOP WILL REITERATE AND COMPARE RIGHT TO THIS BACKTRACKED LEFT
                    left = lps[left - 1];
                }
            }
        }
    }
}
