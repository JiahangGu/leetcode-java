/**
 * 做这道题时，起初的想法是假设dp[i][j]表示word1[0,i]转化为word2[0,j]所需要的最少操作，乍看有理，但在分析状态转移方程时
 * 卡在了如何删和插字符的操作上，因为这两个操作会引起字符长度和当前索引的变化。
 * 看完题解之后，觉得解决问题的关键其实就是这一句话：在A字符串的删除操作等于在B字符串的插入操作，即要求对A进行修改，但其实可同理
 * 对B进行反操作（插入）达到同样的效果。所以现在的操作转变为1.A插入，2.B插入以及3.修改字符。
 * 这样的话，有如下三种情况：
 * 1. 对A插入字符，如果horse到ro的距离为a，则horse到ro的距离<=a+1，因为通过a步转化到ro之后插入一个字符即可。
 * 2. 对B插入字符，如果hors到ros的距离为b，则horse到ros的距离<=b+1
 * 3. 修改A字符，如果hors到ro的距离为c，则horse到ros距离<=c+1
 * 所以可以得到dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1，分别表示对A插入，对B插入和修改，并且如果
 * word1[i] == word2[j]则修改不需要+1。
 * 边界条件是其中一个字符串为空，那么距离为另一字符串的长度，可以理解为全部插入。
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m * n == 0) {
            return m + n;
        }
        int [][]dp = new int[m + 1][n + 1];
        for (int i = 0;i <= m;i++) {
            dp[i][0] = i;
        }
        for (int j = 1;j <= n;j++) {
            dp[0][j] = j;
        }
        for (int i = 1;i <= m;i++) {
            for (int j = 1;j <= n;j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}