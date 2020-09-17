package company.amazon.oa.amcat;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{6, 3, 5, 2, 1, 7, 4};
//        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] arr = new int[]{7,1,6,2,5,3,4};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void quickSort(int[] arr, int i, int j) {
        if (i < j) {
            int partition = findPartition(arr, i, j);
            quickSort(arr, i, partition - 1);
            quickSort(arr, partition + 1, j);
        }
    }

    private static int findPartition(int[] arr, int s, int e) {
        int pivot = arr[e];

        int i = s;
        int j = e - 1;
        while (i <= j) {
            if (arr[i] > pivot) {
                swap(arr, i, j);
                j--;
            } else {
                i++;
            }
        }
        swap(arr, i, e);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
