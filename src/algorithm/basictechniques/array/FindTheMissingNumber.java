package algorithm.basictechniques.array;

public class FindTheMissingNumber {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10};
        findTheMissingNumber(arr);
    }

    // TRICKY SOLUTION TO HANDLE OVERFLOW OF INTEGER
    // #REVISIT
    public static void findTheMissingNumber(int[] arr) {
        //int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10};

        // CONDITION : PROVIDED THAT THE NUMBERS ARE FROM 1 TO N
        int ans = 1;
        for (int i = 2; i < arr.length; i++) {
            ans += i;
            ans -= arr[i - 2];
        }
        System.out.println("Missing Number is : " + ans);
    }
}