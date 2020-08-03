package algorithm.puzzles;

public class SpecialKeyboard {
    public static void main(String[] args) {
        System.out.println(findMaximumAsInSpecialKeyboard(7));
    }

    private static int findMaximumAsInSpecialKeyboard(int keystrokes) {
        if (keystrokes < 7) {
            return keystrokes;
        }
        int[] dp = new int[keystrokes + 1];
        dp[0] = 0;
        int i = 1;
        while (i <= 6) {
            dp[i] = i++;
        }
        while (i <= keystrokes) {
            int max = Math.max(2 * dp[i - 3], Math.max(3 * dp[i - 4], 4 * dp[i - 5]));
            dp[i++] = max;
        }
        return dp[keystrokes];
    }
}
