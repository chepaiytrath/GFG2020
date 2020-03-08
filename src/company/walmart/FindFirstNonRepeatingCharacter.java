package company.walmart;

public class FindFirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String str = "geeksforgeeks";
        System.out.println(findFirstNonRepeatingCharacter(str));
    }

    private static char findFirstNonRepeatingCharacter(String str) {
        char c = 0;
        int[] arr = new int[256];
        for (char ch : str.toCharArray()) {
            arr[ch]++;
        }
        for (char ch : str.toCharArray()) {
            if (arr[ch] == 1) {
                return ch;
            }
        }
        return c;
    }
}
