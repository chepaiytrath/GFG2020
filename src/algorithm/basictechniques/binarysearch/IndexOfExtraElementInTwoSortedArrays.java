package algorithm.basictechniques.binarysearch;

public class IndexOfExtraElementInTwoSortedArrays {
    public static void main(String[] args) {
        indexOfExtraElement();
    }

    private static void indexOfExtraElement() {
        int[] arr1 = new int[]{2, 4, 6, 8, 9, 10, 12};
        int[] arr2 = new int[]{2, 6, 8, 9, 10, 12};

        int n = arr1.length;

        int lo = 0;
        int hi = n - 1;
        int resIndex = -1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // SAME TILL NOW, MISSING ELEMENT IN RHS
            if (arr1[mid] == arr2[mid]) {
                lo = mid + 1;
            } else {
                // NOT SAME TILL NOW, MISSING ELEMENT IN LHS
                resIndex = mid;
                hi = mid - 1;
            }
        }

        System.out.println(resIndex);
    }
}
