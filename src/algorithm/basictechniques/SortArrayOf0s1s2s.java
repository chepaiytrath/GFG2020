package algorithm.basictechniques;

public class SortArrayOf0s1s2s {
    // DUTCH NATIONAL FLAG PROBLEM
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        sortArray(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void sortArray(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        int mid = 0;
        while (mid <= hi) {
            if (arr[mid] == 0) {
                swap(arr, lo, mid);
                lo++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else if (arr[mid] == 2) {
                swap(arr, mid, hi);
                hi--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
