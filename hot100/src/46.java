class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, 0, nums);
        return ans;
    }

    static void dfs(List<List<Integer>> ans, int idx, int []nums) {
        if (idx >= nums.length) {
            List<Integer> path = new ArrayList<>();
            for (int i = 0;i < nums.length;i++) {
                path.add(nums[i]);
            }
            ans.add(path);
            return;
        }
        for (int i = idx;i < nums.length;i++) {
            swap(nums, i, idx);
            dfs(ans, idx + 1, nums);
            swap(nums, i, idx);
        }
    }

    static void swap(int []nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}