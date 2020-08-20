package educative.gtci.p05cyclicsort.easy;

import java.util.ArrayList;
import java.util.List;

public class A03FindAllMissingNumbers {
    public static void main(String[] args) {
        findAllMissingNumbers();
    }

    private static void findAllMissingNumbers() {
        // 1 Indexed Unsorted array
//        int[] arr = new int[]{2, 3, 1, 8, 2, 3, 5, 1};
//        int[] arr = new int[]{2, 4, 1, 2};
        int[] arr = new int[]{2, 3, 2, 1};
        int n = arr.length;
        int i = 0;
        while (i < n) {
            int j = arr[i] - 1;
            // PREVENT CYCLIC LOOP IF arr[j] == arr[i]
            if (i == j || arr[j] == arr[i]) {
                i++;
                continue;
            } else {
                // OTHERWISE PUT AT THE CORRECT POSITION
                swap(arr, i, j);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int idx = 0; idx < n; idx++) {
            if (arr[idx] - 1 != idx) {
                res.add(idx + 1);
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
