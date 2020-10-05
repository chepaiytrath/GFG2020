package algorithm.basictechniques.array;

public class FindTheMissingNumber {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10};
        findTheMissingNumber(arr);
    }


    // Working for example Missing 5:
    // Ex. 1,2,3,4,6            : A
    //     0,1,2,3,4,5,6        : B
    // Indices work as a reference for actual list which includes the missing number
    // Add 1 2 from B and subtract 1 from A = 2 -> Missing in case of 1 2 and 1
    // Add last diff 2 and 3 from B and subtract 2 from A = 3 -> Missing in case of 1 2 3 and 1 2
    // so on and so forth

    // TRICKY SOLUTION TO HANDLE OVERFLOW OF INTEGER
    // #REVISIT
    public static void findTheMissingNumber(int[] arr) {
        //int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10};

        // CONDITION : PROVIDED THAT THE NUMBERS ARE FROM 1 TO N
        int ans = 1;

        // Start from 2
        for (int i = 2; i <= arr.length + 1; i++) {
            ans += i;
            ans -= arr[i - 2];
        }
        System.out.println("Missing Number is : " + ans);
    }
}