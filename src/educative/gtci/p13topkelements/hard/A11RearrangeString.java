package educative.gtci.p13topkelements.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class A11RearrangeString {
    public static void main(String[] args) {
        rearrangeString();
    }

    private static void rearrangeString() {
        String str = "Programming";

        Map<Character, Integer> freqMap = new HashMap<>();
        for (Character ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // GREEDY TECHNIQUE: SELECT MOST FREQUENT CHARACTER WHOSE FREQUENCY IS X AND PUSH ONE OF ITS INSTANCES TO RESULT STRING.
        // DECREMENT ITS FREQUENCY = X - 1 AND PUSH TO PQ SUCH THAT IT IS ADDED AFTER THE NEXT ONE IS PROCESSED. USE PREVENTRY FOR THAT.
        // MAX HEAP: BIGGEST FREQUENCY CHARACTER AT THE TOP
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(freqMap.entrySet());

        StringBuilder sb = new StringBuilder();
        // WHY PREVENTRY IS USED: TO OFFER THE POLLED ENTRY WITH DECREMENTED COUNT AFTER ONE ITERATION
        // THIS PREVENTS REPETITION
        // YOU SHOULDN'T JUST ADD THE ENTRY STRAIGHTAWAY AFTER YOU DECREMENT ITS COUNT
        // WHY? BECAUSE THE COUNT MAY AGAIN BE HIGHEST AND AT TOP OF PRIORITY QUEUE
        // SO WILL BE PICKED UP AGAIN IN THE GREEDY APPOACH
        Map.Entry<Character, Integer> prevEntry = null;
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> currEntry = pq.poll();
            if (prevEntry != null && prevEntry.getValue() > 0) {
                pq.add(prevEntry);
            }
            char ch = currEntry.getKey();
            sb.append(ch);
            currEntry.setValue(currEntry.getValue() - 1);
            //GET PREVENTRY READY FOR NEXT ITERATION
            prevEntry = currEntry;
        }
        System.out.println(sb.toString());
    }
}
