class Solution {
    public boolean canJump(int[] nums) {
        int maxStep = 0;
        for (int i = 0;i < nums.length;i++) {
            if (maxStep >= nums.length - 1) {
                return true;
            }
            if (i == maxStep && nums[i] == 0) {
                return false;
            }
            maxStep = Math.max(maxStep, nums[i] + i);
        }
        return true;
    }
}