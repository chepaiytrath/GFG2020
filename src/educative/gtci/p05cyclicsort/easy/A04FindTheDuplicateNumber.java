package educative.gtci.p05cyclicsort.easy;

public class A04FindTheDuplicateNumber {
    public static void main(String[] args) {
        findDuplicateImproved();
    }

    // CAN BE IMPROVED : WHEN CYCLE IS FOUND, THAT IS THE DUPLICATE
    // NO NEED FOR THE SECOND TRAVERSAL OVER THE ARRAY
    private static void findDuplicate() {
        // 1 INDEXED UNSORTED ARRAY
        int[] arr = new int[]{1, 4, 4, 3, 2};
//        int[] arr = new int[]{2, 1, 3, 3, 5, 4};
//        int[] arr = new int[]{2, 4, 1, 4, 4};
        int n = arr.length;

        int i = 0;
        while (i < n) {
            int j = arr[i] - 1;
            if (i == j || arr[j] == arr[i]) {
                i++;
                continue;
            } else {
                swap(arr, i, j);
            }
        }

        int res = -1;
        for (int j = 0; j < n; j++) {
            if (arr[j] - 1 != j) {
                res = arr[j];
                break;
            }
        }
        System.out.println("DUPLICATE NUMBER IS " + res);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void findDuplicateImproved() {
        // 1 INDEXED UNSORTED ARRAY
//        int[] arr = new int[]{1, 4, 4, 3, 2};
//        int[] arr = new int[]{2, 1, 3, 3, 5, 4};
        int[] arr = new int[]{2, 4, 1, 4, 4};
        int n = arr.length;

        int res = -1;
        int i = 0;
        while (i < n) {
            int j = arr[i] - 1;
            if (i == j) {
                i++;
                continue;
            } else {
                if (arr[i] != arr[j]) {
                    swap(arr, i, j);
                } else {
                    // CYCLE FOUND : THIS IS THE DUPLICATE
                    res = arr[i];
                    break;
                }
            }
        }
        System.out.println("DUPLICATE NUMBER IS " + res);
    }
}
