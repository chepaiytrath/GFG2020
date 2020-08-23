package educative.gtci.p13topkelements.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class A06FrequencySort {
    public static void main(String[] args) {
        freqSort();
    }

    private static void freqSort() {
//        String str = "Programming";
        String str = "abcbab";
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());

        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            for(int i = 0; i < entry.getValue(); i++){
                sb.append(entry.getKey());
            }
        }

        System.out.println(sb.toString());
    }
}
