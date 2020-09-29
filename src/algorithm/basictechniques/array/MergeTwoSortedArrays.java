package algorithm.basictechniques.array;

public class MergeTwoSortedArrays {
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
