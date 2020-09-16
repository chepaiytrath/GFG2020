package company.groupon.hackerrank;

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
        Map<Integer, Integer> count = new HashMap<>(), first = new HashMap<>();
        int res = 0, degree = 0;
        for (int i = 0; i < A.length; ++i) {
            first.putIfAbsent(A[i], i);
            count.put(A[i], count.getOrDefault(A[i], 0) + 1);
            if (count.get(A[i]) > degree) {
                degree = count.get(A[i]);
                res = i - first.get(A[i]) + 1;
            } else if (count.get(A[i]) == degree)
                res = Math.min(res, i - first.get(A[i]) + 1);
        }
        return res;
    }

    public int findShortestSubArrayFromComments(int[] a) {
        Map<Integer, Integer> freq = new HashMap<>(), firstMet = new HashMap<>();
        int minLen = 0;
        for (int i = 0, maxFreq = 0; i < a.length; i++) {
            int f = freq.getOrDefault(a[i], 0) + 1;
            freq.put(a[i], f);
            if (!firstMet.containsKey(a[i]))
                firstMet.put(a[i], i);
            if (f > maxFreq) {
                minLen = i - firstMet.get(a[i]) + 1;
                maxFreq = f;
            } else if (f == maxFreq)
                minLen = Math.min(minLen, i - firstMet.get(a[i]) + 1);
        }
        return minLen;
    }
}
