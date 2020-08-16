package educative.gtci.p01slidingwindow.easy;

public class A02SmallestSubarrayWithAGivenSum {
    public static void main(String[] args) {
        smallestSubarray();
    }

    private static void smallestSubarray() {
        int[] arr = {3, 4, 1, 1, 6};
        int S = 8;

        int n = arr.length;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int windowSum = 0;
        // Compared to below solution, I traverse to last index + 1 (i.e. n -> arr.length)
        while (end <= n) {
            // First decrease the window
            while (windowSum >= S) {
                minLen = Math.min(minLen, end - start);
                windowSum -= arr[start];
                start++;
            }
            //Then add the sum
            // Need an extra check to prevent AIOOB
            if (end < n) {
                windowSum += arr[end];
            }
            end++;
        }
        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }

    private static void smallestSubarrayFromTheLecture() {
        int[] arr = {3, 4, 1, 1, 6};
        int S = 8;

        int n = arr.length;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int windowSum = 0;
        while (end < n) {
            windowSum += arr[end];

            while (windowSum >= S) {
                minLen = Math.min(minLen, end - start + 1);
                windowSum -= arr[start];
                start++;
            }
            end++;
        }
        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }
}
