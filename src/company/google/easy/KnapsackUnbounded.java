package company.google.easy;

public class KnapsackUnbounded {
    public static void main(String[] args) {
        int sack = 100;
        int weights[] = {5, 10, 15};
        int prices[] = {10, 30, 20};

        int maxWtPossible = findMaximumWeightInUnboundedKnapsack(weights, prices, sack);
        System.out.println(maxWtPossible);
    }

    private static int findMaximumWeightInUnboundedKnapsack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[] dp = new int[W + 1];
        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < n; j++) {
                int currentWeight = wt[j];
                if (currentWeight <= i) {
                    dp[i] = Math.max(dp[i], val[j] + dp[i - currentWeight]);
                }
            }
        }
        return dp[dp.length - 1];
    }
}