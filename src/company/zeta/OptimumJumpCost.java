package company.zeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://codeforces.com/blog/entry/81142
public class OptimumJumpCost {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 100};
        System.out.println(findOptimumCost(arr));
    }

    //DP : NOT SURE ABOUT BASE CASES
    //WORKS FOR {1,2,3,4,100}
    private static int findOptimumCost(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = arr[0];
        dp[2] = arr[0];
        for (int i = 1; i < n - 1; i++) {
            dp[i] = Math.min(dp[i], dp[i + 1] + arr[i + 1]);
            dp[i + 2] = dp[i] + arr[i];
        }
        return dp[n];
    }

    // Graph - Dijkstra
    private static int findOptimumCostWithDijkstraAlgo(int[] arr) {
        Map<Integer, Map<Integer, Integer>> map = populateWeightedMap(arr);
        int n = arr.length;

        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> (a.weight - b.weight));
        int src = 0;
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        pq.add(new Element(src, 0));

        boolean[] visited = new boolean[n + 1];
        while (!pq.isEmpty()) {
            Element popped = pq.poll();
            int u = popped.index;
            if (popped.weight > dis[u]) {
                continue;
            }
            dis[u] = Math.min(dis[u], popped.weight);
            visited[u] = true;
            if (map.containsKey(popped.index)) {
                for (Map.Entry<Integer, Integer> edge : map.get(popped.index).entrySet()) {
                    int v = edge.getKey();
                    int w = edge.getValue();
                    if (!visited[v]) {
                        pq.add(new Element(v, dis[u] + w));
                    }
                }
            }
        }
        return dis[n];
    }

    static class Element {
        int index;
        int weight;

        Element(int to, int weight) {
            this.index = to;
            this.weight = weight;
        }
    }

    private static Map<Integer, Map<Integer, Integer>> populateWeightedMap(int[] arr) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (!map.containsKey(i)) {
                map.put(i, new HashMap<>());
            }

            if (i < n - 1) {
                map.get(i).put(i + 2, arr[i]);
            }
            if (i > 0) {
                map.get(i).put(i - 1, arr[i]);
            }
        }
        return map;
    }
}
