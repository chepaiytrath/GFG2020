package company.walmart2.easy;

public class ReverseWordsInString {

    public static void main(String[] args) {
        String input = "I LOVE PROGRAMMING VERY MUCH";
        reverseWords(input);
    }

    private static void reverseWords(String input) {
        char[] arr = input.toCharArray();
        int n = arr.length;
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && arr[j] != ' ') {
                j++;
            }
            if (i == 0 && j == n) {
                break;
            }
            reverse(arr, i, j - 1);
            i = j + 1;
        }
        reverse(arr, 0, n - 1);
        for (char ch : arr) {
            System.out.print(ch + " ");
        }
    }


    private static void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
