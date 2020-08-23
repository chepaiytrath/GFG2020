package company.google.oa;

import java.util.*;

class NetworkDelay {
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
        for(int i = 1; i < N; i++){
            for(int[] edge : times){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                
                if(dist[u] != Integer.MAX_VALUE){
                    dist[v] = Math.min(dist[v], dist[u] + w);
                }
            }
        }
        
        int max = 0;
        for(int i = 1; i <=N; i++){
            max = Math.max(max, dist[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    public int networkDelayTimeDijkstrasAlgo(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        populateAdjList(times, map, N);

        //max of dist
        int src = K - 1;
        int[] dist = new int[N];
        Set<Integer> visited = new HashSet<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> a.dis - b.dis);
        Pair source = new Pair(src, 0);
        pq.add(source);

        while(!pq.isEmpty()){
            Pair popped = pq.poll();
            visited.add(popped.index);

            if(dist[popped.index] < popped.dis){
                continue;
            }

            for(Map.Entry<Integer, Integer> edge : map.get(popped.index).entrySet()){
                if(!visited.contains(edge.getKey())){
                    int to = edge.getKey();
                    int wt = edge.getValue();

                    Pair newPair = new Pair(to, dist[popped.index] + wt);
                    pq.add(newPair);
                    dist[to] = Math.min(dist[to], newPair.dis);
                }
            }
        }

        int max = -1;
        for(int num : dist){
            max = Math.max(max, num);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

    private void populateAdjList(int[][] times, Map<Integer, Map<Integer, Integer>> map, int N){
        for(int i = 0; i < N; i++){
            map.put(i, new HashMap<Integer, Integer>());
        }

        for(int[] time : times){
            map.get(time[0] - 1).put(time[1] - 1, time[2]);
        }
    }

    static class Pair{
        int index;
        int dis;
        Pair(int index, int dis){
            this.index = index;
            this.dis = dis;
        }
    }
}