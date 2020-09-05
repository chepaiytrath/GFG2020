package algorithm.sorting;

public class QuickSort {
    // arr[]: Input Array
    // N : Size of the Array arr[]

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 1, 3, 5};
        quicksort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    private static void quicksort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int part = getpartition(arr, lo, hi);
            quicksort(arr, lo, part - 1);
            quicksort(arr, part + 1, hi);
        }
    }

    private static int getpartition(int[] arr, int lo, int hi) {
        // FIX PIVOT
        int pivot = arr[hi];

        // INDEX POINTS TO NEXT AVAILABLE LOCATION TO SWAP A SMALLER NUMBER THAN PIVOT, WHEN FOUND
        int index = lo;
        for (int i = lo; i < hi; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, index);
                index++;
            }
        }

        // THIS PLACES PIVOT AT ITS CORRECT INDEX
        // NOW ALL THAT'S LEFT IS TO QUICK SORT THE LEFT HALF AND RIGHT HALF OF THIS INDEX
        if (index < hi) {
            swap(arr, index, hi);
        }

        return index;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}