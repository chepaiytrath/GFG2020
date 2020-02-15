//https://www.geeksforgeeks.org/sort-binary-array-using-one-traversal/
package company.intuit;

public class SortBinaryArray {
    public static void main(String[] args) {
        int arr[] = {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0};
        sortBinaryArray(arr);
        printArr(arr);
    }

    private static void sortBinaryArray(int[] arr) {
        int j = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 1) {
                j++;
                swap(arr, i, j);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArr(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}