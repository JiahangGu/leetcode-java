class Solution {
    public int[] twoSum(int[] nums, int target) {
        int []s = new int[2];
        for (int i = 0;i < nums.length;i++) {
            s[0] = i;
            for (int j = i + 1;j < nums.length;j++) {
                if (nums[i] + nums[j] == target) {
                    s[1] = j;
                    return s;
                }
            }
        }
        return s;
    }
}