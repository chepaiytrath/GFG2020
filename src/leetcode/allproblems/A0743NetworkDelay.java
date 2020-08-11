package leetcode.allproblems;

import java.util.Arrays;

class A0743NetworkDelay {
    // LOGIC: FIND THE MAXIMUM VALUE FROM ALL THE SHORTEST PATHS FROM SOURCE TO ALL OTHER NODES
    // BELLMAN FORD GIVES US THE SHORTEST PATHS FROM ONE NODE TO ALL OTHER NODES (SINGLE SOURCE SHORTEST PATH ALGO) IN AN ARRAY
    // JUST FIND THE MAX OF THE ARRAY VALUES
    // BE CAREFUL OF DIST[0] AS IT WILL CONTAIN MAX VAL

    public static void main(String[] args) {
        int[][] times = new int[][]{
                {2, 1, 1}, {2, 3, 1}, {3, 4, 1}
        };
        int time = networkDelayTime(times, 4, 2);
        System.out.println(time);
    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE) {
                    dist[v] = Math.min(dist[v], dist[u] + w);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}