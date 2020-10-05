package leetcode.allproblems;

public class A121BestTimeToBuyAndSellStockEverydayZeta {
    public static void main(String[] args) {
        int[] arr = new int[]{11, 18, 15, 19, 22, 15, 18, 22, 15, 19, 14};
        System.out.println(maxProfit(arr));
    }

    private static int maxProfit(int[] arr) {
        int n = arr.length;
        int right = n - 1;
        int i = n - 1;
        int total = 0;
        while (i >= 0) {
            int currSum = 0;
            while (i - 1 >= 0 && arr[i - 1] < arr[right]) {
                i--;
                if (i != right) {
                    currSum += arr[i];
                }
            }
            int rightVal = arr[right];
            total += (rightVal * (right - i)) - currSum;
            i--;
            right = i;
        }
        return total;
    }
}