package algorithm.stack;

import java.util.Stack;

//https://www.geeksforgeeks.org/the-stock-span-problem/
public class StockSpan {
    public static void main(String[] args) {
        int[] arr = new int[]{100, 80, 60, 70, 60, 75, 85};
        //int[] arr = new int[]{10, 50, 80, 70, 80, 100};
        findStockSpanWithStack(arr);
        findStockSpanWithWithDP(arr);
    }

    // Similar to LongestParenthesisSubstring.lengthOfLongestParenthesisWithStack
    private static void findStockSpanWithStack(int[] arr) {
        int n = arr.length;
        Stack<Integer> indexStack = new Stack<>();
        int[] res = new int[n];

        res[0] = 1; // First index has a span of only 1
        indexStack.push(0);

        // Check span for rest of the array : i = 1 to n
        for (int i = 1; i < n; i++) {
            int currNum = arr[i];
            while (!indexStack.isEmpty()
                    && arr[indexStack.peek()] < currNum) {   // Remove from stack all elements on left which are < currnum
                indexStack.pop();
            }
            int span = 0;

            if (indexStack.isEmpty()) {     // i.e. all elements on left were less than currnum
                span = i + 1;
            } else {
                span = i - indexStack.peek();   // Index of last higher number > currnum is at top of stack
            }

            indexStack.push(i);
            res[i] = span;
        }

        for (int val : res) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Similar to LongestParenthesisSubstring.lengthOfLongestParenthesisWithDynamicProgramming
    private static void findStockSpanWithWithDP(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];

        dp[0] = 1;                          // First index has a span of only 1
        for (int i = 1; i < n; i++) {
            int currnum = arr[i];
            int j = i - 1;                  // Compare currnum with elements on left. Use dp[j] to jump across elements already less than arr[j]
            while (j >= 0 && currnum > arr[j]) {
                j = j - dp[j];              // Keep jumping j by dp[j] steps to left
            }
            dp[i] = i - j;
        }

        for (int val : dp) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
