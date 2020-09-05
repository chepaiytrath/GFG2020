package educative.gtci.p13topkelements.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class A05TopKFrequentNumbers {
    public static void main(String[] args) {
        topKFreq();
    }

    private static void topKFreq() {
//        int[] arr = new int[]{1, 3, 5, 12, 11, 12, 11};
        int[] arr = new int[]{8, 1, 1, 2, 2, 3, 3, 3, 4};
        int k = 2;
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
//        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> (!a.getValue().equals(b.getValue())) ? (a.getValue().compareTo(b.getValue())) : (b.getKey().compareTo(a.getKey())));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        System.out.println(pq);
    }
}
