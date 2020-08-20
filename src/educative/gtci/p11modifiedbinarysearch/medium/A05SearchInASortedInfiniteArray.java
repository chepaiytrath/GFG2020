package educative.gtci.p11modifiedbinarysearch.medium;

public class A05SearchInASortedInfiniteArray {
    public static void main(String[] args) {
        searchInSortedInfiniteArray();
    }

    private static void searchInSortedInfiniteArray() {
        int[] input = new int[]{4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
        int key = 16;
        ArrayReader arr = new ArrayReader(input);

        binarySearch(arr, key);
    }

    private static void binarySearch(ArrayReader arr, int key) {

    }


    static class ArrayReader {
        private int[] arr;

        ArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int get(int index) {
            if (index >= arr.length) {
                return Integer.MAX_VALUE;
            } else {
                return arr[index];
            }
        }
    }
}
