package company.groupon.hackerrank.rakesh;

import java.util.HashMap;
import java.util.Map;

public class LengthOfShortestSubarrayWithDegreeSameAsArray {

//    Time Complexity:
//    Time O(N), hardly find the any reason to scan twice.
//    Space O(M), where M is the size of different numbers.
//    Explanation:
//
//    One pass on A,
//    For each different number a in A,
//    we need to count its frequency and it first occurrence index.
//
//    If a has the maximum frequency,
//    update the degree = count[a] and res = i - first[A[i]] + 1.
//
//    If a is one of the numbers that has the maximum frequency,
//    update the res = min(res, i - first[A[i]] + 1)
    public int findShortestSubArray(int[] A) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> firstMet = new HashMap<>();
        int res = 0, degree = 0;
        for (int i = 0; i < A.length; ++i) {
            firstMet.putIfAbsent(A[i], i);
            freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);
            if (freq.get(A[i]) > degree) {
                degree = freq.get(A[i]);
                res = i - firstMet.get(A[i]) + 1;
            } else if (freq.get(A[i]) == degree)
                res = Math.min(res, i - firstMet.get(A[i]) + 1);
        }
        return res;
    }

    public int findShortestSubArrayFromComments(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> firstMet = new HashMap<>();
        int minLen = 0;
        int maxFreq = 0;
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            int frequency = freq.getOrDefault(curr, 0) + 1;
            freq.put(arr[i], frequency);
            if (!firstMet.containsKey(curr)) {
                firstMet.put(curr, i); // Save first met index
            }
            if (frequency > maxFreq) {
                minLen = i - firstMet.get(curr) + 1;
                maxFreq = frequency;
            } else if (frequency == maxFreq) {
                minLen = Math.min(minLen, i - firstMet.get(curr) + 1);
            }
        }
        return minLen;
    }
}
