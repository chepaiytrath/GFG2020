package company.walmart2;

import java.util.*;

public class Rough {
    public static void main(String[] args) {
        PairItem pi1 = new PairItem("Item1", "Item2");
        PairItem pi2 = new PairItem("Item3", "Item4");
        PairItem pi3 = new PairItem("Item4", "Item5");
        List<PairItem> input = new ArrayList<>();
        input.add(pi1);
        input.add(pi2);
        input.add(pi3);
        List<String> res = findItemAssociationGroup(input);
        System.out.println(res);

        Set<String> set = new HashSet<>();
        Queue<String> que = new LinkedList<>();
        que.addAll(set);

    }

    static class PairItem {
        String first;
        String second;

        PairItem(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    private static List<String> findItemAssociationGroup(List<PairItem> input) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (PairItem pi : input) {
            int first = Integer.valueOf(pi.first.substring(4));
            int second = Integer.valueOf(pi.second.substring(4));
            if (adj.get(first) == null) {
                adj.put(first, new HashSet<>());
            }
            if (adj.get(second) == null) {
                adj.put(second, new HashSet<>());
            }

            adj.get(first).add(second);
            adj.get(second).add(first);
        }

        int V = adj.keySet().size();
        boolean[] visited = new boolean[V + 1];
        List<String> res = new ArrayList<>();
        for (int src : adj.keySet()) {
            if (!visited[src]) {
                Set<String> connected = new HashSet<>();
                dfs(src, visited, adj, connected);
                if (connected.size() > res.size()) {
                    res = new ArrayList(connected);
                }
            }
        }
        return res;
    }

    private static void dfs(int src, boolean[] visited, Map<Integer, Set<Integer>> adj, Set<String> connected) {
        visited[src] = true;
        connected.add("Item" + src);
        for (int c : adj.get(src)) {
            if (!visited[c]) {
                dfs(c, visited, adj, connected);
            }
        }
    }
}
