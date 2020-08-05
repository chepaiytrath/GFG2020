package algorithm.arraysum;

public class PairSumPrintAllPairs {
    public static void main(String[] args) {
        int arr[] = {2, 3, 4, -2, 6, 8, 9, 11};
        java.util.Arrays.sort(arr);
        pairSumPrintAllPairs(arr, 6);
    }

    private static void pairSumPrintAllPairs(int arr[], int sum) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            if (arr[low] + arr[high] == sum) {
                System.out.println("The pair is : ("
                        + arr[low] + ", " + arr[high] + ")");
            }
            if (arr[low] + arr[high] > sum) {
                high--;
            } else {
                low++;
            }
        }
    }
}
