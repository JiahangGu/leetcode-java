class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, maxNum = 0;
        int n = nums.length;
        if (n < 2) return false;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean [][]dp = new boolean[n][target + 1];
        for (int i = 0;i < n;i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1;i < n;i++) {
            for (int j = 1;j <= target;j++) {
                int num = nums[i];
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }
}

//空间复杂度优化到O(1)，注意这里dp[i]是整数能否凑到i，所以dp[0]=true因为不需要凑
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, maxNum = 0;
        int n = nums.length;
        if (n < 2) return false;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) return false;
        boolean []dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1;i < n;i++) {
            for (int j = target;j >= nums[i];j--) {
                dp[j] = dp[j] | dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}