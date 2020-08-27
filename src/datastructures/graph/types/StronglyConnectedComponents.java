package datastructures.graph.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// #REVISIT
public class StronglyConnectedComponents {
    public static void main(String[] args) {
        findSCCWithTarjan();
    }

    public static void findSCCWithTarjan() {
        int V = 8;
        List<List<Integer>> adj = new ArrayList<>();
        createEmptyAdjList(adj, 8);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 4);
        addEdge(adj, 3, 2);
        addEdge(adj, 3, 0);
        addEdge(adj, 4, 0);
        addEdge(adj, 0, 7);
        addEdge(adj, 0, 5);
        addEdge(adj, 7, 4);
        addEdge(adj, 7, 5);
        addEdge(adj, 7, 1);
        addEdge(adj, 5, 6);
        addEdge(adj, 1, 5);
        addEdge(adj, 6, 1);

        int[] id = new int[V];
        Arrays.fill(id, -1);
        int[] lo = new int[V];
        Arrays.fill(lo, Integer.MAX_VALUE);
        Stack<Integer> stack = new Stack<>();
        boolean[] onStack = new boolean[V];


        for (int i = 0; i < V; i++) {
            if (id[i] == -1) {
                tarjanDfs(i, adj, id, lo, stack, onStack);
            }
        }
        System.out.println(sccCount);
    }

    static int sccCount = 0;
    static int ctr = 0;

    // SIMILAR TO CRITICAL ROUTERS TARJAN
    private static void tarjanDfs(int src, List<List<Integer>> adj, int[] id, int[] lo, Stack<Integer> stack, boolean[] onStack) {
        // RULE 1. ASSIGN ID AND LO BY DEFAULT AS SAME VALUE OF CTR
        id[src] = ctr;
        lo[src] = ctr;

        //RULE 2: MAINTAIN AN ACTUAL STACK OF ELEMENTS VISITED AND ONSTACK ARR TO KEEP A TRACK OF WHICH ELEMENTS ARE ON THE STACK
        // THIS HELPS IN PROPER ASSIGNMENT OF VALUES INTO ID[] AND LO[] OF NODES BEING CURRENTLY PROCESSED
        // IF NOT USED, LO[] OF A NODE CAN MISTAKENLY BE SET AS ID OF A NODE WHICH WAS A PART OF A PREVIOUSLY PROCESSED SCC
        // AND WILL THUS HAVE A LOW VALUE AS ITS ID. WE WANT TO SEPARATE THE PREVIOUSLY PROCESSED SCC AND THE CURRENT SCC BEING PROCESSED
        stack.add(src);
        onStack[src] = true;

        // INCREMENT CTR FOR POPULATING ID AND LO OF NEXT NEIGHBORS
        ctr++;

        for (int next : adj.get(src)) {
            // DFS INTO UNVISITED NEIGHBOR
            if (id[next] == -1) {
                // THIS WILL SET THE DEFAULT VALUES IN ID AND LO
                tarjanDfs(next, adj, id, lo, stack, onStack);
            }

            // RULE 3: WHEN BACKTRACKING, SET LO OF SRC AS MIN OF JUST-PROCESSED NODE
            if (onStack[next]) {
                lo[src] = Math.min(lo[src], lo[next]);
            }
        }

        // RULE 4: A NODE WITH SAME VALUE OF LO AND ID MEANS IT IS START OF THE CYCLE
        // IN THAT CASE, POP ELEMENTS OF THIS CYCLE AND INCREMENT SCCCOUNT
        if (id[src] == lo[src]) {
            //FOUND START VERTEX OF A SCC
            while (!stack.isEmpty()) {
                int popped = stack.pop();
                onStack[popped] = false;
                if (popped == src) {
                    break;
                }
            }
            sccCount++;
        }
    }

    private static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    private static void createEmptyAdjList(List<List<Integer>> adj, int V) {
        for (int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<>());
        }
    }
}
