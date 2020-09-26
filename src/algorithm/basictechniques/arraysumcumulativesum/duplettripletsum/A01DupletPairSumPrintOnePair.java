package algorithm.basictechniques.arraysumcumulativesum.duplettripletsum;

import java.util.HashSet;

public class A01DupletPairSumPrintOnePair {
    public static void main(String args[]) {
        int A[] = {1, 4, 45, 6, 10, -8};
        int n = 16;
        int arr_size = A.length;

        // Function calling
        if (pairSumPrintOnePairWithTwoPointerSorting(A, arr_size, n))
            System.out.println("Array has two "
                    + "elements with given sum");
        else
            System.out.println("Array doesn't have "
                    + "two elements with given sum");
    }

    private static boolean pairSumPrintOnePairWithTwoPointerSorting(int A[], int arr_size, int sum) {
        int l, r;

        /* Sort the elements */
        java.util.Arrays.sort(A);

        /* Now look for the two candidates
        in the sorted array*/
        l = 0;
        r = arr_size - 1;
        while (l < r) {
            if (A[l] + A[r] == sum)
                return true;
            else if (A[l] + A[r] < sum)
                l++;
            else // A[i] + A[j] > sum
                r--;
        }
        return false;
    }

    private static void pairSumPrintOnePairWithTwoPointerHashing(int arr[], int sum)
    {
        HashSet<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < arr.length; ++i) {
            int temp = sum - arr[i];

            // checking for condition
            if (s.contains(temp)) {
                System.out.println(
                        "Pair with given sum "
                                + sum + " is (" + arr[i]
                                + ", " + temp + ")");
            }
            s.add(arr[i]);
        }
    }
}