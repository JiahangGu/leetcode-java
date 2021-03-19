class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0, ans, nums, new ArrayList<Integer>());
        return ans;
    }

    static void dfs(int idx, List<List<Integer>> ans, int []nums, List<Integer> path) {
        if (idx >= nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        dfs(idx + 1, ans, nums, path);
        path.add(nums[idx]);
        dfs(idx + 1, ans, nums, path);
        path.remove(path.size() - 1);
    }
}

//按照长度k=0...n构造子集
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0;i <= nums.length;i++) {
            dfs(0, ans, nums, new ArrayList<Integer>(), i);
        }
        return ans;
    }

    static void dfs(int idx, List<List<Integer>> ans, int []nums, List<Integer> path, int k) {
        if (idx == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx;i < nums.length;i++) {
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            dfs(i + 1, ans, nums, path, k);
            path.remove(path.size() - 1);
        }
    }
}

//按照位运算构造
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0;i < (int) Math.pow(2, nums.length);i++) {
            int idx = nums.length - 1;
            int bits = i;
            List<Integer> tmp = new ArrayList<>();
            while (idx >= 0) {
                if ((bits & 1) == 1) {
                    tmp.add(nums[idx]);
                }
                idx -= 1;
                bits >>= 1;
            }
            ans.add(new ArrayList<>(tmp));
        }
        return ans;
    }
}