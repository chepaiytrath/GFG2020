package algorithm.palindromesanagram;

import java.util.*;

public class GroupAnagramsTogether {
    public static void main(String[] args) {
        System.out.println(groupAnagramsTogether());
    }

    private static List<Integer> groupAnagramsTogether() {
        //String[] arr = new String[]{"act", "cat", "tac", "god", "dog"};
        String[] arr = new String[]{"act", "cat", "tac"};

        TreeMap<String, Integer> map = new TreeMap<>();

        for (String str : arr) {
            char[] sortedArr = str.toCharArray();
            Arrays.sort(sortedArr);
            String sortedStr = new String(sortedArr);
            map.put(sortedStr, map.getOrDefault(sortedStr, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (a, b) -> a.getValue() - b.getValue());

        List<Integer> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list) {
            res.add(entry.getValue());
        }
        return res;
    }
}
