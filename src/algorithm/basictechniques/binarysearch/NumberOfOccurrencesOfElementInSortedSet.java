package algorithm.basictechniques.binarysearch;

// #REVISIT
// BOILS DOWN TO FINDING FIRST AND LAST OCCURRENCE OF A NUMBER IN A SORTED ARRAY WITH DUPLICATES ALLOWED
public class NumberOfOccurrencesOfElementInSortedSet {
    public static void main(String[] args) {
        int[] arr = {3, 5, 5, 5, 5, 7, 8, 8};
        int target = 5;
        int count = findNumberOfOccurrencesOfElementInSortedSetWithBinarySearch(arr, target);
        System.out.println(count);
    }

    private static int findNumberOfOccurrencesOfElementInSortedSetWithBinarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        if (target < arr[0] || target > arr[n - 1]) {
            return 0;
        }
        int left = binarySearch(arr, target, false);
        if (left < 0) {
            return 0;
        }
        int right = binarySearch(arr, target, true);
        return right - left + 1;
    }

    private static int binarySearch(int[] arr, int target, boolean dir) {
        // dir = false : go left
        // dir = true : go right
        int lo = 0;
        int hi = arr.length - 1;
        int targetIndex = -1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >>> 1);
            if (arr[mid] > target) {
                hi = mid - 1;
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                targetIndex = mid;
                if (dir) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return targetIndex;
    }
}
