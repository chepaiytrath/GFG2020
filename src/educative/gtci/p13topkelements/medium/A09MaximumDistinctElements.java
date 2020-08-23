package educative.gtci.p13topkelements.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class A09MaximumDistinctElements {
    public static void main(String[] args) {
        findMax();
    }

    // Remove k elements to get maximum number of distinct characters that occur only once
    private static void findMax() {
        /*int[] arr = new int[]{7, 3, 5, 8, 5, 3, 3};
        int k = 2;*/
        /*int[] arr = new int[]{3, 5, 12, 11, 12};
        int k = 3;*/
        int[] arr = new int[]{1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5};
        int k = 2;

        //Populate a frequency map
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        int singleCount = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                singleCount++;
            } else {
                //Priority que to store entries of numbers which occurred more than once
                pq.add(entry);
            }
        }

        // Greedy approach: Select the smallest non distinct frequency first
        while (k > 0 && !pq.isEmpty()) {
            // Imagine deleting all characters except one : To get another singleCount
            k -= pq.poll().getValue() - 1;

            //In case k < entry val: either all elements should be deleted or some should be left
            if (k >= 0) {
                singleCount++;
            }
        }

        // In case there are still some elements left from target count = k to be deleted in the main string
        if (k > 0) {
            singleCount = singleCount - k;
        }
        System.out.println(singleCount);
    }
}