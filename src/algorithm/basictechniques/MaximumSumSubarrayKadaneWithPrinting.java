package algorithm.basictechniques;

public class MaximumSumSubarrayKadaneWithPrinting {
    public static void main(String[] args) {
        int[] arr = new int[] { -2, -3, 4, -1, -2, 1, 5, -3 };
        // int[] arr = new int[] { -5, -4, -3, -2, -1 };

        int currMax = 0;
        int maxSum = Integer.MIN_VALUE;

        int start = -1;
        int end = -1;
        for (int i = 0; i < arr.length; i++) {
            currMax = Math.max(arr[i], currMax + arr[i]);;
            if (currMax == arr[i]) {
                start = i;
            }
            maxSum = Math.max(maxSum, currMax);
            if (maxSum == currMax) {
                end = i;
            }
        }

        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}