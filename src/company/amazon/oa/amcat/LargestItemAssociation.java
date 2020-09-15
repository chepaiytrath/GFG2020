package company.amazon.oa.amcat;

import java.util.*;

public class LargestItemAssociation {
    public static void main(String[] args) {
        LargestItemAssociation s = new LargestItemAssociation();
        /*List<PairString> input = Arrays.asList(
                new PairString[]{
                        s.new PairString("item1", "item2"),
                        s.new PairString("item3", "item4"),
                        s.new PairString("item4", "item5")
                }
        );

//      Testing equal sized arraylist. 1->2->3->7 4->5->6->7
        List<PairString> input2 = Arrays.asList(
                new PairString[]{
                        s.new PairString("item1", "item2"),
                        s.new PairString("item2", "item3"),
                        s.new PairString("item4", "item5"),
                        s.new PairString("item6", "item7"),
                        s.new PairString("item5", "item6"),
                        s.new PairString("item3", "item7")
                }
        );
        List<String> lst = s.largestItemAssociationDFS(input);
        for (String sa : lst) System.out.print(" " + sa);
        System.out.println();
        List<String> lst2 = s.largestItemAssociationDFS(input2);
        for (String sa : lst2) System.out.print(" " + sa);
        System.out.println();

//      Testing duplicates: 1->2->3->7 , 5->6
        List<PairString> input3 = Arrays.asList(
                new PairString[]{
                        s.new PairString("item1", "item2"),
                        s.new PairString("item1", "item3"),
                        s.new PairString("item2", "item7"),
                        s.new PairString("item3", "item7"),
                        s.new PairString("item5", "item6"),
                        s.new PairString("item3", "item7")
                }
        );
        List<String> lst3 = s.largestItemAssociationDFS(input3);
        for (String sa : lst3) System.out.print(" " + sa);
        System.out.println();*/


        List<PairString> input4 = Arrays.asList(
                new PairString[]{
                        s.new PairString("item1", "item2"),
                        s.new PairString("item3", "item4"),
                        s.new PairString("item4", "item6"),
                        s.new PairString("item4", "item5"),
                        s.new PairString("item6", "item7")
                }
        );

        List<String> lst4 = s.largestItemAssociationDisjointSetsUnionFind(input4);
        for (String sa : lst4) System.out.print(" " + sa);
    }


    class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    public List<String> largestItemAssociationDFS(List<PairString> itemAssociation) {
        Map<String, List<String>> adj = new HashMap<>();
        // CONSIDER IT DIRECTED GRAPH
        for (PairString p : itemAssociation) {
            List<String> list = adj.getOrDefault(p.first, new ArrayList<>());
            list.add(p.second);
            adj.put(p.first, list);
        }

        int maxSizeAssociationGroup = 0;
        // One or more lists with size x grouped together and put as value to key x
        Map<Integer, List<List<String>>> sizeGroupedLists = new HashMap<>();
        for (String item : adj.keySet()) {
            Set<String> connectedComponents = new TreeSet<>();
            dfs(item, adj, connectedComponents);

            int currAssociationGroupSize = connectedComponents.size();

            // Keep track of maximum size of association group
            maxSizeAssociationGroup = Math.max(maxSizeAssociationGroup, currAssociationGroupSize);

            // Put the set into the sizeGroupedLists map. Initialize map with list value if no value present for currAssociationGroupSize
            List<List<String>> list = sizeGroupedLists.getOrDefault(currAssociationGroupSize, new ArrayList<>());
            list.add(new ArrayList<>(connectedComponents));
            sizeGroupedLists.put(currAssociationGroupSize, list);
        }

        // Get the lists for maxSizeAssociationGroup
        List<List<String>> res = sizeGroupedLists.get(maxSizeAssociationGroup);

        // Sort the lists lexicographically
        Collections.sort(res, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                int i = 0;
                int j = 0;
                while (i < o1.size() && j < o2.size()) {
                    if (o1.get(i).equals(o2.get(j))) {
                        i++;
                        j--;
                    } else {
                        return o1.get(i).compareTo(o2.get(j));
                    }
                }
                if (i == o1.size() && j == o2.size()) {
                    return 0;
                } else if (i == o1.size()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        // Return the first list in sorted order
        return res.get(0);
    }

    private static void dfs(String src, Map<String, List<String>> adj, Set<String> connectedComponents) {
        connectedComponents.add(src);
        if (adj.containsKey(src)) {
            for (String child : adj.get(src)) {
                dfs(child, adj, connectedComponents);
            }
        }
    }

    private static List<String> largestItemAssociationDisjointSetsUnionFind(final List<PairString> itemAssociation) {
        final UnionFind uf = new UnionFind();
        uf.initialize(itemAssociation);

        for (PairString pair : itemAssociation) {
            uf.union(pair.first, pair.second);
        }

        String largest = "";
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Group> entry : uf.map.entrySet()) {
            if (entry.getValue().items.size() > max) {
                max = entry.getValue().items.size();
                largest = entry.getKey();
            } else if (entry.getValue().items.size() == max) {
                final String key = String.join("", uf.map.get(largest).items);
                if (String.join("", uf.map.get(entry.getKey()).items).compareTo(key) < 0) {
                    largest = entry.getKey();
                }
            }
        }

        return new ArrayList(uf.map.get(largest).items);
    }

    static class UnionFind {
        final Map<String, Group> map = new HashMap<>();

        private void initialize(final List<PairString> pairs) {
            for (final PairString pair : pairs) {
                final Group first = new Group(pair.first);
                final Group second = new Group(pair.second);

                first.items.add(pair.first);
                second.items.add(pair.second);

                map.put(pair.first, first);
                map.put(pair.second, second);
            }
        }

        private void union(final String a, final String b) {
            final String parentA = find(a);
            final String parentB = find(b);

            if (!parentA.equals(parentB)) {
                // UPDATE PARENT IN GROUP OBJECT
                map.get(parentB).parent = parentA;

                // COPY ALL CHILDREN FROM PARENT B TO PARENT A
                for (String childOfB : map.get(parentB).items) {
                    map.get(parentA).items.add(childOfB);
                }
            }
        }

        private String find(final String a) {
            if (map.get(a).parent.equals(a)) {
                return a;
            }
            return find(map.get(a).parent);
        }
    }

    static class Group {
        String parent;
        TreeSet<String> items;

        Group(final String parent) {
            this.parent = parent;
            this.items = new TreeSet<>();
        }
    }
}
