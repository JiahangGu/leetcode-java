class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int []prefix = new int[n + 2];
        int []postfix = new int[n + 2];
        prefix[0] = prefix[n + 1] = postfix[0] = postfix[n + 1] = 1;
        for (int i = 0;i < n;i++) {
            prefix[i + 1] = prefix[i] * nums[i];
        }
        for (int i = n;i > 0;i--) {
            postfix[i] = postfix[i + 1] * nums[i - 1];
        }
        int []ans = new int[n];
        for (int i = 1;i <= n;i++) {
            ans[i - 1] = prefix[i - 1] * postfix[i + 1];
        }
        return ans;
    }
}

//空间复杂度优化到O(1)
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int []ans = new int[n];
        ans[0] = 1;
        for (int i = 1;i < n;i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int r = 1;
        for (int i = n - 1;i >= 0;i--) {
            ans[i] *= r;
            r *= nums[i];
        }
        return ans;
    }
}