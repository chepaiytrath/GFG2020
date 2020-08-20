package educative.gtci.p05cyclicsort.easy;

import java.util.ArrayList;
import java.util.List;

public class A05FindAllDuplicateNumbers {
    public static void main(String[] args) {
        findAllDupes();
    }

    private static void findAllDupes() {
        // 1 INDEXED UNSORTED ARRAY
//        int[] arr = new int[]{3, 4, 4, 5, 5};
        int[] arr = new int[]{5, 4, 7, 2, 3, 5, 3};

        int n = arr.length;
        int i = 0;
        while (i < n) {
            int j = arr[i] - 1;
            if (i == j || arr[i] == arr[j]) {
                i++;
                continue;
            } else {
                swap(arr, i, j);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if(arr[j] != j + 1){
                res.add(arr[j]);
            }
        }
        System.out.println(res);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
