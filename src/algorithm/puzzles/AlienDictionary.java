package algorithm.puzzles;

import java.util.ArrayList;
import java.util.Stack;

public class AlienDictionary {
    public static void main(String[] args) {
//        String[] str = new String[]{"baa", "abcd", "abca", "cab", "cad" };
        String[] str = new String[]{"caa", "aaa", "aab" };
        int k = 3;

        findOutput(str, k);
    }

    private static void findOutput(String[] str, int k) {
        Graph g = new Graph(k);
        int words = str.length;
        for (int i = 0; i < words - 1; i++) {
            String first = str[i];
            String second = str[i + 1];
            int limit = Math.min(first.length(), second.length());
            for (int j = 0; j < limit; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    g.addEdge(first.charAt(j) - 'a', second.charAt(j) - 'a');
                    break;
                }
            }
        }
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[k];
        for (int i = 0; i < k; i++) {
            int ch = str[i].charAt(0) - 'a';
            if (!visited[ch]) {
                topologicalSort(ch, st, g.adj, visited);
            }
        }

        while (!st.isEmpty()) {
            char ch = (char) (st.pop() + 'a');
            System.out.print(ch + " ");
        }
    }

    private static void topologicalSort(int ch, Stack<Integer> st, ArrayList<Integer>[] adj, boolean[] visited) {
        visited[ch] = true;
        for (int c : adj[ch]) {
            if (!visited[c]) {
                topologicalSort(c, st, adj, visited);
            }
        }
        st.add(ch);
    }

    static class Graph {
        int V;
        ArrayList<Integer>[] adj;

        Graph(int verCount) {
            this.V = verCount;
            adj = new ArrayList[V];
            for (int i = 0; i < verCount; i++) {
                adj[i] = new ArrayList();
            }
        }

        private void addEdge(int u, int v) {
            adj[u].add(v);
        }
    }
}
