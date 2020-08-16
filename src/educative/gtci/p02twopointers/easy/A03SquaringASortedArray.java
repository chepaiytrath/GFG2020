package educative.gtci.p02twopointers.easy;

public class A03SquaringASortedArray {
    public static void main(String[] args) {
        squareArray();
    }

    private static void squareArray() {
//        int[] arr = new int[]{-2, -1, 0, 2, 3};
        int[] arr = new int[]{-3, -1, 0, 1, 2};
        int n = arr.length;
        int[] res = new int[n];

        int left = 0;
        int right = n - 1;
        int ptr = n - 1;

        while (left < right) {
            int leftSq = arr[left] * arr[left];
            int rightSq = arr[right] * arr[right];

            if (rightSq >= leftSq) {
                res[ptr] = rightSq;
                ptr--;
                right--;
            } else if (leftSq > rightSq) {
                res[ptr] = leftSq;
                ptr--;
                left++;
            }
        }
        if (ptr == 0) {
            arr[ptr] = arr[left] * arr[left];
        }

        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
