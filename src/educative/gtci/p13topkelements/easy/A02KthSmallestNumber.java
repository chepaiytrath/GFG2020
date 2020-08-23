package educative.gtci.p13topkelements.easy;

import java.util.PriorityQueue;

public class A02KthSmallestNumber {
    public static void main(String[] args) {
        kthSmallest();
    }

    private static void kthSmallest() {
        /*int[] arr = new int[]{1, 5, 12, 2, 11, 5};
        int k = 3;*/
        /*int[] arr = new int[]{1, 5, 12, 2, 11, 5};
        int k = 4;*/
        int[] arr = new int[]{5, 12, 11, -1, 12};
        int k = 3;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int i = 0;
        while (i < k) {
            pq.add(arr[i]);
            i++;
        }
        while (i < arr.length) {
            int num = arr[i];
            if (num < pq.peek()) {
                pq.add(num);
                pq.poll();
            }
            i++;
        }

        System.out.println(pq.peek());
    }
}
