package algorithm.sorting;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 6, 3, 4, 1};
        System.out.println("Array before sorting");
        printArr(arr);

        bubbleSort(arr);

        System.out.println("\nArray after sorting");
        printArr(arr);
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void printArr(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
