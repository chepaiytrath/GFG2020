package datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.swing.border.MatteBorder;

public class DirectedGraphAdjacencyList {
    private int v;
    private ArrayList<Integer>[] adj;

    DirectedGraphAdjacencyList(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int x, int y) {
        adj[x].add(y);
        // VICE VERSA NOT TO BE DONE IN DIRECTED GRAPH
        // adj[y].add(x);
    }

    public void dfs(int source) {
        // O(V + E)
        boolean[] vis = new boolean[v];
        dfsUtil(source, adj, vis);
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                dfsUtil(i, adj, vis);
            }
        }
    }

    private void dfsUtil(int ind, ArrayList<Integer>[] adj, boolean[] vis) {
        vis[ind] = true;
        System.out.print(ind + " ");
        for (int childInd : adj[ind]) {
            if (!vis[childInd]) {
                dfsUtil(childInd, adj, vis);
            }
        }
    }

    public void findMotherVertexBruteForce() {
        // Time : O(V (V + E))
        // Space : O(V)
        // EXPLANATION: DFS on each vertex. First vertex for which all visited are true
        // after a DFS is the one of the mother vertices
        int mother = -1;
        for (int i = 0; i < v; i++) {
            boolean[] vis = new boolean[v];
            if (!vis[i]) {
                findMotherVertexBruteForceUtil(i, adj, vis);
            }
            if (allVisited(vis)) {
                mother = i;
            }
        }
        if (mother != -1) {
            System.out.println("Mother vertex is : " + mother);
        } else {
            System.out.println("Mother vertex is not found ");
        }
    }

    private void findMotherVertexBruteForceUtil(int ind, ArrayList<Integer>[] adj, boolean[] vis) {
        vis[ind] = true;
        for (int childInd : adj[ind]) {
            if (!vis[childInd]) {
                findMotherVertexBruteForceUtil(childInd, adj, vis);
            }
        }
    }

    private boolean allVisited(boolean[] vis) {
        for (boolean b : vis) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    List<Integer> finished = new ArrayList<>();

    public void findMotherVertexBetterSolution() {
        // O(V + E)
        // Also called Kosarajus Strongly connected component algo
        // EXPLANATION: If there exist mother vertex (or vertices),
        // then one of the mother vertices is the last finished vertex in DFS.
        // (Or a mother vertex has the maximum finish time in DFS traversal)
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                findMotherVertexBetterSolutionUtil(i, visited);
            }
        }

        visited = new boolean[v];
        int lastFin = finished.get(finished.size() - 1);
        findMotherVertexBetterSolutionUtil(lastFin, visited);
        if (allVisited(visited)) {
            System.out.println("Mother vertex is : " + lastFin);
        } else {
            System.out.println("Mother vertex is not found ");
        }
    }

    private void findMotherVertexBetterSolutionUtil(int i, boolean[] visited) {
        visited[i] = true;
        for (int child : adj[i]) {
            if (!visited[child]) {
                findMotherVertexBetterSolutionUtil(child, visited);
            }
        }
        finished.add(i);
    }

    public void printTransitiveClosure() {
        int[][] mat = new int[v][v];
        for (int i = 0; i < v; i++) {
            printTransitiveClosureUtil(i, mat[i]);
        }

        for (int i = 0; i < v; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    private void printTransitiveClosureUtil(int i, int[] vis) {
        vis[i] = 1;
        for (int child : adj[i]) {
            if (vis[child] == 0) {
                printTransitiveClosureUtil(child, vis);
            }
        }
    }

    public void dfsIterative() {
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfsIterativeUtil(i, visited);
            }
        }
    }

    private void dfsIterativeUtil(int source, boolean[] visited) {
        Stack<Integer> st = new Stack<>();
        visited[source] = true;
        st.add(source);
        while (!st.isEmpty()) {
            Integer popped = st.pop();
            List<Integer> children = adj[popped];
            System.out.print(popped + " ");
            for (int i = children.size() - 1; i >= 0 ; i--) {
                int child = children.get(i);
                if (!visited[child]) {
                    visited[child] = true;
                    st.add(child);
                }
            }
        }
    }
}