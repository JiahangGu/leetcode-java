/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, sum, 0, map);
    }

    private int dfs(TreeNode root, int target, int currSum, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        currSum += root.val;
        res += map.getOrDefault(currSum - target, 0);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        res += dfs(root.left, target, currSum, map);
        res += dfs(root.right, target, currSum, map);
        map.put(currSum, map.get(currSum) - 1);
        return res;
    }
}