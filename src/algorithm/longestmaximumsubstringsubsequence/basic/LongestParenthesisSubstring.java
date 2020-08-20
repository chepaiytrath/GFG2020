package algorithm.longestmaximumsubstringsubsequence.basic;

import java.util.Stack;

public class LongestParenthesisSubstring {
    public static void main(String[] args) {
        String input = "()()((()))(((";
        System.out.println(lengthOfLongestParenthesisWithDynamicProgramming(input));
    }

    private static int lengthOfLongestParenthesis(String input) {
        int maxLength = -1;
        char[] arr = input.toCharArray();
        Stack<Integer> indexStack = new Stack<>();

        indexStack.add(-1);
        for (int i = 0; i < input.length(); i++) {
            if (arr[i] == '(') {
                indexStack.add(i);
            } else {
                indexStack.pop();
                if (!indexStack.isEmpty()) {
                    maxLength = Math.max(maxLength, i - indexStack.peek());
                } else {
                    indexStack.add(i);
                }
            }
        }
        return maxLength;
    }

    private static int lengthOfLongestParenthesisWithDynamicProgramming(String input) {
        int maxans = 0;
        int dp[] = new int[input.length()];
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == ')') {
                if (input.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && input.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}