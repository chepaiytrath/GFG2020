package company.google.oa;

import java.util.*;

class OpenTheLock {
    public static void main(String[] args) {
        String target = "0202";
        String[] deadends = new String[]{
                "0201", "0101", "0102", "1212", "2002"
        };
        System.out.println(openLock(deadends, target));
    }

    // LOGIC : BFS WITH QUE INITIALIZED BY 0000
    // WHY BFS: IT IS UNWEIGHTED GRAPH AND WE NEED TO FIND THE SHORTEST DISTANCE BETWEEN TWO VERTICES AND THE ROUTE IS DEFINED BY THE SUCCESSORS
    // FIND SUCCESSORS OF EACH POPPED STRING : HOW? : REPLACE EACH CHARACTER WITH EITHER ONE UP OR ONE DOWN
    // EACH STRING GIVES 8 SUCCESSORS : ADD ALL OF THEM TO QUEUE
    // MAINTAIN VISITED SET OF STRINGS INITIALIZED WITH DEADENDS
    // IF POPPED NODE IS ONE OF THE VISITED(ALSO MAY BE A DEADEND), NO NEED TO PUT ITS SUCCESSORS INTO THE QUEUE
    // MAINTAIN A DEPTH WHICH IS INCREMENTED AT BEGINNING OF EACH LEVEL
    // RETURN DEPTH AS ANSWER
    public static int openLock(String[] deadends, String target) {
        Queue<String> que = new LinkedList<>();
        Set<String> visited = new HashSet(Arrays.asList(deadends));
        int dis = -1;
        que.add("0000");
        while (!que.isEmpty()) {
            dis++;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String popped = que.poll();
                // IF IT IS TARGET, JUST RETURN THE CURRENT DEPTH
                if (popped.equals(target)) {
                    return dis;
                }
                //CHECKS BOTH VISITED AND DEADENDS
                if (visited.contains(popped)) {
                    continue;
                }
                // ONCE CHECKED FOR TARGET AND IF NOT VISITED, MARK IT VISITED
                visited.add(popped);
                Set<String> nextCombos = findNextCombos(popped);
                que.addAll(nextCombos);
            }
        }
        return -1;
    }

    public static Set<String> findNextCombos(String str) {
        // USE SET TO AVOID ADDING DUPLICATE COMBOS
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            char ithChar = str.charAt(i);

            // USE 9/0 INSTEAD OF '9'/'0'
            // USING '9'/'0' RESULTS IN APPENDING OF ITS ASCII VALUE INTO STRING
            String dec = str.substring(0, i) + (ithChar == '0' ? 9 : (ithChar - '0' - 1)) + str.substring(i + 1);
            String inc = str.substring(0, i) + (ithChar == '9' ? 0 : (ithChar - '0' + 1)) + str.substring(i + 1);
            set.add(inc);
            set.add(dec);
        }
        return set;
    }
}