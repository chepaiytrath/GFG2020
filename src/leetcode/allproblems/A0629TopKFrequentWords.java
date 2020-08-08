package leetcode.allproblems;

import java.util.*;

public class A0629TopKFrequentWords {
    public static void main(String[] args) {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent(words, 2));
    }

    // TO FIND K LARGEST ELEMENTS, PUT THEM IN AN INCREASING PRIORITY QUEUE : SO THAT THE K LARGEST ELEMENTS ARE ALWAYS AT THE BOTTOM OF THE QUEUE
    // AFTER PQ SIZE CROSSES THRESHOLD, ANY ELEMENT ADDED TO PQ IS OF NO USE SO POLL IT: THIS POLLED ELEMENT WILL ALWAYS BE THE SMALLEST ELEMENT IN THE STACK AT THAT TIME
    // AT THE END GET THE K ELEMENTS FROM QUEUE INTO A LIST
    public static List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return null;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        //COMPARATOR TO MAKE SURE ELEMENTS GET INSERTED IN AN INCREASING ORDER AND NOT DECREASING
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> {
            if (a.getValue() > b.getValue()){
                return 1;
            }else if(a.getValue() < b.getValue()){
                return -1;
            }
            //IN CASE TWO ELEMENTS ARE EQUAL, KEEP THEM IN LEXICOGRAPHICALLY DECREASING ORDER SO THAT IT CAN BE POLLED WHEN SIZE CROSSES THRESHOLD
            else return b.getKey().compareTo(a.getKey());
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }
}
