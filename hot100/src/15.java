class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length <= 2) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0;i + 2 < nums.length;i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = 0 - nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[l]);
                    temp.add(nums[r]);
                    ans.add(temp);
                    l += 1;
                    while (l < nums.length && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    r -= 1;
                    while (r > 0 && nums[r] == nums[r + 1]) {
                        r--;
                    }
                }
                else if (nums[l] + nums[r] < target) {
                    l++;
                }
                else {
                    r--;
                }
            }
        }
        return ans;
    }
}