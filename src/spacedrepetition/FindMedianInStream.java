package spacedrepetition;

import java.util.PriorityQueue;

public class FindMedianInStream {
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    public static void main(String[] args) {
        add(5);
        System.out.println(median());

        add(15);
        System.out.println(median());

        add(1);
        System.out.println(median());

        add(3);
        System.out.println(median());
    }

    private static void add(int val) {
        if (maxHeap.isEmpty() || val < maxHeap.peek()) {
            maxHeap.add(val);
        } else if (minHeap.isEmpty() || val > minHeap.peek()) {
            minHeap.add(val);
        } else {
            maxHeap.add(val);
        }
        balance();
    }

    private static void balance() {
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    private static double median() {
        if (maxHeap.size() == minHeap.size() + 1) {
            return maxHeap.peek();
        } else {
            // (maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}