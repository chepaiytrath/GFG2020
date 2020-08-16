package educative.gtci.p01slidingwindow.hard;

// #REVISIT
// #DIFFERENTPATTERN
public class A07LongestSubarrayWithOnesAfterReplacement {
    public static void main(String[] args) {
        longestSubstr();
    }

    private static void longestSubstr() {
        int[] arr = {0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1};
        int k = 3;

        int n = arr.length;
        int start = 0;
        int end = 0;
        int count1s = 0;
        int maxLen = 0;

        while (end < n) {
            if (arr[end] == 1) {
                count1s++;
            }
            int windowSize = end + 1 - start;
            if (windowSize - count1s > k) {
                int valAtStart = arr[start];
                if (valAtStart == 1) {
                    count1s--;
                }
                start++;
            }

            maxLen = Math.max(maxLen, end + 1 - start);
            end++;
        }
        System.out.println(maxLen);
    }
}
