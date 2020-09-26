package rough;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, -1};
        rearrange(arr, arr.length);
        for (int val : arr) {
            System.out.print(val + " ");
        }
    }

    static void rearrange(int arr[], int n) {
        // code here
        int[] res = new int[n];


        boolean flag = false;
        if (arr[0] < 0) {
            flag = true;
        }
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();


        for (int val : arr) {
            if (flag) {
                if (val < 0) {
                    first.add(val);
                } else {
                    second.add(val);
                }
            } else {
                if (val >= 0) {
                    first.add(val);
                } else {
                    second.add(val);
                }
            }
        }

        int idx = 0;
        int i = 0;
        int j = 0;
        while (i < first.size() && j < second.size()) {
            res[idx] = first.get(i++);
            idx++;
            res[idx] = second.get(j++);
            idx++;
        }

        while (i < first.size()) {
            res[idx] = first.get(i++);
            idx++;
        }
        while(j < second.size()) {
            res[idx] = second.get(j++);
            idx++;
        }

        arr = res;
    }
}