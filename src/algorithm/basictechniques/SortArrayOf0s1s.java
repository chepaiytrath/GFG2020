package algorithm.basictechniques;

public class SortArrayOf0s1s {
    public static void main(String[] args) {
        //int arr[] = {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0};
        int arr[] = {0};
        sortArrayOf01(arr);
        printArr(arr);
    }

    private static void sortArrayOf01(int[] arr) {
        int firstOneIdx = 0;
        int curr = 0;
        while (curr < arr.length) {
            if (arr[curr] == 0) {
                swap(arr, curr, firstOneIdx);
                firstOneIdx++;
            }
            curr++;
        }
    }

    private static void swap(int[] arr, int curr, int firstOneIdx) {
        int temp = arr[curr];
        arr[curr] = arr[firstOneIdx];
        arr[firstOneIdx] = temp;
    }

    private static void printArr(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
