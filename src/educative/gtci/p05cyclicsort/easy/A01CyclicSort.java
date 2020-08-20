package educative.gtci.p05cyclicsort.easy;

public class A01CyclicSort {
    public static void main(String[] args) {
        cyclicSort();
    }

    private static void cyclicSort() {
        int[] arr = new int[]{1, 5, 6, 4, 3, 2};
        int n = arr.length;

        int i = 0;
        while (i < n) {
            if (arr[i] - 1 == i) {
                i++;
                continue;
            }
            int j = arr[i] - 1;
            swap(arr, i, j);
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
