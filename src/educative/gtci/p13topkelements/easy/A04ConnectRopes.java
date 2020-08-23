package educative.gtci.p13topkelements.easy;

import java.util.PriorityQueue;

public class A04ConnectRopes {
    public static void main(String[] args) {
        connectRopes();
    }

    // Cost of connecting two ropes is equal to the sum of their lengths
    private static void connectRopes() {
        int[] arr = new int[]{3, 4, 5, 6};

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        int i = 0;
        while (i < arr.length) {
            pq.add(arr[i]);
            i++;
        }

        int res = 0;
        // GREEDY APPROACH: SELECT THE 2 SHORTEST ROPES IN THE HEAP TO CONNECT TOGETHER.
        // PUSH THEIR SUM TO HEAP AS WELL
        // IN CAE OF EVEN ELEMENTS: THERE WILL REMAIN ONE USELESS ELEMENT IN THE HEAP
        // IN CASE OF ODD ELEMENTS: THERE WILL REMAIN NO USELESS ELEMENT IN THE HEAP
        // SO USE SIZE > 1
        while (pq.size() > 1) {
            int sum = 0;
            int x = pq.poll();
            int y = pq.poll();
            sum += x + y;
            res += sum;
            pq.add(sum);
        }
        System.out.println(res);
    }
}
