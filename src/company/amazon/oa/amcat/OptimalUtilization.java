package company.amazon.oa.amcat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimalUtilization {
    public static void main(String[] args) {
        // SAMPLE 1
        /*List<int[]> a = new ArrayList<>();
        a.add(new int[]{1, 8});
        a.add(new int[]{2, 7});
        a.add(new int[]{3, 14});

        List<int[]> b = new ArrayList<>();
        b.add(new int[]{1, 5});
        b.add(new int[]{2, 10});
        b.add(new int[]{3, 14});

        int target = 20;*/


        // SAMPLE 2
        /*List<int[]> a = new ArrayList<>();
        a.add(new int[]{1, 2});
        a.add(new int[]{2, 4});
        a.add(new int[]{3, 6});

        List<int[]> b = new ArrayList<>();
        b.add(new int[]{1, 2});
        int target = 7;*/

        List<int[]> a = new ArrayList<>();
        a.add(new int[]{1, 3});
        a.add(new int[]{2, 5});
        a.add(new int[]{3, 7});
        a.add(new int[]{4, 10});

        List<int[]> b = new ArrayList<>();
        b.add(new int[]{1, 2});
        b.add(new int[]{2, 3});
        b.add(new int[]{3, 4});
        b.add(new int[]{4, 5});
        int target = 10;


        List<int[]> res = getPairs(a, b, target);
        for (int[] arr : res) {
            for (int v : arr) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }


    // WORST CASE ALL ELEMENT ARRAYS HAVE SAME VALUE IN INDEX 1
    // O(n^2)
    private static List<int[]> getPairs(List<int[]> list1, List<int[]> list2, int target) {
        // COLLECT RESPONSE IN THIS LIST
        // CLEAR ITS CONTENTS IF A BETTER SUM IS FOUND
        List<int[]> res = new ArrayList<>();

        // SORT TO OPTIMIZE
        Collections.sort(list1, (int[] a, int[] b) -> (a[1] - b[1]));
        Collections.sort(list2, (int[] a, int[] b) -> (a[1] - b[1]));


        int m = list1.size();
        int n = list2.size();

        // START I FROM LOWEST OF LIST1
        // START J FROM HIGHEST OF LIST2
        int i = 0;
        int j = n - 1;

        // KEEP A TRACK OF BEST SUM : <= TARGET AND GREATEST
        int bestsum = Integer.MIN_VALUE;
        while (i < m && j >= 0) {
            int sum = list1.get(i)[1] + list2.get(j)[1];
            if (sum > target) {
                // DECREMENT J BECAUSE IT IS AT ITS HIGHEST
                j--;
            } else if (sum <= target) {

                if (sum > bestsum) {
                    bestsum = sum;
                    res.clear();
                }
                res.add(new int[]{list1.get(i)[0], list2.get(j)[0]});
                int idx = i + 1;
                while (idx < m && list1.get(idx)[1] == list1.get(idx - 1)[1]) {
                    res.add(new int[]{list1.get(idx)[0], list2.get(j)[0]});
                    idx++;
                }
                i++;
            }
        }

        return res;
    }
}
