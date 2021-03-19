class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(ans, 0, 0, path, target, candidates);
        return ans;
    }

    static void dfs(List<List<Integer>> ans, int idx, int sum, List<Integer> path, int target, int[] candidates) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = idx;i < candidates.length;i++) {
            sum += candidates[i];
            path.add(candidates[i]);
            dfs(ans, i, sum, path, target, candidates);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}