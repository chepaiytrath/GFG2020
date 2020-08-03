package algorithm.puzzles;

class PaintersPartitionProblem {
    public static void main(String args[]) {
        int arr[] = {10, 20, 60, 50, 30, 40};

        // Calculate size of array.
        int n = arr.length;
        int k = 3;
        System.out.println(partition(arr, n, k));
    }

    //
    private static int partition(int arr[], int n, int k) {
        if (k == 1) // one partition
            return sum(arr, 0, n - 1);
        if (n == 1)  // one board
            return arr[0];

        int best = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++)
            best = Math.min(best, Math.max(partition(arr, i, k - 1),
                    sum(arr, i, n - 1)));

        return best;
    }

    private static int sum(int[] arr, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += arr[i];
        }
        return sum;
    }
} 