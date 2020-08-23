package educative.gtci.p11modifiedbinarysearch.easy;

public class A07BitonicArrayMaximum {
    public static void main(String[] args) {
        bitonicArray();
    }

    private static void bitonicArray() {
//        int[] arr = new int[]{1, 3, 8, 12, 4, 2};
//        int[] arr = new int[]{3, 8, 3, 1};
//        int[] arr = new int[]{1, 3, 8, 12};
        int[] arr = new int[]{10, 9, 8};
        int num = binarySearch(arr);
        System.out.println(num);
    }

    private static int binarySearch(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;

        // VERY GOOD DESCRIPTION IN EDUCATIVE
        // NOT START <= END
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[mid + 1]) {
                start = mid + 1;
            } else {
                // NOT END = MID - 1
                end = mid;
            }
        }
        return arr[start];
    }
}
