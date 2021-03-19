class Solution {
    public int numTrees(int n) {
        int []dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1;i <= n;i++) {
            for (int j = 0;j < i / 2;j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
            dp[i] *= 2;
            if ((i & 1) == 1) {
                dp[i] += dp[i / 2] * dp[i / 2];
            }
        }
        return dp[n];
    }
}