package company.amazon.oa.amcat;

import java.util.PriorityQueue;

public class MinimumCostToConnectRopes {
    public static void main(String[] args) {
//        int[] ropes = new int[]{8, 4, 6, 12};
//        int[] ropes = new int[]{20, 4, 8, 2};
        int[] ropes = new int[]{1, 2, 5, 10, 35, 89};
//        int[] ropes = new int[]{2, 2, 3, 3};
        System.out.println(minCost(ropes));
    }

    private static int minCost(int[] ropes) {
        if (ropes.length < 3) {
            int sum = 0;
            for (int val : ropes) {
                sum += val;
            }
            return sum;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int rope : ropes) {
            pq.add(rope);
        }

        int sum = 0;
        while (pq.size() > 1) {
            int temp = pq.poll() + pq.poll();
            sum += temp;
            pq.add(temp);
        }

        return sum;
    }
}
