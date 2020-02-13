package algorithm.searching;

public class BinarySearch {
    private static int binarySearch(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] < x) {
                start = mid + 1;
            } else if (arr[mid] > x) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -(start + 1);
    }

    public static void main(String[] args) {
        int arr[] = {2, 4, 6, 8, 10, 12, 14, 16};
        int x = 20;

        int index = binarySearch(arr, x);
        if (index < 0) {
            System.out.println("Not found");
        } else {
            System.out.println("Found at index : " + index);
        }
    }
}
