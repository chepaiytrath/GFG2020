package company.amazon.oa.amcat;

import java.util.PriorityQueue;

class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] points = new int[][]{{3, 3}, {5, -1}, {-2, 4}};
        int K = 2;
        int[][] res = kClosest(points, K);
        for (int[] arr : res) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (((a[0] * a[0]) + (a[1] * a[1])) - ((b[0] * b[0]) + (b[1] * b[1]))));
        for (int[] point : points) {
            pq.add(point);
        }

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    public static int[][] kClosest2(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> b.getDistance() - a.getDistance());
        int i = 0;
        while (i < k) {
            Point p = new Point(points[i][0], points[i][1]);
            pq.add(p);
            i++;
        }

        while (i < points.length) {
            Point p = new Point(points[i][0], points[i][1]);
            if (p.getDistance() < pq.peek().getDistance()) {
                pq.add(p);
                pq.poll();
            }
            i++;
        }

        int[][] res = new int[pq.size()][2];
        for (int idx = 0; idx < res.length; idx++) {
            Point p = pq.poll();
            res[idx][0] = p.i;
            res[idx][1] = p.j;
        }

        return res;
    }

    static class Point {
        int i;
        int j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getDistance() {
            return i * i + j * j;
        }
    }
}