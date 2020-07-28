package company.walmart2.medium.pending;

import java.util.LinkedList;
import java.util.Queue;

public class StockBuyAndSellToMaximizeProfit {
    //https://www.geeksforgeeks.org/stock-buy-sell/
    public static void main(String[] args) {
        int[] arr = new int[]{100, 180, 260, 310, 40, 535, 695};
        System.out.println(findMaxProfit(arr));
    }

    static class Interval {
        int buy;
        int sell;
    }

    private static int findMaxProfit(int[] arr) {
        int i = 0;
        int n = arr.length;
        Queue<Interval> que = new LinkedList<>();
        while (i < n-1) {
            while (arr[i] > arr[i + 1]) {
                i++;
            }
            if(i == n-1){
                break;
            }
            Interval inter = new Interval();
            inter.buy = i;
            while (arr[i] < arr[i + 1]) {
                i++;
            }
            inter.sell = i;
            que.add(inter);
            i++;
        }
        int profit = 0;
        while (!que.isEmpty()) {
            Interval o = que.poll();
            profit += o.sell - o.buy;
        }
        return profit;
    }
}