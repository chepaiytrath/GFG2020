package spacedrepetition;

public class SumOfMiddleElementsOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 4, 6, 10};
        int[] arr2 = new int[]{4, 5, 6, 9, 12};

        System.out.println(findSum(arr1, arr2));

    }

    private static int findSum(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        int n = arr1.length;


        int resLength = n + 1;
        int[] res = new int[resLength];
        int idx = 0;

        while (i < n && j < n) {
            if (arr1[i] <= arr2[j]) {
                res[idx] = arr1[i];
                i++;
            } else {
                res[idx] = arr2[j];
                j++;
            }
            idx++;
            if (idx == resLength) {
                break;
            }
        }
        return res[resLength - 1] + res[resLength - 2];
    }
}
