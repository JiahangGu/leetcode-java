class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int []iMax = new int[n];
        int []iMin = new int[n];
        iMax[0] = iMin[0] = nums[0];
        int ans = nums[0];
        for (int i = 1;i < n;i++) {
            iMax[i] = Math.max(nums[i], Math.max(iMax[i - 1] * nums[i], iMin[i - 1] * nums[i]));
            iMin[i] = Math.min(nums[i], Math.min(iMin[i - 1] * nums[i], iMax[i - 1] * nums[i]));
            ans = Math.max(ans, iMax[i]);
        }
        return ans;
    }
}

//优化空间
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int iMax = nums[0];
        int iMin = nums[0];
        int ans = nums[0];
        for (int i = 1;i < n;i++) {
            int mx = iMax, mn = iMin;
            iMax = Math.max(nums[i], Math.max(mx * nums[i], mn * nums[i]));
            iMin = Math.min(nums[i], Math.min(mn * nums[i], mx * nums[i]));
            ans = Math.max(ans, iMax);
        }
        return ans;
    }
}