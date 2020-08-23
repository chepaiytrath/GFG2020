package educative.gtci.p13topkelements.easy;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class A03KClosestPointsToTheOrigin {
    public static void main(String[] args) {
        kClosestPointsToTheOrigin();
    }

    private static void kClosestPointsToTheOrigin() {
        int[][] arr = new int[][]{
                {1, 3},
                {3, 4},
                {2, -1}
        };
        int k = 2;

        // CLOSEST TO ORIGIN : MEANS SMALLEST DISTANCE FROM ORIGIN : WHICH IS POINT.DISTANCE
        // WHY MAX HEAP : BECAUSE THUMB RULE : FOR K SMALLEST(CLOSEST), USE MAX HEAP : FIRST SMALLEST IS AT THE BOTTOM
        // LAST K ELEMENTS IN MAX HEAP WILL BE THE K SMALLEST ELEMENTS (SMALLEST DISTANCE IN THIS CASE)
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> b.getDistance() - a.getDistance());
        int n = arr.length;
        int i = 0;
        while (i < k) {
            Point p = new Point(arr[i][0], arr[i][1]);
            pq.add(p);
            i++;
        }

        //CONTINUE WHERE YOU LEFT OFF IN PREVIOUS WHILE FROM I = K
        while (i < n) {
            Point p = new Point(arr[i][0], arr[i][1]);
            if (p.getDistance() < pq.peek().getDistance()) {
                pq.add(p);
                pq.poll();
            }
            i++;
        }

        System.out.println(new ArrayList<>(pq));
    }

    static class Point {
        int x;
        int y;
        int distance;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance() {
            return x * x + y * y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
