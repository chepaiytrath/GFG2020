package algorithm.longestmaximumsubstringsubsequence.basic;

import java.util.HashSet;

// #REVISIT
public class LongestConsecutiveSubsequence {
    public static void main(String[] args) {
//        int[] arr = new int[]{1, 9, 3, 10, 4, 20, 2};
        int[] arr = new int[]{36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
        System.out.println(findLongestConsecutiveSubsequenceUsingHashing(arr));
    }

    private static int findLongestConsecutiveSubsequenceUsingHashing(int[] arr) {
        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }

        int longestPath = -1;
        for (int i = 0; i < n; i++) {
            if (!set.contains(arr[i] - 1)) {
                int j = arr[i];
                while (set.contains(j)) {
                    j++;
                }
                int diff = j - arr[i];
                longestPath = Math.max(longestPath, diff);
            }
        }

        return longestPath;
    }
}
