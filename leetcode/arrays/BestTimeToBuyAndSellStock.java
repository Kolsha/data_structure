package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-4. todo Kadane's Algorithm
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int profit = 0;
        int min =prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(min > prices[i]) {
                min = prices[i];
            } else {
                int temp = prices[i] - min;
                if(profit < temp) {
                    profit = temp;
                }
            }
        }
        return profit;
    }
}
