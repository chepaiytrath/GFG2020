package company.paypal;

// https://stackoverflow.com/questions/63871852/optimization-of-index-coloring-problem-with-one-color-adjacent-allowed?noredirect=1#comment112946651_63871852
public class ButterflyEffectPaypal {
    public static void main(String[] args) {
        int n = 4;
        int k = 3;
        int x = 1;
        placeButterfliesDP(n, k, x);
        placeButterfliesBacktracking(n, k, x);
    }

    private static void placeButterfliesDP(int n, int k, int x) {
        // X REFERS ONLY TO ONE COLORID
        int[][] dp = new int[2][n];
        dp[0][0] = 1; // ENDING WITH X
        dp[1][0] = k - 1; // NOT ENDING WITH X
        int mod = 1000000007;
        for (int i = 1; i < n; i++) {
            dp[0][i] = (dp[0][i - 1] % mod + dp[1][i - 1] % mod) % mod;

            // K - 1 FOR X-CASE : BECAUSE 
            // K - 2 FOR NON-X-CASE : BECAUSE WE CAN NEITHER USE X NOR THE SAME NUMBER RIGHT BEFORE : SO 2 NUMBERS LESS
            dp[1][i] = (((k - 1) * dp[0][i - 1]) % mod + ((k - 2) * dp[1][i - 1]) % mod) % mod;
        }
        System.out.println(dp[0][n - 1] + dp[1][n - 1]);
    }


    static int count = 0;

    private static void placeButterfliesBacktracking(int n, int k, int x) {
        int currpos = 0;
        int[] arr = new int[n];
        placeButterfliesUtil(n, k, x, currpos, arr);
        System.out.println(count % 1000000007);
        count = 0;
    }

    private static void placeButterfliesUtil(int n, int k, int x, int currpos, int[] arr) {
        if (currpos == n) {
            int mod = 1000000007;
            count = count % mod;
            count++;
            return;
        }

        for (int colorId = 1; colorId <= k; colorId++) {
            if (isSafe(colorId, currpos, arr, x)) {
                arr[currpos] = colorId;
                placeButterfliesUtil(n, k, x, currpos + 1, arr);
            }
        }
    }

    private static boolean isSafe(int colorId, int currpos, int[] arr, int x) {
        if (currpos < arr.length && currpos > 0) {
            if (colorId != x && arr[currpos - 1] == colorId) {
                return false;
            }
        }
        return true;
    }
}