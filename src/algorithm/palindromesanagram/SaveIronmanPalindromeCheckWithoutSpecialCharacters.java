package algorithm.palindromesanagram;

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
            if (!(Character.isDigit(arr[lo]) || Character.isAlphabetic(arr[lo]))) {
                lo++;
                continue;
            } else if (!(Character.isDigit(arr[hi]) || Character.isAlphabetic(arr[hi]))) {
                hi--;
                continue;
            } else {
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
