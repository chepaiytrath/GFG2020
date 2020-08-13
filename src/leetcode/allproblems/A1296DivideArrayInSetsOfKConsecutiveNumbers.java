package leetcode.allproblems;

import java.util.Map;
import java.util.TreeMap;

// SAME AS A0846HandOfStraights
class A1296DivideArrayInSetsOfKConsecutiveNumbers {

    public static boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int key : map.keySet()) {
            int keyValue = map.get(key);
            if (map.get(key) > 0) {
                for (int i = 1; i < k; i++) {
                    int nextKey = key + i;
                    int nextKeyValue = map.getOrDefault(nextKey, 0);
                    if (nextKeyValue < keyValue) {
                        return false;
                    } else {
                        map.put(nextKey, nextKeyValue - keyValue);
                    }
                }
            }
        }
        return true;
    }
}