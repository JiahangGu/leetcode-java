class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0;
        int ans = nums[0];
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] == ans) {
                cnt += 1;
            }
            else {
                if (cnt > 0) {
                    cnt--;
                }
                else {
                    ans = nums[i];
                    cnt = 1;
                }
            }
        }
        return ans;
    }
}