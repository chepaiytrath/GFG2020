package datastructures.graph;

import datastructures.graph.types.GraphEdgeArray;
import datastructures.graph.types.GraphEdgeArray.Edge;

import java.util.*;


public class ShortestPath {

    // ANOTHER APPROACH TO FIND SSSP: USING TOPOLOGICAL SORT : VERY SIMILAR TO BELLMAN FORD ALGO
    // STEPS TO FIND SSSP FROM SRC TO DEST
    // 1. FIND TOP SORT OF GRAPH
    // 2. CREATE A DISTANCE ARRAY AND INITIALIZE DIST[SRC] = 0
    // 3. ONE BY ONE, PICK EACH ELEMENT FROM TOP SORT ARRAY AND RELAX DIST[] BASED ON ITS ADJACENT EDGE WEIGHT

    public static void main(String[] args) {
        dijkstrasAlgo();
    }

    static class Pair {
        int index;
        int dis;

        Pair(int index, int dis) {
            this.index = index;
            this.dis = dis;
        }
    }

    // DIJKSTRA's ALGO : SINGLE SOURCE SHORTEST PATH
    // Works on both Directed and non directed graphs
    // For working on undirected graphs: convert it into a digraph by inserting parallel edges
    // MAY OR MAY NOT WORK FOR GRAPH WITH NEGATIVE WEIGHTED EDGE : DUE TO ITS GREEDY NATURE
    // O(E*LogV)
    public static void dijkstrasAlgo() {
        int V = 5;
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adj.put(i, new HashMap<>());
        }

        adj.get(0).put(1, 1);
        adj.get(0).put(2, 4);
        adj.get(1).put(2, 2);
        adj.get(1).put(3, 1);
        adj.get(2).put(4, 1);
        adj.get(3).put(4, 5);

        int src = 0;

        // Stores pairs of nodes with distances from source
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);
        // Source node has distance of 0 from itself
        Pair p = new Pair(src, 0);
        pq.add(p);

        // Stores distances from source for each node
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Once a node has been processed, its optimal distance cannot be further improved
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            // POP THE LOWEST PAIR IN PQ : LOWEST DISTANCE TO SOURCE
            Pair popped = pq.poll();
            // MARK IT AS VISITED: DISTANCE CANNOT BE IMPROVED FURTHER
            visited.add(popped.index);

            // OPTIMIZATION STEP
            // IN CASE DUPLICATES ARE PRESENT, IGNORE THE DUPLICATE IF DISTANCE IS ALREADY BETTER IN DIST ARRAY
            if (popped.dis > dist[popped.index]) {
                continue;
            }

            for (Map.Entry<Integer, Integer> edge : adj.get(popped.index).entrySet()) {
                if (!visited.contains(edge.getKey())) {
                    int to = edge.getKey();
                    int wt = edge.getValue();
                    Pair newPair = new Pair(to, dist[popped.index] + wt);

                    // POSSIBLY ADDING DUPLICATES INTO PQ
                    pq.add(newPair);

                    // UPDATE MIN DISTANCE IN DIST ARRAY
                    dist[to] = Math.min(dist[to], newPair.dis);
                }
            }
        }

        for (int distance : dist) {
            System.out.print(distance + " ");
        }
    }


    //BELLMAN FORD : WORKS ON NEGATIVE WEIGHTED EDGE GRAPH AS WELL
    public void bellmanFord(GraphEdgeArray gea) {
        // SAMPLE INPUT
        // GraphEdgeArray gea = new GraphEdgeArray(7, 10);

        // gea.edges[0].src = 0;
        // gea.edges[0].dest = 1;
        // gea.edges[0].wt = 6;

        // gea.edges[1].src = 0;
        // gea.edges[1].dest = 2;
        // gea.edges[1].wt = 5;

        // gea.edges[2].src = 0;
        // gea.edges[2].dest = 3;
        // gea.edges[2].wt = 5;

        // gea.edges[3].src = 1;
        // gea.edges[3].dest = 4;
        // gea.edges[3].wt = -1;

        // gea.edges[4].src = 2;
        // gea.edges[4].dest = 1;
        // gea.edges[4].wt = -2;

        // gea.edges[5].src = 2;
        // gea.edges[5].dest = 4;
        // gea.edges[5].wt = 1;

        // gea.edges[6].src = 3;
        // gea.edges[6].dest = 2;
        // gea.edges[6].wt = -2;

        // gea.edges[7].src = 3;
        // gea.edges[7].dest = 5;
        // gea.edges[7].wt = -1;

        // gea.edges[8].src = 4;
        // gea.edges[8].dest = 6;
        // gea.edges[8].wt = 3;

        // gea.edges[9].src = 5;
        // gea.edges[9].dest = 6;
        // gea.edges[9].wt = 3;

        int source = 0;
        System.out.println(bellmanFordUtil(gea, source));
    }

    // SINGLE SOURCE SHORTEST PATH : SAME AS DIJKSTRA'S ALGO
    private int bellmanFordUtil(GraphEdgeArray gea, int source) {
        // Relaxation : Updation of distances to other nodes on finding a shorter path
        int relaxationCount = gea.V - 1;
        // COULD ALSO BE INT[][] LIKE IN NETWORK DELAY LEETCODE
        Edge[] edges = gea.edges;

        int[] dist = new int[gea.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // RELAX EACH EDGE V - 1 TIMES
        for (int i = 0; i < gea.V - 1; i++) {

            // RELAXATION OF EACH EDGE
            for (int j = 0; j < gea.E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].wt;
                if (dist[u] == Integer.MAX_VALUE) {
                    // IGNORE THE VERTICES WHO HAVE NOT YET BEEN ASSIGNED DISTANCE
                    // INITIALLY ONLY SOURCE VERTEX WILL BE GIVEN A DISTANCE OF 0
                    continue;
                } else {
                    // RELAX EDGE : UPDATE DISTANCE OF 'TO' IF CURR DISTANCE + WT IS LESSER
                    dist[v] = Math.min(dist[v], dist[u] + weight);
                }
            }
        }

        // One more iteration to check for negative weight cycle
        for (int j = 0; j < gea.E; j++) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].wt;
            if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + weight) {
                System.out.println("NEGATIVE WEIGHT CYCLE FOUND");
                return -1;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i : dist) {
            min = Math.min(min, i);
        }
        return min;
    }

    static int INF = Integer.MAX_VALUE;

    // O(V^3)
    public void floydWarshall(Integer[][] graph) {
        // ALSO CALLED: ALL PAIRS SHORTEST PATH
        // SAMPLE INPUT
        // int graph[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 },
        // { INF, INF, INF, 0 } };

        int V = graph.length;

        // HOW IT WORKS: ON BASIS OF DP: KEP UPDATING VALUES OF WEIGHTED ADJACENCY MATRIX BASED ON FINDING A BETTER PATH FROM VIA SOME OTHER NODE

        // FOR EACH NODE(VIA K), TRAVERSE EACH ELEMENT IN MATRIX(VIA I AND J) AND FIND IF GOING THROUGH THAT NODE WILL GIVE A BETTER PATH FOR I TO J
        // I.E. FOR PATH BETWEEN I AND J. IF K EXISTS SUCH THAT I K + K J < I J : THEN USE I K + K J AS VALUE OF I J
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // INF CHECK IS REQUIRED FOR BOTH: WHICH MEANS THERE IS NO EDGE BETWEEN I K  OR  K J : SO IT CANT GIVE A BETTER PATH FOR  I J
                    if (graph[i][k] != INF && graph[k][j] != INF) {
//                        int sum = 0;
//                        sum += graph[i][k] + graph[k][j];

                        int sum = graph[i][k] + graph[k][j];
                        graph[i][j] = Math.min(graph[i][j], sum);
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (graph[i][i] < 0) {
                System.out.println("NEGATIVE WEIGHT CYCLE DETECTED");
                return;
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.println(Arrays.asList(graph[i]).toString());
        }
    }
}