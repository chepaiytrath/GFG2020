package algorithm.basictechniques.kadane;

//#REVISIT
public class A03MaximumSumSubarrayKadaneWithPrinting {
    public static void main(String[] args) {
        printMaximumSumSubarray();
        System.out.println();
        printMaximumSumSubarray();
    }

    private static void printMaximumSumSubarray() {
        int[] arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        // int[] arr = new int[] { -5, -4, -3, -2, -1 };

        int n = arr.length;
        int start = -1;
        int end = -1;

        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // Same as basic kadane
            currSum = Math.max(arr[i], currSum + arr[i]);

            // If previous sum decreases the currSum, then currSum = arr[i]
            // That is beginning of each maximum sum subarray
            if (currSum == arr[i]) {
                start = i;
            }

            // If the currSum is larger than the existing maxSum
            // that means it is a maximum sum subarray till that point
            maxSum = Math.max(maxSum, currSum);
            if (maxSum == currSum) {
                end = i;
            }
        }

        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}