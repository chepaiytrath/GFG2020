//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
package algorithm.basictechniques.kadane;

public class A01MaximumSumSubarray {
    public static void main(String[] args) {
        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        //int arr[] = {-1, -2, -3, -4, -5, -6};
        //int arr[] = {-6, -5, -4, -3, -2, -1};
        //int arr[] = {-5, -4, -1, -3, -2, -1};
        int result = maximumSumSubarray(arr);
        System.out.println("Max Sum = " + result);
    }

    private static int maximumSumSubarray(int[] arr) {
        int max_so_far = Integer.MIN_VALUE;
        int max_ending_here = 0;

        for (int i = 0; i < arr.length; i++) {
            max_ending_here = (max_ending_here + arr[i] > 0) ? max_ending_here + arr[i] : 0;
            max_so_far = Math.max(max_ending_here, max_so_far);
        }

        return max_so_far;
    }

    //Handles all negative array too
    private static int maximumSumSubarray2(int[] arr) {
        int max_so_far = arr[0];
        int max_ending_here = arr[0];

        for (int i = 1; i < arr.length; i++) {
            max_ending_here = Math.max(arr[i], max_ending_here + arr[i]);
            max_so_far = Math.max(max_ending_here, max_so_far);
        }

        return max_so_far;
    }

    //Prints the indices of the subarray
    private static int maximumSumSubarray3(int[] arr) {
        int max_so_far = arr[0];
        int max_ending_here = arr[0];
        int start = 0, end = 0;

        for (int i = 1; i < arr.length; i++) {
            boolean flag;
            if (max_ending_here + arr[i] > arr[i]) flag = true;
            else flag = false;

            max_ending_here = Math.max(max_ending_here + arr[i], arr[i]);

            if (max_ending_here > max_so_far) {
                max_so_far = max_ending_here;
                if (!flag) {
                    start = end = i;
                } else {
                    end = i;
                }
            }
        }
        System.out.println(start + "," + end);

        return max_so_far;
    }
}
