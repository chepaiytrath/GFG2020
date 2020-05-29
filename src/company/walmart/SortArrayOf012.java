//Dutch National Flag Problem
package company.walmart;

public class SortArrayOf012 {
    public static void main(String[] args) {
        int arr[] = {2, 0, 1, 2, 0, 1, 2, 0, 1};
        sortArrayOf012(arr);
        printArr(arr);
    }

    private static void sortArrayOf012(int[] arr) {
        int lo = 0, mid = 0, hi = arr.length - 1;
        while (mid <= hi) {
            int x = arr[mid];
            if (x == 0) {
                swap(arr, lo, mid);
                lo++;
                mid++;
            } else if (x == 1) {
                mid++;
            } else if (x == 2) {
                swap(arr, mid, hi);
                hi--;
            }
        }
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static void printArr(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
