//https://www.geeksforgeeks.org/a-product-array-puzzle/
package algorithm.basictechniques.mathematical;

public class ProductArray {
    public static void main(String[] args) {
        int arr[] = {10, 3, 5, 6, 2};
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
}