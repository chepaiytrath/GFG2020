package educative.gtci.p05cyclicsort.easy;

public class A02FindTheMissingNumber {
    public static void main(String[] args) {
        findMissingNumber();
    }

    private static void findMissingNumber() {
        // 0 indexed
        int[] arr = new int[]{8, 3, 5, 2, 4, 6, 0, 1};
//        int[] arr = new int[]{4, 0, 3, 1};
        int n = arr.length;

        int i = 0;
        while (i < n) {
            if (arr[i] == i || arr[i] >= n) {
                i++;
                continue;
            } else if (arr[i] < n) {
                int j = arr[i];
                swap(arr, i, j);
            }
        }

        int res = -1;
        for (int j = 0; j < n; j++) {
            if (arr[j] != j) {
                res = j;
                break;
            }
        }

        System.out.println("Missing number is : " + res);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
