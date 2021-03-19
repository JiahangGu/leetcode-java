class Solution {
    public void nextPermutation(int[] nums) {
        int idx = nums.length - 2;
        while (idx >= 0 && nums[idx] >= nums[idx + 1]) {
            idx -= 1;
        }
        if (idx >= 0) {
            int i = nums.length - 1;
            while (i > idx && nums[i] <= nums[idx]) {
                i--;
            }
            swap(nums, idx, i);
        }
        idx++;
        int r = nums.length - 1;
        while (idx < r) {
            swap(nums, idx, r);
            idx++;
            r--;
        }
    }

    static void swap(int []nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}