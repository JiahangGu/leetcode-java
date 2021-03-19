//解法1，dp，dp[i]为以nums[i]结尾的最长递增子序列
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        int []dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1;i < n;i++) {
            dp[i] = 1;
            for (int j = 0;j < i;j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

//解法2，贪心，使用一个栈更新不影响结果的最小序列结尾数字，最终该序列长度为结果
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;;
        if (n == 0) return 0;
        int []dp = new int[n + 1];
        int len = 1;
        dp[0] = nums[0];
        for (int i = 1;i < n;i++) {
            int r = len - 1;
            if (dp[r] < nums[i]) {
                dp[len] = nums[i];
                len++;
            }
            else {
                int l = 0;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (dp[mid] < nums[i]) {
                        l = mid + 1;
                    }
                    else {
                        r = mid;
                    }
                }
                dp[l] = nums[i];
            }
        }
        return len;
    }
}