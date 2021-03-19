/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        int []ans = robMax(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] robMax(TreeNode root) {
        int []ans = new int[2];
        if (root == null) {
            ans[0] = ans[1] = 0;
            return ans;
        }
        int []left = robMax(root.left);
        int []right = robMax(root.right);
        ans[0] = root.val + left[1] + right[1];
        ans[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return ans;
    }
}