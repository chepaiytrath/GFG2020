package leetcode.allproblems;

import java.util.HashMap;
import java.util.Map;

public class A00001TwoSum {
    // Assume one such pair exists
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = twoSum(nums, target);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (!indexMap.containsKey(complement) && indexMap.get(complement) != i) {
                indexMap.put(nums[i], i);
            } else {
                return new int[]{indexMap.get(complement), i};
            }
        }
        return null;
    }
}
