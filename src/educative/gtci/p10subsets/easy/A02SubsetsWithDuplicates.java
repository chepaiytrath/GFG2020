package educative.gtci.p10subsets.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A02SubsetsWithDuplicates {
    public static void main(String[] args) {
        findSubsetsWithDupes();
    }

    private static void findSubsetsWithDupes() {
//        int[] arr = new int[]{1, 3, 3};
        int[] arr = new int[]{1, 5, 3, 3};
        int n = arr.length;

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        Arrays.sort(arr);
        int size = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            int start = 0;
            if (i > 0 && num == arr[i - 1]) {
                start = size;
            }
            // COMES HANDY TO GET START INDEX FOR NEXT DUPLICATE VALUE
            size = res.size();
            while (start < size) {
                List<Integer> list = new ArrayList<>(res.get(start));
                list.add(num);
                res.add(list);
                start++;
            }
        }
        System.out.println(res);
    }
}
