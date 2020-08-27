package algorithm.basictechniques.leftrightarray;

//https://www.geeksforgeeks.org/equilibrium-index-of-an-array/
public class EquilibriumPoint {
    public static void main(String[] args) {
        long arr[] = new long[]{1, 3, 5, 2, 2};
        int n = 5;
        System.out.print(equilibriumPoint(arr, n));
    }

    public static int equilibriumPoint(long arr[], int n) {
        if (n == 1) {
            return 1;
        }

        // THUMB RULE:
        // PRE POPULATE TWO ARRAYS: LEFT RIGHT
        // LEFT[I] : SUM OF ALL ELEMENTS TOWARDS LEFT OF CURRENT INDEX
        // RIGHT[I] : SUM OF ALL ELEMENTS TOWARDS RIGHT OF CURRENT INDEX
        // IF FOR AN INDEX I : LEFT[I] == RIGHT[I] : THEN THAT INDEX IS THE ANSWER
        long[] left = new long[n];
        long[] right = new long[n];


        // PREPOPULATION
        long cumsum = arr[0];
        left[0] = 0;
        for (int i = 1; i < n; i++) {
            left[i] = cumsum;
            cumsum += arr[i];
        }

        cumsum = arr[n - 1];
        right[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = cumsum;
            cumsum += arr[i];
        }


        // CHECKING FOR SAME VALUES IN BOTH ARRAYS
        for (int i = 0; i < n; i++) {
            if (left[i] == right[i]) {
                // 1 indexed
                return i + 1;
            }
        }
        return -1;
    }
}
