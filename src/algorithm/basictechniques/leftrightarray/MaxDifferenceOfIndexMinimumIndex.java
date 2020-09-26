package algorithm.basictechniques.leftrightarray;

// #REVISIT
// https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
public class MaxDifferenceOfIndexMinimumIndex {

    public static void main(String[] args) {
        int n = 9;
        int[] arr = {34, 8, 10, 3, 2, 80, 30, 33, 1};
        System.out.println(maxIndexDiff(arr, n));
    }

    // Function to find maximum difference of j-i
    // arr[]: input array
    // n: size of array

    // DRAW BOTH ARRAYS ON WHITEBOARD, WILL UNDERSTAND
    private static int maxIndexDiff(int arr[], int n) {
        // LEFT[I]: SMALLEST VALUE FROM LEFT TO RIGHT INCLUDING I
        int[] left = new int[n];
        left[0] = arr[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.min(arr[i], left[i - 1]);
        }

        // RIGHT[I]: LARGEST VALUE FROM RIGHT TO LEFT INCLUDING I
        int[] right = new int[n];
        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(arr[i], right[i + 1]);
        }

        int i = 0;
        int j = 0;

        int maxdiff = -1;
        // INCREMENT J TILL RIGHT[J] >= LEFT[I]
        // WHEN RIGHT[J] < LEFT[I]: THEN INCREMENT I ONE BY ONE
        while (i < n && j < n) {
            // >= to satisfy edge case
            if (right[j] >= left[i]) {
                maxdiff = Math.max(maxdiff, j - i);
                j++;
            } else {
                i++;
            }
        }
        return maxdiff;
    }
}