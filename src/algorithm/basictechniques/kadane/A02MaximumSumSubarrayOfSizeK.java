package algorithm.basictechniques.kadane;

public class A02MaximumSumSubarrayOfSizeK {
    public static void main(String[] args) {
        maxSum();
    }

    //Positive Integers Array and Positive K
    private static void maxSum() {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        int n = arr.length;

        int start = 0;
        int end = 0;

        int windowSum = 0;
        int maxSum = 0;
        while (start <= n - k) {
            windowSum += arr[end];
            if (end >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[start];
                start++;
            }
            end++;
        }
        System.out.println(maxSum);
    }
}
