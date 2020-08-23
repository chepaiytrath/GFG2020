package educative.gtci.p13topkelements.medium;

import java.util.PriorityQueue;

public class A07KthLargestNumberInAStream {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 5, 12, 2, 11};
        int k = 4;
        A07KthLargestNumberInAStream obj = new A07KthLargestNumberInAStream(arr, k);
        System.out.println(obj.add(6));
        System.out.println(obj.add(13));
        System.out.println(obj.add(4));
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
    int k;

    A07KthLargestNumberInAStream(int[] arr, int k) {
        this.k = k;
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    private int add(int num) {
        pq.add(num);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
