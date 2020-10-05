package leetcode.allproblems;

import java.util.LinkedList;
import java.util.Queue;

public class A0122BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        //int[] arr = new int[]{7, 1, 5, 3, 6, 4}; // 7
        int[] arr = new int[]{100, 180, 260, 310, 40, 535, 695}; // 865
        //int[] arr = new int[]{23, 13, 25, 29, 33, 19, 34, 45, 65, 67}; // 68
        //int[] arr = new int[]{50,40,30,20,10};

        System.out.println(maxProfit(arr));
    }

    private static int maxProfit(int[] arr) {
        int n = arr.length;
        int maxProfit = 0;  // Default is no profit

        int buyPrice = -1;
        int sellPrice = -1;

        boolean buy = true; // First action is to buy
        int i = 0;
        while (i < n) {
            if (buy) {
                // Increment i till sequence is decreasing
                while (i + 1 < n && arr[i + 1] < arr[i]) { // Iterate till you find the best buying price
                    i++;
                }
                buyPrice = arr[i];  // Found the current best buying price
                buy = false;
                i++;    // Go to next index and start finding the best selling price
            } else {
                // Increment i till sequence is increasing
                while (i + 1 < n && arr[i + 1] > arr[i]) {  // Iterate till you find the best selling price
                    i++;
                }
                sellPrice = arr[i]; // Found the current best selling price

                System.out.println("Buy at " + buyPrice + " and Sell at " + sellPrice);
                maxProfit += sellPrice - buyPrice;  // Closure of selling at the best price

                i++;    // Go to next index and start finding the best buying price
                buy = true;
            }
        }
        return maxProfit;
    }


    static class Interval {
        int buy;
        int sell;
    }

    // Using collection of Intervals
    private static int findMaxProfit(int[] arr) {
        int i = 0;
        int n = arr.length;
        Queue<Interval> que = new LinkedList<>();
        while (i < n - 1) {
            while (i < n - 1 && arr[i] > arr[i + 1]) {
                i++;
            }
            if (i == n - 1) {
                break;
            }
            Interval inter = new Interval();
            inter.buy = i;
            while (i < n - 1 && arr[i] < arr[i + 1]) {
                i++;
            }
            inter.sell = i;
            que.add(inter);
            i++;
        }
        int profit = 0;
        while (!que.isEmpty()) {
            Interval o = que.poll();
            profit += arr[o.sell] - arr[o.buy];
        }
        return profit;
    }
}