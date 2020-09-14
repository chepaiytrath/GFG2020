package algorithm.basictechniques.mathematical;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(findFibonacciNum2(1));
        System.out.println(findFibonacciNum2(2));
        System.out.println(findFibonacciNum2(3));
        System.out.println(findFibonacciNum2(4));
        System.out.println(findFibonacciNum2(5));
        System.out.println(findFibonacciNum2(10));
    }

    private static int findFibonacciNum(int num) {
        int prev = 0;
        int curr = 1;

        for (int i = 0; i < num; i++) {
            int temp = curr;
            curr = curr + prev;
            prev = temp;
        }
        return curr;
    }

    private static int findFibonacciNum2(int num) {
        int[] dp = new int[num + 2];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[dp.length - 1];
    }

}
