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





    private static List<String> largestItemAssociationDisjointSetsUnionFind(List<PairString> itemAssociation) {
        // INITIALIZE UNION FIND MAP
        UnionFind uf = new UnionFind();
        uf.initialize(itemAssociation);

        // EXECUTE UNION FOR ALL PAIRS
        for (PairString p : itemAssociation) {
            uf.union(p.first, p.second);
        }

        // TRAVERSE UNION FIND MAP TO FIND KEY WITH LARGEST CHILDREN SET
        int largestGroupSize = 0;
        String largestItemKey = "";
        for (Map.Entry<String, Group> entry : uf.relationMap.entrySet()) {
            String item = entry.getKey();
            int size = entry.getValue().children.size();
            if (size > largestGroupSize) {
                largestGroupSize = size;
                largestItemKey = item;
            } else if (size == largestGroupSize) {
                String currEntryChildren = String.join("", entry.getValue().children);
                String largestGroupSoFar = String.join("", uf.relationMap.get(largestItemKey).children);
                if(currEntryChildren.compareTo(largestGroupSoFar) < 0){
                    largestItemKey = item;
                }
            }
        }

        // RETURN LARGEST GROUP SIZE AS LIST
        return new ArrayList(uf.relationMap.get(largestItemKey).children);
    }

    static class UnionFind {
        // relationMap stores mapping of
        // item1 -> Parent of item1, Children of item1
        // Parent of item1, Children of item1 <-> Group
        Map<String, Group> relationMap = new HashMap<>();

        // INITIALIZE MAP
        // 1 -> 1, blank children set
        // 2 -> 2, blank children set
        // 3 -> 3, blank children set ....
        void initialize(List<PairString> pairs) {
            for (PairString p : pairs) {
                String first = p.first;
                String second = p.second;

                Group g1 = new Group(first);
                g1.children.add(first);

                Group g2 = new Group(second);
                g2.children.add(second);

                relationMap.put(first, g1);
                relationMap.put(second, g2);
            }
        }

        // UNION OF TWO ITEMS
        // Find parent of each item and
        // 1. Update parents in relationMap
        // 2. Add all children of parent2 to parent1
        void union(String first, String second) {
            String parent1 = find(first);
            String parent2 = find(second);
            if (!parent1.equals(parent2)) {
                // 1. Update parents in relationMap
                relationMap.get(parent2).parent = parent1;

                // 2. Add all children of parent2 to parent1
                for (String child : relationMap.get(parent2).children) {
                    relationMap.get(parent1).children.add(child);
                }
            }
        }

        // FIND PARENT OF AN ITEM
        // Recursively find parent
        // Root is the item with same parent a itself (Similar to -1 in Abdul Bari Example)
        private String find(String item) {
            if (relationMap.get(item).parent.equals(item)) {
                return item;
            }
            return find(relationMap.get(item).parent);
        }
    }

    static class Group {
        String parent;
        TreeSet<String> children;

        Group(String parent) {
            this.parent = parent;
            this.children = new TreeSet<>();
        }
    }
}
