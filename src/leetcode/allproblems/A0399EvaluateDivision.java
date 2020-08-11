package leetcode.allproblems;

import java.util.*;

// Each equation is like an edge in graph with weight as corresponding values[i]
class A0399EvaluateDivision {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        double[] values = {2.0, 3.0, 5.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));

        double[] res = calcEquation(equations, values, queries);
        for (double v : res) {
            System.out.println(v);
        }
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        populateMap(map, equations, values);


        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);
            Set<String> visited = new HashSet<>();
            double initialValue = 1.0;

            result[i] = dfs(src, dest, visited, map, initialValue);
            if (result[i] == 0.0) {
                result[i] = -1.0;
            }
        }

        return result;
    }

    // Initial call, val = 1.0 to handle a/a which should give 1.0
    private static double dfs(String src, String dest, Set<String> visited, Map<String, Map<String, Double>> map, Double val) {
        // containsKey check handles edge case wherein an invalid query is made with a string which is not part of map
        if (!map.containsKey(src)) {
            return 0.0;
        }
        //if src == dest, then we have reached the dest with the proper val sent in each call. Just return that
        if (src.equals(dest)) {
            return val;
        }

        visited.add(src);
        double res = 0.0;
        for (String child : map.get(src).keySet()) {
            //visited check
            if (!visited.contains(child)) {
                res = dfs(child, dest, visited, map, val * map.get(src).get(child));
                //Break after finding the first non zero value which is the result
                if (res != 0.0) {
                    break;
                }
            }
        }
        return res;
    }

    // Simulate weighted edges
    private static void populateMap(Map<String, Map<String, Double>> map, List<List<String>> equations, double[] values) {
        for (int i = 0; i < values.length; i++) {
            //Each entry in equation
            String s1 = equations.get(i).get(0);
            String s2 = equations.get(i).get(1);

            //Initialize HashMap to store weights
            if (!map.containsKey(s1)) {
                map.put(s1, new HashMap<String, Double>());
            }
            if (!map.containsKey(s2)) {
                map.put(s2, new HashMap<String, Double>());
            }

            // Fill weight sin hashmap
            // a -> (b, 2)
            // b -> (a, 1/2)
            map.get(s1).put(s2, values[i]);
            map.get(s2).put(s1, 1.0 / values[i]);
        }
    }
}