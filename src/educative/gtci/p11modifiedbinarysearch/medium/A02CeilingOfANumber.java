package educative.gtci.p11modifiedbinarysearch.medium;

public class A02CeilingOfANumber {
    public static void main(String[] args) {
        binarySearch();
    }

    private static void binarySearch() {
        int[] arr = new int[]{1, 4, 9, 11, 15, 20, 30};
        int ceilOf = 8;

        int res = binarySearchUtil(arr, ceilOf);
        System.out.println(res);
    }

    // RULE: AFTER BINARY SEARCH, SMALLEST ELEMENT GREATER OR EQUAL TO TARGET IS AT START
    private static int binarySearchUtil(int[] arr, int ceilOf) {
        if(ceilOf > arr[arr.length - 1]){
            // CANT FIND AN ELEMENT >= TARGET IF LAST ELEMENT(HIGHEST) IS LESS THAN TARGET
            return -1;
        }
        int start = -1;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == ceilOf) {
                return mid;
            } else if (arr[mid] < ceilOf) {
                start = mid + 1;
            } else {
                end = end - 1;
            }
        }
        return start;
    }
}
