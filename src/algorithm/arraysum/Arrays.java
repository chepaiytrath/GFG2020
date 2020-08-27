package algorithm.arraysum;

public class Arrays {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10};
        findTheMissingNumber(arr);
    }

    // #REVISIT
    public static void findTheMissingNumber(int[] arr) {
        // TRICKY SOLUTION TO HANDLE OVERFLOW OF INTEGER
        // CONDITION : PROVIDED THAT THE NUMBERS ARE FROM 1 TO N
        int ans = 1;
        for (int i = 2; i < arr.length; i++) {
            ans += i;
            ans -= arr[i - 2];
        }
        System.out.println("Missing Number is : " + ans);
    }

    public void mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        // MOVE INDEX OF ARRAY WITH SMALLER ELEMENT
        // IF BOTH EQUAL THEN MOVE BOTH INDEXES

        //SAMPLE INPUT
        // int[] arr1 = { 1, 3, 4, 5 };
        // int[] arr2 = { 2, 4, 6, 8 };

        int i = 0;
        int j = 0;

        int ind = 0;
        Integer[] res = new Integer[arr1.length + arr2.length];
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                res[ind++] = arr1[i++];
            } else if (arr2[j] < arr1[i]) {
                res[ind++] = arr2[j++];
            } else if (arr1[i] == arr2[j]) {
                res[ind++] = arr1[i++];
                res[ind++] = arr2[j++];
            }
        }
        while (i < arr1.length)
            res[ind++] = arr1[i++];

        while (j < arr2.length)
            res[ind++] = arr2[j++];

        System.out.println(java.util.Arrays.asList(res));

        //ANOTHER APPROACH: USE MAP TO SAVE ALL KEYS
    }
}