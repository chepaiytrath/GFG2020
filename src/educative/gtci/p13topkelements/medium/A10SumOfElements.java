package educative.gtci.p13topkelements.medium;

import java.util.Collections;
import java.util.PriorityQueue;

public class A10SumOfElements {
    public static void main(String[] args) {
        sumOfElements();
    }

    private static void sumOfElements() {
        int k1 = 3;
        int k2 = 6;
        int[] arr = new int[]{1, 3, 12, 5, 15, 11};
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int num : arr) {
            pq.add(num);
        }

        for (int i = 0; i < k1; i++) {
            pq.poll();
        }

        int sum = 0;
        for (int j = 0; j < k2 - k1 - 1; j++) {
            sum += pq.poll();
        }
        System.out.println(sum);
    }
}
