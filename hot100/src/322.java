class Solution {
    public int coinChange(int[] coins, int amount) {
        int []dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1;i <= amount;i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int coin : coins) {
            for (int i = 1;i <= amount;i++) {
                if (i >= coin && dp[i - coin] < Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] < Integer.MAX_VALUE ? dp[amount] : -1;
    }
}