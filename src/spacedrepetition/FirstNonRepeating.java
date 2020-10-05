package spacedrepetition;

public class FirstNonRepeating {
    public static void main(String[] args) {
        Character c = null;
        String str1 = "hello";
        c = first(str1);
        System.out.println((c == null) ? "-1" : c);
        String str2 = "zxvczbtxyzvy";
        System.out.println(first(str2));
        String str3 = "xxyyzz";
        c = first(str3);
        System.out.println((c == null) ? "-1" : c);
    }

    private static Character first(String str) {
        int[][] dp = new int[2][26];

        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            dp[0][c - 'a']++;
            dp[1][c - 'a'] = i;
        }


        int index = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (dp[0][i] == 1 && dp[1][i] < index) {
                index = dp[1][i];
            }
        }
        return index == Integer.MAX_VALUE ? null : arr[index];
    }

}
