package educative.gtci.p10subsets.easy;

import java.util.ArrayList;
import java.util.List;

public class A01Subsets {
    // Find all of its distinct subsets
    public static void main(String[] args) {
        findSubsets();
    }

    private static void findSubsets() {
        int[] arr = new int[]{1, 5, 3};
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : arr) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newList = new ArrayList<>(res.get(i));
                newList.add(num);
                res.add(newList);
            }
        }
        System.out.println(res);
    }
}
