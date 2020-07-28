package company.walmart2;

import java.util.PriorityQueue;

public class KthLargestElementInStream {
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        add(4, 3);
        add(5, 3);
        add(8, 3);
        add(2, 3);
        System.out.println(pq.peek());
    }

    private static void add(int val, int k){
        pq.offer(val);
        if(pq.size() > k){
            pq.poll();
        }
    }
}