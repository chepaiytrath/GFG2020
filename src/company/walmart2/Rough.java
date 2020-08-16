package company.walmart2;

import java.util.*;

public class Rough {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, 3, 5}, {4, 2, 1, 5, 3, 2}, {7, 5, 7, 3}
        };
        printOccurrences(arr);
    }


    private static void printOccurrences(int[][] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] row : arr) {
            for (int i = 0; i < row.length; i++) {
                int num = row[i];
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        Comparator<Map.Entry<Integer, Integer>> compareById = (Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) -> o2.getValue() - o1.getValue();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(set);
        Collections.sort(list, compareById);

        System.out.println(list.get(0).getKey());


    }
}
