package educative.gtci.p11modifiedbinarysearch.medium;

public class A05SearchInASortedInfiniteArray {
    public static void main(String[] args) {
        searchInSortedInfiniteArray();
    }

    // Main problem isn't how to search but where to search
    // Odd/even size of backend array doesnt matter
    // Search in first 2 elements, then next 4 elements, then next 8 elements
    // Just need to find thi range to search elements in
    private static void searchInSortedInfiniteArray() {
        /*int[] input = new int[]{4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
        int key = 16;*/
        /*int[] input = new int[]{4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
        int key = 11;*/
        /*int[] input = new int[]{1, 3, 8, 10, 15};
        int key = 15;*/
        int[] input = new int[]{1, 3, 8, 10, 15};
        int key = 200;
        ArrayReader arr = new ArrayReader(input);

        int start = 0;
        int end = 1;
        while (arr.get(end) < key) {
            int tempStart = start;
            start = end + 1;
            // (end - tempStart + 1) * 2; : MEANS 1 -> 2 -> 4 -> 8 ...
            // INITIALLY START = 0 AND END = 1 : SO LENGTH = 2
            // FOR NEXT BLOCK, DOUBLE THIS LENGTH : 2 * 2 = 4
            // NEW END = OLD END + DOUBLE LENGTH : 1 + 4 = 5
            end = end + (end - tempStart + 1) * 2;
        }
        int index = binarySearch(arr, key, start, end);
        System.out.println("FOUND ELEMENT AT : " + index);
    }

    private static int binarySearch(ArrayReader arr, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr.get(mid) == key) {
                return mid;
            } else if (arr.get(mid) < key) {
                start = mid + 1;
            } else if (arr.get(mid) > key) {
                end = mid - 1;
            }
        }
        return -1;
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
