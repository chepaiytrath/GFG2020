package company.google.easy;

public class SaveIronmanPalindromeCheckWithoutSpecialCharacters {
    public static void main(String[] args) {
        String str = "I1 am :IronnorI Ma, 1i";
//        String str = "Ab?121/Ba";

        boolean isPal = checkIfPalindrome(str);
        System.out.println(isPal);
    }

    private static boolean checkIfPalindrome(String str) {
        int lo = 0;
        int hi = str.length() - 1;
        char[] arr = str.toCharArray();
        while (hi > lo) {
            // ARR[LO] IS SPACE : NEITHER A DIGIT NOR AN ALPHABET
            if (!(Character.isDigit(arr[lo]) || Character.isAlphabetic(arr[lo]))) {
                lo++;
                continue;
            }
            // ARR[HI] IS SPACE : NEITHER A DIGIT NOR AN ALPHABET
            else if (!(Character.isDigit(arr[hi]) || Character.isAlphabetic(arr[hi]))) {
                hi--;
                continue;
            } else {
                // EITHER BOTH ARE DIGITS OR BOTH ARE LETTERS OR ONE IS DIGIT/LETTER
                if (Character.isDigit(arr[lo]) && arr[lo] != arr[hi]) {
                    return false;
                } else if (Character.isAlphabetic(arr[lo]) && Character.toLowerCase(arr[lo]) != Character.toLowerCase(arr[hi])) {
                    return false;
                }
            }
            lo++;
            hi--;
        }
        return true;
    }
}
