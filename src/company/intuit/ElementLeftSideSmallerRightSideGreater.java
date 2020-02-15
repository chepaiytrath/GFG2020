//https://www.geeksforgeeks.org/find-the-element-before-which-all-the-elements-are-smaller-than-it-and-after-which-all-are-greater-than-it/
package company.intuit;

public class ElementLeftSideSmallerRightSideGreater {
    public static void main(String[] args) {
        int arr[] = {5, 1, 4, 3, 6, 8, 10, 7, 9};
        int result = elementLeftSideSmallerRightSideGreater(arr);
        System.out.println(result);
    }

    private static int elementLeftSideSmallerRightSideGreater(int[] arr) {
        int leftMaxArr[] = getLeftMaxArr(arr);
        int rightMinArr[] = getRightMinArr(arr);

        for (int i = 0; i < arr.length; i++) {
            if(leftMaxArr[i] < arr[i] && rightMinArr[i] > arr[i]){
                return i;
            }
        }
        return -1;
    }

    private static int[] getLeftMaxArr(int[] arr) {
        int leftMaxArr[] = new int[arr.length];
        int max = Integer.MIN_VALUE;
        leftMaxArr[0] = max;
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i - 1]);
            leftMaxArr[i] = max;
        }
        return leftMaxArr;
    }

    private static int[] getRightMinArr(int[] arr) {
        int rightMinArr[] = new int[arr.length];
        int min = Integer.MAX_VALUE;
        rightMinArr[arr.length - 1] = min;
        for (int i = arr.length - 2; i >= 0; i--) {
            min = Math.min(min, arr[i + 1]);
            rightMinArr[i] = min;
        }
        return rightMinArr;
    }
}