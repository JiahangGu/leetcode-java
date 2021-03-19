class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int [][]dp = new int[n][3];
        dp[0][0] = -prices[0];
        dp[0][1] = dp[0][2] = 0;
        for (int i = 1;i < n;i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }
}

//优化空间复杂度为O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int holder = -prices[0], sailor = 0, freeze = 0;
        int x, y, z;
        for (int i = 1;i < n;i++) {
            x = Math.max(holder, sailor - prices[i]);
            y = Math.max(sailor, freeze);
            z = holder + prices[i];
            holder = x;
            sailor = y;
            freeze = z;
        }
        return Math.max(sailor, freeze);
    }
}