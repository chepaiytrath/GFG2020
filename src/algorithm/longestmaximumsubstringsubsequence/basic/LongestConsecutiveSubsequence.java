package algorithm.longestmaximumsubstringsubsequence.basic;

import java.util.HashSet;
import java.util.Set;

// #REVISIT
public class LongestConsecutiveSubsequence {
    public static void main(String[] args) {
//        int[] arr = new int[]{1, 9, 3, 10, 4, 20, 2};
        int[] arr = new int[]{36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
        System.out.println(findLongestConsecutiveSubsequenceUsingHashing(arr));
    }

    private static int findLongestConsecutiveSubsequenceUsingHashing(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int longestPath = 0;
        for (int num : arr) {
            if (!set.contains(num - 1)) {
                int curr = num;
                while (set.contains(curr)) {
                    curr++;
                }
                int currConsecutiveSubsequenceLength = curr - num;
                longestPath = Math.max(longestPath, currConsecutiveSubsequenceLength);
            }
        }

        return longestPath;
    }
}
