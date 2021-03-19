class Solution {
    public int[] searchRange(int[] nums, int target) {
        int []ans = {-1, -1};
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        if (nums.length == 0 || nums[l] != target) {
            return ans;
        }
        ans[0] = l;
        l = 0;
        r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] <= target) {
                l = mid;
            }
            else {
                r = mid - 1;
            }
        }
        ans[1] = l;
        return ans;
    }
}