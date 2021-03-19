class Solution {
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;
        for (int i = 0;i <= p2;i++) {
            if (nums[i] == 0) {
                nums[i] = nums[p0];
                nums[p0] = 0;
                p0++;
            }
            else if (nums[i] == 2) {
                nums[i] = nums[p2];
                nums[p2] = 2;
                p2--;
                i--;
            }
        }
    }
}