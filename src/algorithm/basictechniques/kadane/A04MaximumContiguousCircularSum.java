package algorithm.basictechniques.kadane;

//https://www.geeksforgeeks.org/maximum-contiguous-circular-sum/
public class A04MaximumContiguousCircularSum {
    public static void main(String[] args) {
        //int[] arr = new int[]{8, -8, 9, -9, 10, -11, 12};     //22
        //int[] arr = new int[]{10, -3, -4, 7, 6, 5, -4, -1};   //23
        int[] arr = new int[]{-1, 40, -14, 7, 6, 5, -4, -1};    //52

        System.out.println(maximumContiguousCircularSum(arr));
    }


    // RULE
    // Find maximum of two values
    // 1. Normal maximum subarray sum
    // 2. Total sum - Minimum subarray sum
    // Answer is Max(1,2)
    private static int maximumContiguousCircularSum(int[] arr) {
        int sum = 0;
        int maxSubarraySum = kadane(arr);


        //Find minimum subarray sum by reversing the signs and running normal kadane
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; //Parallely find um of array as well, saving another loop
            arr[i] = -arr[i];
        }

        int minSubarraySum = -kadane(arr);

        int res = Math.max(maxSubarraySum, sum - minSubarraySum);
        return res;
    }

    private static int kadane(int[] arr) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int val : arr) {
            currSum = Math.max(val, currSum + val);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}
