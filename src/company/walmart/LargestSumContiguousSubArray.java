//Kadane's Algorithm
package company.walmart;

public class LargestSumContiguousSubArray {
    public static void main(String[] args) {
        //int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        //int arr[] = {-1, -2, -3, -4, -5};
        int arr[] = {Integer.MIN_VALUE, -4, -3, -2, -1};
        int sum = findLargestSumContiguousSubArray(arr);
        System.out.println(sum);
    }

    private static int findLargestSumContiguousSubArray(int[] arr) {
        int meh = 0, msf = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            meh += arr[i];
            msf = Math.max(meh, msf);
            if (meh < 0) {
                meh = 0;
            }
        }
        return msf;
    }
}