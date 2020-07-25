package company.mustdo;

import java.util.HashMap;
import java.util.Map;

public class Arrays {
    public void findSubarrayWithGivenSumOnlyPositiveIntegers(int[] arr, int target) {
        // SOLUTION: https://www.geeksforgeeks.org/find-subarray-with-given-sum/
        // WINDOW SLIDING APPROACH : O(n)
        // ONLY HANDLES POSITIVE INTEGERS

        // SAMPLE INPUT
        // int[] arr = { 1, 4, 20, 3, 10, 5 };

        int start = 0;
        int end = -1;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            while (sum > target) {
                sum -= arr[start];
                start++;
            }
            if (sum == target) {
                end = i - 1;
                break;
            }
            sum += arr[i];
        }
        if (end != -1) {
            System.out.println("Subarray found between index " + start + " and " + end);
            for (int i = start; i <= end; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            System.out.print("No subarray found");
        }
    }

    public void findSubarrayWithGivenSumAllIntegers(int[] arr, int target) {
        // SOLUTION:
        // https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
        // WINDOW SLIDING APPROACH : O(n)
        // HANDLES ALL INTEGERS

        // SAMPLE INPUT
        // int[] arr = { 10, 2, -2, -20, 10 };

        int start = -1;
        int end = -1;
        int sum = 0;
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum - target == 0) {
                start = 0;
                end = i;
                break;
            }
            if (sumIndexMap.containsKey(sum - target)) {
                start = sumIndexMap.get(sum - target) + 1;
                end = i;
                break;
            }
            sumIndexMap.put(sum, i);
        }
        if (end != -1) {
            System.out.println("Subarray found between index " + start + " and " + end);
            for (int i = start; i <= end; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            System.out.print("No subarray found");
        }
    }

    public void countTriplets(int[] arr) {
        // SOLUTION:
        // https://www.geeksforgeeks.org/count-triplets-such-that-one-of-the-numbers-can-be-written-as-sum-of-the-other-two/

        // https://www.youtube.com/watch?v=ZD7WxJ3O2XE
        // Uses Combinations from Math
        // HINT Use Combinatorics : nCr = n! / ((n-r)! * r!)

        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] freq = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
        }

        int ans = 0;
        ans += freq[0] * (freq[0] - 1) * (freq[0] - 2) / 6;

        for (int i = 1; i <= max; i++) {
            ans += freq[i] * (freq[i - 1]) * freq[0] / 2;
        }

        for (int i = 0; 2 * i <= max; i++) {
            ans += freq[i] * (freq[i] - 1) * (freq[2 * 1]) / 2;
        }

        for (int i = 1; i <= max; i++) {
            for (int j = i + 1; i + j <= max; j++) {
                ans += freq[i] * freq[j] * freq[i + j];
            }
        }
        System.out.println("Number of triplets : " + ans);
    }

    public void kadanesAlgorithm(int[] arr) {
        // SAMPLE INPUT
        // int[] arr = { -5, -4, -3, -2, -1 };
        // int[] arr1 = { -2, -3, 4, -1, -2, 1, 5, -3 };

        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            currSum = Math.max(arr[i], arr[i] + currSum);
            maxSum = Math.max(maxSum, currSum);
        }
        System.out.println("Largest Sum Contiguous Subarray = " + maxSum);
    }

    public void findTheMissingNumber(int[] arr) {
        // TRICKY SOLUTION TO HANDLE OVERFLOW OF INTEGER
        // PROVIDED NUMBER ARE FROM 1 TO N
        int ans = 1;
        for (int i = 2; i < arr.length; i++) {
            ans += i;
            ans -= arr[i - 2];
        }
        System.out.println("Missing Number is : " + ans);
    }

    public void mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        // MOVE INDEX OF ARRAY WITH SMALLER ELEMENT
        // IF BOTH EQUAL THEN MOVE BOTH INDEXES

        //SAMPLE INPUT
        // int[] arr1 = { 1, 3, 4, 5 };
        // int[] arr2 = { 2, 4, 6, 8 };

        int i = 0;
        int j = 0;

        int ind = 0;
        Integer[] res = new Integer[arr1.length + arr2.length];
        while (i < arr1.length && j < arr2.length) {
            if(arr1[i] < arr2[j]){
                res[ind++] = arr1[i++];
            }else if(arr2[j] < arr1[i]){
                res[ind++] = arr2[j++];
            }else if(arr1[i] == arr2[j]){
                res[ind++] = arr1[i++];
                res[ind++] = arr2[j++];
            }
        }
        while (i < arr1.length) 
            res[ind++] = arr1[i++]; 
      
        while (j < arr2.length) 
            res[ind++] = arr2[j++]; 

        System.out.println(java.util.Arrays.asList(res));    

        //ANOTHER APPROACH: USE MAP TO SAVE ALL KEYS
    }

    public void rearrangeArrayAlternatelyInMaximumMinimumForm(){
        //TO DO
        //https://www.geeksforgeeks.org/rearrange-array-maximum-minimum-form-set-2-o1-extra-space/

    }

    

}