package algorithm.basictechniques.leftrightarray;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/find-whether-subarray-form-mountain-not/
public class MountainSubarrayProblem {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 2, 4, 4, 6, 3, 2};
        int n = 8;
        int q = 2;

        // RANGE QUERIES
        ArrayList<Pair> queries = new ArrayList<>();
        Pair p = new Pair(0, 2);
        queries.add(p);
        p = new Pair(1, 3);
        queries.add(p);

        System.out.println(processQueries(arr, n, queries, q));
    }

    static class Pair {
        int l;
        int r;

        Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static ArrayList<Boolean> processQueries(int[] arr, int n, ArrayList<Pair> queries,
                                                    int q) {
        // EITHER ALL INCREASING
        // OR ALL DECREASING
        // OR FIRST SOME INCREASING AND THEN REST DECREASING : MOUNTAIN

        // Stores last increasing till each index form left to right
        // LEFT[I] : PEAK TILL THAT INDEX FROM LEFT TO RIGHT
        int[] left = new int[n];


        // Stores first decreasing till each index form right to left
        // RIGHT[I] : PEAK TILL THAT INDEX FROM RIGHT TO LEFT
        int[] right = new int[n];

        // THUMB RULE:
        // PREPROCESS THE TWO ARRAYS : LEFT AND RIGHT
        // BOTH LEFT AND RIGHT PEAKS SHOULD MATCH FOR A RANGE I-J LIKE THIS:
        // LEFT[J] <= RIGHT[I] : THEN IT IS A MOUNTAIN
        left[0] = 0;
        int lastinc = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                lastinc = i;
            }
            left[i] = lastinc;
        }

        right[n - 1] = n - 1;
        int firstdec = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                firstdec = i;
            }
            right[i] = firstdec;
        }

        ArrayList<Boolean> res = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            Pair p = queries.get(i);
            res.add(right[p.l] >= left[p.r]);
        }

        return res;
    }
}
