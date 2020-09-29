package algorithm.basictechniques.array;

import java.util.ArrayList;
import java.util.List;

public class FindIntersectionInThreeSortedArrays {
    public static void main(String[] args) {
        System.out.println(findIntersection());
    }

    private static List<Integer> findIntersection() {
        int[] arr1 = new int[]{1, 5, 10, 20, 40, 80};
        int[] arr2 = new int[]{6, 7, 20, 80, 100};
        int[] arr3 = new int[]{3, 4, 15, 20, 30, 70, 80, 120};

        List<Integer> res = new ArrayList<>();

        int i = 0;
        int j = 0;
        int k = 0;

        // If any one pointer has crossed its array length, then break out because no more intersections possible
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            int x = arr1[i];
            int y = arr2[j];
            int z = arr3[k];
            // If all are same, then add to response and increment all 3 pointers
            if (x == y && y == z) {
                res.add(arr1[i]);
                i++;
                j++;
                k++;
            } else if (x < y) {
                // If first element is less than second, only increment first
                i++;
            } else if (y < z) {
                // If above is not true, then if second element is less than third, only increment second
                j++;
            } else {
                // If both above are not true, then z is less than both x and y, so only increment third
                k++;
            }
        }
        return res;
    }
}
