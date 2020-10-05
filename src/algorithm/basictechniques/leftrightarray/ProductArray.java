//https://www.geeksforgeeks.org/a-product-array-puzzle/
package algorithm.basictechniques.leftrightarray;

public class ProductArray {
    public static void main(String[] args) {
        int arr[] = {10, 3, 5, 6, 2};
        //int arr[] = {12, 0};
        int result[] = productArray(arr);
        for (int x : result) {
            System.out.print(x + " ");
        }
    }

    private static int[] productArray(int[] arr) {
        int prod[] = new int[arr.length];
        int leftProd[] = new int[arr.length];
        int rightProd[] = new int[arr.length];

        leftProd[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            leftProd[i] = leftProd[i - 1] * arr[i - 1];
        }

        rightProd[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            rightProd[i] = rightProd[i + 1] * arr[i + 1];
        }

        for (int i = 0; i < arr.length; i++) {
            prod[i] = leftProd[i] * rightProd[i];
        }
        return prod;
    }

    // Without using right array
    private static int[] productArrayWithoutExtraArrayOptimized(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        res[0] = 1;

        // Fill res array once from left to right
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * arr[i - 1];
        }

        // Overwrite res array while traversing from right to left
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * arr[i];
        }

        return res;
    }
}