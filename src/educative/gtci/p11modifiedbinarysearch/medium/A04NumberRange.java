package educative.gtci.p11modifiedbinarysearch.medium;

public class A04NumberRange {
    public static void main(String[] args) {
        findRange();
    }

    private static void findRange() {
        /*int[] arr = new int[]{4, 6, 6, 6, 9};
        int key = 6;*/
        /*int[] arr = new int[]{1, 3, 8, 10, 15};
        int key = 10;*/
        int[] arr = new int[]{1, 3, 8, 10, 15};
        int key = 12;

        int[] res = search(arr, key);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }

    private static int[] search(int[] arr, int key) {
        // Find the leftmost occurrence
        int left = binarySearch(arr, key, true);
        if (left < 0) {
            return new int[]{-1, -1};
        }
        // Find the rightmost occurrence
        int right = binarySearch(arr, key, false);
        return new int[]{left, right};
    }

    private static int binarySearch(int[] arr, int key, boolean flag) {
        int start = 0;
        int end = arr.length - 1;
        int res = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                // Found the key: Now need to find the leftmost occurrence and rightmost occurrence
                // Save mid in res and find the key in the rest of the array : left/right depending on boolean flag
                res = mid;
                if (flag) {
                    // If flag is true, find in left side of mid so : end = mid - 1
                    end = mid - 1;
                } else {
                    // If flag is false, find in right side of mid so : start = mid + 1
                    start = mid + 1;
                }
            } else if (arr[mid] > key) {
                // Normal Binary search updation of indices
                end = mid - 1;
            } else if (arr[mid] < key) {
                // Normal Binary search updation of indices
                start = mid + 1;
            }
        }
        return res;
    }
}
