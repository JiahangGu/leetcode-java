class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int [][]dp = new int[m][n];
        int ans = 0;
        for (int i = 0;i < m;i++) {
            dp[i][0] = matrix[i][0] - '0';
            ans = Math.max(ans, dp[i][0]);
        }
        for (int i = 0;i < n;i++) {
            dp[0][i] = matrix[0][i] - '0';
            ans = Math.max(ans, dp[0][i]);
        }
        for (int i = 1;i < m;i++) {
            for (int j = 1;j < n;j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans * ans;
    }
}