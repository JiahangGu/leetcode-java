/*
本题给定气球序列，通过射爆气球i获取分数nums[i-1]*nums[i]*nums[i+1]，求解最大的得分结果。需要注意的是，气球i射爆之后，i-1和
i+1变成相邻气球，并在下一次可能参与相应计算。所以[i,j]的序列在射爆中间的第k个气球后，区间变为[i,k-1][k+1,j]，并且随着中间气球
的射爆，最终i,j两个气球相邻。所以问题转化为在[i,j]中求最大得分，最后剩下一个气球k,此时气球序列为i,j,k三个。并且由于边界默认为1，
i,j=1所以最终得分为k。
所以可以得到状态转移方程，假设dp[i][j]表示(i,j)序列能得到的最大得分，i,j开区间，通过在左右边界加上默认的1得到n+2个气球的序列，
最终结果为dp[0][n+1]，并且考虑射爆气球的顺序最终会得到一个状态为除i,j之外只剩一个气球即三个气球，在此状态之前会有除去这三个气球
之外只剩一个气球，即每射爆一个气球就会将该气球划分的两个气球序列合并为一个新的序列。假设每次剩下的气球为k，则射爆k的得分为三个部分，
1是i,k的区间得分dp[i][k]，2是k,j的区间得分dp[k][j]，3是i,j,k三个气球乘积的得分nums[i]*nums[k]*nums[j]。即
dp[i][j] = max(dcoins[i] * coins[k] * coins[j] + dp[i][k] + dp[k][j])  k=i+1,...j-1
注意计算顺序，因为i要得到结果为0，顺序为n->0，j要得到n+1，顺序为i+1->n+1,i+1<=k<=j-1
 */
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int []coins = new int[n + 2];
        coins[0] = coins[n + 1] = 1;
        for (int i = 1;i <= n;i++) {
            coins[i] = nums[i - 1];
        }
        int [][]dp = new int[n + 2][n + 2];
        for (int i = n;i >= 0;i--) {
            for (int j = i + 1;j < n + 2;j++) {
                for (int k = i + 1;k < j;k++) {
                    dp[i][j] = Math.max(dp[i][j], coins[i] * coins[k] * coins[j] + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][n + 1];
    }
}