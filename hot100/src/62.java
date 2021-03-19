class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0;i < n;i++) {
            dp[i] = 1;
        }
        for (int i = 1;i < m;i++) {
            for (int j = 1;j < n;j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n - 1];
    }
}

//组合数学,n!/m!(n-m)!可以写作x=m+1,y=1...n-m
class Solution {
    public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1;y < m;x++, y++) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
}