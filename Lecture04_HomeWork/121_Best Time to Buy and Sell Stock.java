/*
121. Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
    Input: [7, 1, 5, 3, 6, 4]
    Output: 5
max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

Example 2:
    Input: [7, 6, 4, 3, 1]
    Output: 0
In this case, no transaction is done, i.e. max profit = 0.

*/

public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        // Kadane's Algorithm 
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            // calculate the difference of the original array
            // find a contiguous subarray with maximum profit
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    /*
    Time complexity: O(n), n == prices.length;
    Space complexity: O(1)
    */

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        BestTimetoBuyandSellStock test = new BestTimetoBuyandSellStock();
        System.out.println(test.maxProfit(prices));
    }
}