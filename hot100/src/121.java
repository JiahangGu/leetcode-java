class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        int holder = -prices[0], sailor = 0;
        for (int i = 1;i < n;i++) {
            sailor = Math.max(sailor, holder + prices[i]);
            holder = Math.max(holder, -prices[i]);
        }
        return sailor;
    }
}