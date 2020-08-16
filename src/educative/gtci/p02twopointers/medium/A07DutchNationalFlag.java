package educative.gtci.p02twopointers.medium;

public class A07DutchNationalFlag {
    public static void main(String[] args) {
        ductchnf();
    }

    private static void ductchnf() {
        int[] arr = new int[]{2, 2, 0, 1, 2, 0};
        int n = arr.length;

        int l = 0; //0
        int m = 0; //1
        int h = n - 1; //2

        while (m <= h) {
            if (arr[m] == 1) {
                m++;
            } else if (arr[m] == 0) {
                swap(arr, l, m);
                l++;
                m++;
            } else if (arr[m] == 2) {
                swap(arr, m, h);
                h--;
            }
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
