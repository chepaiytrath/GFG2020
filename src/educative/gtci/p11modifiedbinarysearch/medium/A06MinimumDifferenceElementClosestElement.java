package educative.gtci.p11modifiedbinarysearch.medium;

public class A06MinimumDifferenceElementClosestElement {
    public static void main(String[] args) {
        findMinimumDiff();
    }

    // ALSO CALLED ClosestElement
    private static void findMinimumDiff() {
        /*int[] arr = new int[]{1, 3, 8, 10, 15};
        int key = 12;*/

        /*int[] arr = new int[]{4, 6, 4};
        int key = 7;*/

        int[] arr = new int[]{1, 5, 8, 9, 10, 15};
        int key = 7;

        int res = binarySearch(arr, key);
        System.out.println("Minimum Difference Element : " + res);
    }

    private static int binarySearch(int[] arr, int key) {
        if (key >= arr[arr.length - 1]) {
            return arr[arr.length - 1];
        }
        int start = 0;
        int end = arr.length - 1;

        //TO TRACK MIN DIFF VALUE AND CORRESPONDING NUMBER
        int minDiff = Integer.MAX_VALUE;
        int num = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                return key;
            } else if (arr[mid] < key) {
                // CHECK IF CURR NUM IS CLOSEST
                // UPDATE MINDIFF AND NUM
                if (key - arr[mid] < minDiff) {
                    minDiff = key - arr[mid];
                    num = arr[mid];
                }

                start = mid + 1;
            } else if (arr[mid] > key) {
                // CHECK IF CURR NUM IS CLOSEST
                // UPDATE MINDIFF AND NUM
                if (arr[mid] - key < minDiff) {
                    minDiff = arr[mid] - key;
                    num = arr[mid];
                }

                end = mid - 1;
            }
        }
        return num;
    }

}
