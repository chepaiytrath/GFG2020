package leetcode.allproblems;

import java.util.HashMap;
import java.util.Map;

public class A0525ContiguousSubarray0s1s {
    public static void main(String[] args) {
        int[] arr = new int[]{0,0,1,0,0,0,1,1};
        System.out.println(findMaxLength(arr));
    }

    public static int findMaxLength(int[] arr) {
        // CONSIDER 0 = -1 AND 1 = 1. IN A SUBARRAY WITH EQUAL NUMBERED 0s 1s: TOTAL SUM WILL BE 0.
        int cumsum = 0;
        int maxlen = Integer.MIN_VALUE;

        Map<Integer, Integer> map = new HashMap<>();

        // THUMB RULE:
        // IF CUMSUM OF TWO INDICES IS SAME, THAT MEANS NO CHANGE ENCOUNTERED SINCE LAST INDEX
        // I.E. EQUAL NUMBER OF 0s AND 1s
        for (int i = 0; i < arr.length; i++) {
            cumsum += arr[i] == 0 ? -1 : 1;
            if (cumsum == 0) {
                // CURRENT CUMSUM = 0 : THIS IS POSSIBLE ONLY FROM BEGINNING OF ARRAY, 0 TO CURRENT INDEX
                maxlen = Math.max(maxlen, i + 1);
                continue;
            }
            if (!map.containsKey(cumsum)) {
                map.put(cumsum, i);
            } else {
                // CURRENT CUMSUM HAS ALREADY BEEN ENCOUNTERED AND SAVED IN MAP
                maxlen = Math.max(maxlen, i - map.get(cumsum));
            }
        }

        return maxlen == Integer.MIN_VALUE ? 0 : maxlen;
    }
}