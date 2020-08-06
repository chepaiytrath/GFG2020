package company.google.easy;

public class PeakElement {
    public static void main(String[] args) {
//        int[] arr = new int[]{1, 3, 20, 4, 1, 0};
        int[] arr = new int[]{5, 10, 15, 20};
        int peakIndex = findPeakElementWithBinarySearch(arr, 0, arr.length - 1);
        System.out.println(arr[peakIndex]);
    }

    private static int findPeakElementWithBinarySearch(int[] arr, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (mid == 0) {
            if (arr[mid] > arr[mid + 1]) {
                return mid;
            }/*else{
                return -1;
            }*/
        } else if (mid == arr.length - 1) {
            if (arr[mid] > arr[mid - 1]) {
                return mid;
            }/*else{
                return -1;
            }*/
        }
        if (arr[mid] < arr[mid - 1]) {
            return findPeakElementWithBinarySearch(arr, lo, mid - 1);
        }

        if (arr[mid] < arr[mid + 1]) {
            return findPeakElementWithBinarySearch(arr, mid + 1, hi);
        }
        return mid;
    }
}
