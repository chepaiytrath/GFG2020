package educative.gtci.p11modifiedbinarysearch.easy;

public class A01OrderAgnosticBinarySearch {
    public static void main(String[] args) {
        binarySearch();
    }

    private static void binarySearch() {
        /*int[] arr = new int[]{4, 6, 10};
        int k = 10;

        int[] arr = new int[]{1,2,3,4,5,6,7};
        int k = 5;

        int[] arr = new int[]{10, 6, 4};
        int k = 10;*/

        int[] arr = new int[]{10, 6, 4};
        int k = 4;

        System.out.println(binarySearchUtil(arr, k));
    }

    // FOLLOW THIS FORMAT
    private static int binarySearchUtil(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        boolean isAscending = arr[start] < arr[end];

        // <= BECAUSE FOR LAST ELEMENT IT WILL BE ON SAME INDEX
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            }

            // MAKE ORDER AGNOSTIC
            if (isAscending) {
                if (arr[mid] < target) {
                    // mid + 1 SO AS TO SEARCH IN THE REMAINING ELEMENTS
                    start = mid + 1;
                } else {
                    // mid - 1 SO AS TO SEARCH IN THE REMAINING ELEMENTS
                    end = mid - 1;
                }
            } else {
                if (arr[mid] < target) {
                    end = mid - 1;
                } else {
                    start = start + 1;
                }
            }
        }
        return -1;
    }
}
