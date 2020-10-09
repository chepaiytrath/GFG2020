package algorithm.basictechniques.array;

public class FindTheMissingNumber {
    public static void main(String[] args) {
        //findTheMissingNumber();
        findTheMissingNumber2();
    }

    // Working for example Missing 5:
    // Ex. 1,2,3,4,6            : A
    //     0,1,2,3,4,5,6        : B
    // Indices work as a reference for actual list which includes the missing number
    // Sum of indices - sum of actual elements will give the missing value


    // For elements : 1 to n
    // Tricky solution to handle overflow of integers
    // #REVISIT
    public static void findTheMissingNumber() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10};

        // CONDITION : PROVIDED THAT THE NUMBERS ARE FROM 1 TO N
        int ans = 1;

        // Start from 2
        for (int i = 2; i <= arr.length + 1; i++) {
            ans += i;
            ans -= arr[i - 2];
        }
        System.out.println("Missing Number is : " + ans);
    }

    // For elements 0 to n
    public static void findTheMissingNumber2() {
        int[] arr = new int[]{1, 3, 0, 4, 2, 8, 6, 5};
        int n = arr.length;

        int res = 0;

        for (int i = 1; i <= n; i++) {
            res += i;
            res -= arr[i - 1];
        }

        System.out.println(res);
    }

    // For Sorted arrays : Both 0 to n and 1 to n
    public static void findTheMissingNumberForSortedArrays() {
        int[] arr = new int[]{1, 3, 0, 4, 2, 8, 6, 5};
        int n = arr.length;

        int res = 0;

        for (int i = 1; i <= n; i++) {
            res += i;
            res -= arr[i - 1];
        }

        System.out.println(res);
    }

}