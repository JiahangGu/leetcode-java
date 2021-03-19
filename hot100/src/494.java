//递归做法，时间复杂度O(2^n)
class Solution {
    int ans;
    public int findTargetSumWays(int[] nums, int S) {
        ans = 0;
        dfs(nums, 0, 0, S);
        return ans;
    }

    private void dfs(int []nums, int idx, int sum, int target) {
        if (idx == nums.length) {
            if (target == sum) {
                ans++;
            }
            return;
        }
        dfs(nums, idx + 1, sum + nums[idx], target);
        dfs(nums, idx + 1, sum - nums[idx], target);
    }
}

//dp，复杂度O(n*2000)
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int[][] dp = new int[n][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000]++;
        for (int i = 1; i < n; i++) {
            for (int j = -1000; j <= 1000; j++) {
                if (dp[i - 1][j + 1000] > 0) {
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000];
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[n - 1][S + 1000];
    }
}

//进一步优化，上限为O(sum*n)，不需要遍历-1000~1000的范围，根据题目优化时间。
//将数组拆分为两部分x和y，因为sum < S则需要一部分为负数假设为y，则x+y=sum, x-y=S。则x=(sum + S) / 2，即找出数组可以组合
//得到和为x的方案数，则可以由另一部分添加负号构成y得到和为S。
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (((sum + S) & 1) == 1) {
            return 0;
        }
        if (S > sum) {
            return 0;
        }
        int len = (S + sum) / 2;
        int []dp = new int[len + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = len;j >= num;j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[len];
    }
}