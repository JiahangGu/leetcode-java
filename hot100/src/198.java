class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int robbed = 0, notRob = 0, x;
        for (int i = 0;i < n;i++) {
            x = robbed;
            robbed = Math.max(robbed, notRob + nums[i]);
            notRob = x;
        }
        return robbed;
    }
}