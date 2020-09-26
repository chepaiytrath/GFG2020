package algorithm.basictechniques.arraysumcumulativesum.duplettripletsum;

public class A02DupletPairSumPrintAllPairs {
    public static void main(String[] args) {
        int arr[] = {2, 3, 4, -2, 6, 8, 9, 11};
        pairSumPrintAllPairs(arr, 6);
    }

    private static void pairSumPrintAllPairs(int arr[], int sum) {
        java.util.Arrays.sort(arr);
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            if (arr[low] + arr[high] == sum) {
                System.out.println("The pair is : (" + arr[low] + ", " + arr[high] + ")");
            }

            // CAN BE > OR >= : DOESNT MATTER WHO IS INCREMENTED OR DECREMENTED WHEN DUPLET SUM IS EQUAL TO TARGET
            if (arr[low] + arr[high] > sum) {
                high--;
            } else {
                low++;
            }
        }
    }
}
