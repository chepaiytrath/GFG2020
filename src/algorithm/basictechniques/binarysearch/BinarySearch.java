package algorithm.basictechniques.binarysearch;

public class BinarySearch {
    public static void main(String[] args) {
        int[] sortedArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int search = 12;
        int index = binarySearch(sortedArr, search);
        System.out.println(index);
    }

    private static int binarySearch(int[] sortedArr, int search) {
        int lo = 0;
        int hi = sortedArr.length - 1;

        // KEEP BREAKING INTO HALF LIKE A ENGLISH DICTIONARY SEARCH
        while (hi > lo) {
            int mid = lo + (hi - lo) / 2;
            if (sortedArr[mid] == search) {
                return mid;
            } else if (sortedArr[mid] < search) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return -1;
    }

    /*private static int binarySearchWithRecursion(int[] sortedArr, int search) {

    }*/

}

