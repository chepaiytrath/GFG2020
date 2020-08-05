package algorithm.arraysum;

public class PairWithSumClosestToTargetButNotGreater {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 10, 12, 27, 32, 39, 50, 67, 86};
        int k = 77;
        System.out.println(findPairWithClosestSumLessThanEqualTarget(arr, k));
    }

    private static int findPairWithClosestSumLessThanEqualTarget(int[] arr, int k) {
        int n = arr.length;
        int i = 0;
        int j = n - 1;
        int minDiff = Integer.MAX_VALUE;
        int num = -1;

        while (i < n && j >= 0) {
            int sum = arr[i] + arr[j];
            int diff = k - sum;
            if (diff < 0) {
                j--;
                continue;
            }
            if (diff == 0) {
                return Math.max(arr[i], arr[j]);
            }
            if (diff < minDiff) {
                num = Math.max(arr[i], arr[j]);
                minDiff = diff;
                i++;
            }
        }
        return num;
    }
}
