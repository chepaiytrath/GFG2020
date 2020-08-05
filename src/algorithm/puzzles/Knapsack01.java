package algorithm.puzzles;

public class Knapsack01 {
    public static void main(String[] args) {
        int sack = 5;
        int[] weights = new int[]{5, 3, 4, 2};
        int[] prices = new int[]{60, 50, 70, 30};

//        int prices[] = new int[]{60, 100, 120};
//        int weights[] = new int[]{10, 20, 30};
//        int sack = 50;

//        int maxWtPossible = findMaxWeightPossibleWithRecursion(weights, prices, sack, 0);
        int maxWtPossible = findMaxWeightPossibleWithDynamicProgramming(weights, prices, sack);
        System.out.println(maxWtPossible);
    }

    private static int findMaxWeightPossibleWithRecursion(int[] weights, int[] prices, int sack, int itemNo) {
        if (itemNo == weights.length) {
            return 0;
        }
        if (weights[itemNo] > sack) {
            return findMaxWeightPossibleWithRecursion(weights, prices, sack, itemNo + 1);
        }

        // current item : denoted by itemNo. Each iteration itemNo + 1 is sent
        // 2 choices: Either put the current item into the sack or don't
        // Answer is max of two
        // When current item is put into sack, then its value plus whatever remains is the answer of that choice
        // If it is not put into sack, then its value is not added and only recursive call is made to the rest
        return Math.max(prices[itemNo] + findMaxWeightPossibleWithRecursion(weights, prices, sack - weights[itemNo], itemNo + 1),
                findMaxWeightPossibleWithRecursion(weights, prices, sack, itemNo + 1));
    }

    private static int findMaxWeightPossibleWithDynamicProgramming(int[] weights, int[] prices, int sack) {
        int n = weights.length;
        int[][] dp = new int[n][sack + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (i == 0) {
                    if (j >= weights[i]) {
                        dp[i][j] = prices[i];
                    }
                    continue;
                }
                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], prices[i] + dp[i - 1][j - weights[i]]);
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n-1][sack];
    }
}