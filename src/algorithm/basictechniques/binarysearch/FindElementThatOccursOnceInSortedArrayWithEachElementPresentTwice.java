package algorithm.basictechniques.binarysearch;

public class FindElementThatOccursOnceInSortedArrayWithEachElementPresentTwice {
    public static void main(String[] args) {
        findElement();
    }

    private static void findElement() {
        int[] arr = new int[]{1, 1, 2, 2, 3, 3, 4, 50, 50, 65, 65};
        int n = arr.length;
        int res = -1;

        //Rule :
        // If no missing number : All first occurrences will be at odd indices and all second occurrences will be at even indices
        // Before the missing number : First occurrence is at even index and second at odd index
        // After the missing number :  First occurrence is at odd index and second at even index

        int i = 0;
        int j = n - 1;

        while (i < j) {
            int mid = i + (j - i) / 2;

            // Mid is at even index
            if (mid % 2 == 0) {
                //For even mid index, check only if arr[mid] == arr[mid + 1]
                if (arr[mid] == arr[mid + 1]) { // Ideal case
                    i = mid + 2; // Two steps forward because same element at next index
                } else {
                    // Don't do j = mid - 1
                    // Because j may point to either second occurrence OR the missing number's first occurrence itself
                    j = mid;
                }
            } else if (mid % 2 != 0) {
                // Mid is at odd index

                //For odd mid index, check only if arr[mid] == arr[mid - 1]
                if (arr[mid] == arr[mid - 1]) { // Ideal case
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
        }

        res = arr[i];
        System.out.println(res);
    }
}
