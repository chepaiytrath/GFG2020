package leetcode.allproblems;

public class A0121BestTimeToBuyAndSellStock1 {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(arr));
    }

    private static int maxProfit(int[] arr) {
        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > min) {
                maxProfit = Math.max(maxProfit, arr[i] - min);
            }
        }
        return maxProfit == Integer.MIN_VALUE ? 0 : maxProfit;
    }
}