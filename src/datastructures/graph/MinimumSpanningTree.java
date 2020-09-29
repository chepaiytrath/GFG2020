package datastructures.graph;

import java.util.*;

public class MinimumSpanningTree {
    public static void main(String[] args) {
        primsAlgorithmLazyApproach();

        System.out.println();
        System.out.println();

        kruskalsAlgorithmLazyApproach();
    }

    // SOLUTION FROM WILLIAM FISET : https://www.youtube.com/watch?v=jsmMtJpPnhU&t=590s
    // O(E*log V)
    // Two thumb rules
    // 1. Don't add edges to PQ whose "to" is already visited
    // 2. If a popped edge from PQ is already visited, continue popping
    private static void primsAlgorithmLazyApproach() {
        // Eager approach uses Indexed PriorityQueue which is Out of scope
        int V = 8;
        Map<Integer, Map<Integer, Integer>> map = populateAdjList(V);


        Set<Integer> visited = new HashSet<>();
        Set<Edge> mstEdges = new HashSet<>();

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> (a.wt - b.wt));

        int src = 5; // Can be anything from 0 - V
        for (Map.Entry<Integer, Integer> edge : map.get(src).entrySet()) {
            int from = src;
            int to = edge.getKey();
            int wt = edge.getValue();
            pq.add(new Edge(from, to, wt));
        }
        visited.add(src);

        int mstEdgeTarget = V - 1;
        while (!pq.isEmpty() || mstEdges.size() != mstEdgeTarget) {
            Edge popped = pq.poll();

            // 2. If a popped edge from PQ is already visited, continue popping
            if (visited.contains(popped.to)) {
                continue;
            }

            // Each popped edge is part of MST
            mstEdges.add(popped);

            // Mark src as visited because going to add all its valid edges to PQ
            src = popped.to;
            visited.add(src);
            for (Map.Entry<Integer, Integer> edge : map.get(src).entrySet()) {
                int from = src;
                int to = edge.getKey();
                int wt = edge.getValue();
                // 1. Don't add edges to PQ whose "to" is already visited
                if (!visited.contains(to)) {
                    pq.add(new Edge(from, to, wt));
                }
            }
        }

        // Add weights of all edges to get min cost
        int minCost = 0;
        for (Edge e : mstEdges) {
            minCost += e.wt;
        }

        System.out.println(mstEdges);
        System.out.println(minCost);
    }

    static class Edge {
        int from;
        int to;
        int wt;

        Edge(int from, int to, int wt) {
            this.from = from;
            this.to = to;
            this.wt = wt;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", wt=" + wt +
                    '}'+"\n";
        }
    }

    // Example graph from: https://www.youtube.com/watch?v=jsmMtJpPnhU&t=219s
    private static Map<Integer, Map<Integer, Integer>> populateAdjList(int V) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < V; i++) {
            map.put(i, new HashMap<Integer, Integer>());
        }

        map.get(0).put(1, 10);
        map.get(0).put(2, 1);
        map.get(0).put(3, 4);

        map.get(1).put(0, 10);
        map.get(1).put(2, 3);
        map.get(1).put(4, 0);

        map.get(2).put(0, 1);
        map.get(2).put(1, 3);
        map.get(2).put(5, 8);
        map.get(2).put(3, 2);

        map.get(3).put(0, 4);
        map.get(3).put(2, 2);
        map.get(3).put(5, 2);
        map.get(3).put(6, 7);

        map.get(4).put(1, 0);
        map.get(4).put(5, 1);
        map.get(4).put(7, 8);

        map.get(5).put(2, 8);
        map.get(5).put(4, 1);
        map.get(5).put(7, 9);
        map.get(5).put(6, 6);
        map.get(5).put(3, 2);

        map.get(6).put(3, 7);
        map.get(6).put(5, 6);
        map.get(6).put(7, 12);

        map.get(7).put(4, 8);
        map.get(7).put(5, 9);
        map.get(7).put(5, 12);

        return map;
    }


    private static void kruskalsAlgorithmLazyApproach() {
        int V = 8;
        Map<Integer, Map<Integer, Integer>> map = populateAdjList(V);
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.wt - b.wt);

        for (Integer src : map.keySet()) {
            for (Map.Entry<Integer, Integer> edge : map.get(src).entrySet()) {
                int from = src;
                int to = edge.getKey();
                int wt = edge.getValue();
                Edge e = new Edge(from, to, wt);
                pq.add(e);
            }
        }

        UnionFinder uf = new UnionFinder();
        uf.initialize(V);

        List<Edge> mstEdges = new ArrayList<>();
        int mstEdgeTarget = V - 1;
        while (!pq.isEmpty() && mstEdges.size() != mstEdgeTarget) {
            Edge popped = pq.poll();
            int from = popped.from;
            int to = popped.to;

            int fromParent = uf.find(from);
            int toParent = uf.find(to);

            if (fromParent == toParent) {
                continue;
            } else {
                uf.union(from, to);
                mstEdges.add(popped);
            }
        }
        int minCost = 0;
        for (Edge e : mstEdges) {
            minCost += e.wt;
        }

        System.out.println(mstEdges);
        System.out.println(minCost);
    }

    static class UnionFinder {
        int[] arr;

        private void initialize(int V) {
            arr = new int[V];
            for (int i = 0; i < V; i++) {
                arr[i] = -1;
            }
        }

        public int find(int src) {
            if (arr[src] < 0) {
                return src;
            }
            arr[src] = find(arr[src]);
            return arr[src];
        }


        public void union(int v1, int v2) {
            int p1 = find(v1);
            int p2 = find(v2);

            // Same rank
            if (arr[p1] == arr[p2]) {
                int temp = arr[p2];
                arr[p2] = p1;
                arr[p1] += temp;
            } else if (arr[p1] < arr[p2]) {
                int temp = arr[p2];
                arr[p2] = p1;
                arr[p1] += temp;
            } else if (arr[p1] > arr[p2]) {
                int temp = arr[p1];
                arr[p1] = p2;
                arr[p2] += temp;
            }
        }
    }
}