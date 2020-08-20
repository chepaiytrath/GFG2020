package educative.gtci.p11modifiedbinarysearch.medium;

public class A03NextLetter {
    public static void main(String[] args) {
        findNextLetter();
    }

    private static void findNextLetter() {
        String str = "acfh";
        char[] arr = str.toCharArray();
        char key = 'c';
        char res = binarySearch(arr, key);
        System.out.println(res);
    }

    private static char binarySearch(char[] arr, char key) {
        int n = arr.length;
        if (key < arr[0] || key > arr[arr.length - 1]) {
            return arr[0];
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= key) {
                // BECAUSE WE WANT SMALLEST ELEMENT GREATER THAN KEY
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return arr[start % n];
    }


}
