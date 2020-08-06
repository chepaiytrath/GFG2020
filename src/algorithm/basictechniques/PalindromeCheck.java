package algorithm.basictechniques;

public class PalindromeCheck {
    public static void main(String[] args) {
        String input = "malayalam";
        System.out.println(checkIfPalindromeWithRecursion(input, 0, input.length() - 1));
    }

    private static boolean checkIfPalindromeWithoutRecursion(String input) {
        int lo = 0;
        int hi = input.length() - 1;
        char[] arr = input.toCharArray();
        while (hi > lo) {
            if (arr[lo] != arr[hi]) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    private static boolean checkIfPalindromeWithRecursion(String input, int i, int j) {
        if (i == j || i > j) {
            return true;
        }
        if (input.charAt(i) != input.charAt(j)) {
            return false;
        } else {
            return checkIfPalindromeWithRecursion(input, i + 1, j - 1);
        }
    }
}
