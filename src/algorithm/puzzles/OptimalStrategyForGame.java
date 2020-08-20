package algorithm.puzzles;

import java.util.HashMap;
import java.util.Map;

public class OptimalStrategyForGame {
    //ALSO CALLED POTS OF GOLD PROBLEM
    public static void main(String[] args) {
        int arr[] = {20, 30, 2, 2, 2, 10};
        Map<String, Integer> dp = new HashMap<>();
        System.out.println(findBestSolution(arr, 0, arr.length - 1, dp));
    }

    private static int findBestSolution(int[] arr, int left, int right, Map<String, Integer> dp) {
        if (dp.containsKey(left + "|" + right)) {
            return dp.get(left + "|" + right);
        }
        if (right == left + 1) {
            return Math.max(arr[left], arr[right]);
        }
        // EITHER CHOOSE LEFT AND FIND BEST IN REMAINING ARRAY
        // FINDING BEST: PLAYER 2 ALSO PLAYS OPTIMALLY: SO PLAYER 1 IS FORCED TO SELECT THE MINIMUM OF THE TWO OPTIONS
        int choiceLeft = arr[left] + Math.min(findBestSolution(arr, left + 2, right, dp), findBestSolution(arr, left + 1, right - 1, dp));

        // OR CHOOSE RIGHT AND FIND BEST IN REMAINING ARRAY
        // FINDING BEST: PLAYER 2 ALSO PLAYS OPTIMALLY: SO PLAYER 1 IS FORCED TO SELECT THE MINIMUM OF THE TWO OPTIONS
        int choiceRight = arr[right] + Math.min(findBestSolution(arr, left, right - 2, dp), findBestSolution(arr, left + 1, right - 1, dp));

        // ANSWER = MAXIMUM OF THE TWO CHOICES: LEFT OR RIGHT
        int ans = Math.max(choiceLeft, choiceRight);

        // SAVE INDICES OF LEFT RIGHT IN DP FOR FUTURE USE IN CASE SOME OTHER RECURSIVE CALL NEEDS SAME VALUES
        dp.put(left + "|" + right, ans);
        return ans;
    }
}
