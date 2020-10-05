package algorithm.basictechniques.array;

public class MagicTriplets {
    public static void main(String[] args) {
        countTriplets();
    }

    // Given an array of size n, a triplet (a[i], a[j], a[k]) is called a Magic Triplet
    // if a[i] < a[j] < a[k] and i < j < k.
    // Count the number of magic triplets in a given array.

    //https://practice.geeksforgeeks.org/problems/magic-triplets4003/1
    public static int countTriplets() {
        // Solution : Count numbers less than a[i] on its left and greater than a[i] on its right
        // Multiply them to get all permutations possible with a[i] in the middle and add this count to result

        //int[] arr = new int[]{3, 2, 1};
        int[] arr = new int[]{1, 2, 3, 4};
        // code here
        int result = 0;
        int n = arr.length;

        int i = 1;
        while (i < n - 1) {
            int left = i - 1;
            int right = i + 1;

            // Count numbers less than a[i] on its left
            int leftCount = 0;
            while (left >= 0) {
                if (arr[left] < arr[i]) {
                    leftCount++;
                }
                left--;
            }

            // Count numbers greater than a[i] on its right
            int rightCount = 0;
            while (right < n) {
                if (arr[right] > arr[i]) {
                    rightCount++;
                }
                right++;
            }

            // Multiply them to get all permutations possible and add this count to result
            result += (leftCount * rightCount);
            i++;
        }

        return result;
    }
}
