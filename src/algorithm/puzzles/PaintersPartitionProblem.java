package algorithm.puzzles;

//https://www.geeksforgeeks.org/painters-partition-problem/
class PaintersPartitionProblem {
    public static void main(String args[]) {
        int arr[] = {10, 20, 60, 50, 30, 40};

        // Calculate size of array.
        int n = arr.length;
        int k = 3;
        System.out.println(partition(arr, n, k));
//        System.out.println(partitionWithDp(arr, k));
    }

    // #REVISIT
    // #COMPLICATED
    // NOT EVEN DP SOLUTION
    private static int partition(int arr[], int n, int k) {
        if (k == 1) // one worker
        {
            return sum(arr, 0, n - 1);
        }
        if (n == 1)  // one board
        {
            return arr[0];
        }

        int best = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int left = partition(arr, i, k - 1); //Check for 0 to i boards with k-1 workers
            int right = sum(arr, i, n - 1);     //Check for i to n-1 boards with 1 worker
            best = Math.min(best, Math.max(left, right));
        }
        return best;
    }

    private static int sum(int[] arr, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // DP SOLUTION
    // BASE CASE 1: 1 WORKER, MULTIPLE JOBS : SUM OF TIME OF EACH JOB
    // BASE CASE 2: MULTIPLE WORKERS, 1 JOB : VALUE OF ONE JOB
    private static int partitionWithDp(int[] boards, int workers) {

        int dp[][] = new int[workers + 1][boards.length];

        int sum[] = new int[boards.length];
        int cumsum = 0;
        // Fill row dp[1] with cumulative sum array
        // BASE CASE 1
        for (int i = 0; i < boards.length; i++) {
            cumsum += boards[i];
            sum[i] = cumsum;
        }

        dp[1] = sum;
        // Start from i = 2 because row dp[1] is already filled with cumulative sum array
        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if (j == 0) {
                    // BASE CASE 2
                    dp[i][j] = boards[0];
                    continue;
                }

                int best = Integer.MAX_VALUE;
                // FOR EACH PARTITION FROM 0 TO J CHECK MAXIMUM VALUE FROM BOTH PARTITION
                // PARTITION LEFT IS GIVEN TO K - 1 WORKERS AND PARTITION RIGHT TO 1 WORKER(SO USE CUMULATIVE SUM ARR)
                for (int k = 0; k <= j; k++) {
                    // FROM ALL THE MAXIMUMS FIND THE SMALLEST MAXIMUM WHICH WILL BE THE ANSWER
                    best = Math.min(best, Math.max(dp[i - 1][k], sum[j] - sum[k]));
                }
                dp[i][j] = best;
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}