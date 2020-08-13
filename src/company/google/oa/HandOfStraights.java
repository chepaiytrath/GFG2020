package company.google.oa;

import java.util.Map;
import java.util.TreeMap;

class HandOfStraights {
    public static void main(String[] args) {
        int[] hand = new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8};
        int W = 3;
        System.out.println(isNStraightHand(hand, W));
    }

    // LOGIC : Take all element of array into TreeMap with their count
    // RULE: Total count of first digit in a sorted group is less than equal to the counts of the digits ahead of it
    // Ex. 1, 2, 3, 6, 2, 3, 4, 7, 8   =>    1,2,3 + 2,3,4 + 6,7,8 when W = 3
    // Count of 1 = 1
    // Count of 2 = 2
    // Count of 3 = 2
    // 1 can form only one group of elements when W = 3.
    // If 1 count was higher than 2 or 3 count that would have meant that even after forming 1 group of W elements,
    // there still remains a 1 which would not be possible to be joined with any other digit after 3 because it would not be consecutive
    // So count of 1 should be less than equal to subsequent digit count
    // O(nlogn + nW) : n = size of hand, W = group size
    public static boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int currKey : map.keySet()) {
            int currKeyCount = map.get(currKey);
            if (map.get(currKey) > 0) {
                for (int i = 1; i < W; i++) {
                    int nextKey = currKey + i;
                    // Edge case where last element is checked for consecutive digits which will not be in map.
                    // Default value of 0 is good because if 2 is > 1, then last digit would already have been grouped up.
                    int nextKeyCount = map.getOrDefault(nextKey, 0);
                    if (nextKeyCount < currKeyCount) {
                        return false;
                    } else {
                        map.put(nextKey, nextKeyCount - currKeyCount);
                    }
                }
            }
        }
        return true;
    }
}