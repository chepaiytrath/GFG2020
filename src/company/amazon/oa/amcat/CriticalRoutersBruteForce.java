package company.amazon.oa.amcat;

import java.util.*;

public class CriticalRoutersBruteForce {
    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(1,2));
        connections.add(Arrays.asList(2,0));
        connections.add(Arrays.asList(1,3));

        int n = 4;
        System.out.println(criticalConnections(n, connections));
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
}
