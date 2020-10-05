package algorithm.palindromesanagram;

//https://www.geeksforgeeks.org/check-anagram-string-palindrome-not/
public class AnagramPalindrome {
    public static void main(String[] args) {
        System.out.println(isPossible());
    }

    private static boolean isPossible() {
        //String str = "geeksoskeeg";
        //String str = "geeksforgeeks";
        String str = "naman";
        int[] dp = new int[26];
        char[] arr = str.toCharArray();
        for (char c : arr) {
            dp[c - 'a']++;
        }

        boolean oddChecked = false;
        for (int i = 0; i < 26; i++) {
            if (dp[i] % 2 != 0) {
                if (!oddChecked) {
                    oddChecked = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
