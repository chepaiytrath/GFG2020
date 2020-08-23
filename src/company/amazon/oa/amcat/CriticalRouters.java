package company.amazon.oa.amcat;

import java.util.*;

public class CriticalRouters {
    public static void main(String[] args) {
        /*List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(1,2));
        connections.add(Arrays.asList(2,0));
        connections.add(Arrays.asList(1,3));

        int n = 4;*/


        int n = 5;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(1, 0));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(3, 2));
        connections.add(Arrays.asList(4, 2));
        connections.add(Arrays.asList(4, 3));
        connections.add(Arrays.asList(3, 0));
        connections.add(Arrays.asList(4, 0));
        System.out.println(criticalConnectionsWithTarjan(n, connections));
    }

    private static List<List<Integer>> criticalConnections(int n, List<List<Integer>> edges) {
        List<List<Integer>> criticalEdges = new ArrayList<>();
        Map<Integer, Set<Integer>> adj = populateAdjacencyList(n, edges);

        for (List<Integer> edge : edges) {
            //Remove edge from adj list
            int v1 = edge.get(0);
            int v2 = edge.get(1);
            adj.get(v1).remove(v2);
            adj.get(v2).remove(v1);


            boolean[] visited = new boolean[n];
            int src = 0; //can be any number if it still is connected after removing edg
            dfs(src, adj, visited);

            if (!checkAllVisited(visited)) {
                criticalEdges.add(Arrays.asList(v1, v2));
            }

            //Add edge back to adj list
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }
        return criticalEdges;
    }

    private static Map<Integer, Set<Integer>> populateAdjacencyList(int n, List<List<Integer>> edges) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new HashSet<>());
        }

        for (List<Integer> edge : edges) {
            int i = edge.get(0);
            int j = edge.get(1);
            adj.get(i).add(j);
            adj.get(j).add(i);
        }
        return adj;
    }

    private static void dfs(int src, Map<Integer, Set<Integer>> adj, boolean[] visited) {
        visited[src] = true;
        for (int c : adj.get(src)) {
            if (!visited[c]) {
                dfs(c, adj, visited);
            }
        }
    }

    private static boolean checkAllVisited(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


    public static List<List<Integer>> criticalConnectionsWithTarjan(int n, List<List<Integer>> edges) {
        // id stores the discovery sequence
        int[] id = new int[n];
        // lo stores the lowest index that a node is connected to
        int[] lo = new int[n];

        // Fill all id with -1 so as to check visited : no need for boolean[] visited
        Arrays.fill(id, -1);

        // Response list
        List<List<Integer>> res = new ArrayList<>();

        // Populate a new Adjacency list from the edge list
        Map<Integer, List<Integer>> adj = populateAdjacencyList(edges);

        // Do a DFS for non-visited ones
        // Each DFS adds the bridges into the resultant list
        for (int i = 0; i < n; i++) {
            // Use id == -1 to check if not visited
            if (id[i] == -1) {
                tarjanDfs(i, id, lo, res, adj, i);
            }
        }
        return res;
    }

    // Keep this ctr to populate id[] values when each node is discovered for the first time.
    // Keep incrementing the ctr at each DFS.
    static int ctr = 0;

    private static void tarjanDfs(int src, int[] id, int[] lo, List<List<Integer>> res, Map<Integer, List<Integer>> adj, int parent) {
        // Initialize with ctr and increment ctr
        // This ensures the discovery order is maintained by id[]
        id[src] = ctr;

        //Initialize lo[] with same value of ctr because this is the lowest id that the current node has encountered
        lo[src] = ctr;
        ctr++;

        // THREE CHECKS FOR EACH CHILD
        //1. PARENT CHECK
        //2. CHILD NOT VISITED CHECK : DFS INTO CHILD AND UPDATE LO SRC WHEN BACKTRACKING
        //3. CHILD ALREADY VISITED CHECK : CYCLE FOUND : UPDATE LO[SRC]
        for (int child : adj.get(src)) {
            // CAN'T LOOK BACK IN THE DFS. ONLY OPTION TO MOVE AHEAD AND TRY FINDING A VISITED NODE WHICH CREATES A CYCLE.
            if (child == parent) {
                continue;
            }

            // If not visited, then DFS into child.
            // Once it comes back from DFS of child, check if the child has found a cycle with some other SCC, i.e. lo[child] > id[src]
            if (id[child] == -1) {
                tarjanDfs(child, id, lo, res, adj, src);
                // PROPAGATE THE LO OF CHILD : LO[CHILD]
                lo[src] = Math.min(lo[src], lo[child]);
                if (lo[child] > id[src]) {
                    res.add(Arrays.asList(src, child));
                }
            }
            // If child is already visited : child's ID id[child] would be lower than id[src] and lo[src]
            // Update only the lo[src] : This means loop found with a visited node other than parent.
            // That node Id will surely be lesser than the src
            else {
                // NOTE: MIN BETWEEN LO AND ID
                lo[src] = Math.min(lo[src], id[child]);
            }
        }
    }

    private static Map<Integer, List<Integer>> populateAdjacencyList(List<List<Integer>> edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);


            List<Integer> l1 = adj.getOrDefault(u, new ArrayList<Integer>());
            l1.add(v);
            adj.put(u, l1);

            List<Integer> l2 = adj.getOrDefault(v, new ArrayList<Integer>());
            l2.add(u);
            adj.put(v, l2);
        }
        return adj;
    }
}
