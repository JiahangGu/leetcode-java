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
    public void flatten(TreeNode root) {
        TreeNode tmp, head = root;
        while (head != null) {
            if (head.left == null) {
                head = head.right;
                continue;
            }
            tmp = head.left;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
            tmp.right = head.right;
            head.right = head.left;
            head.left = null;
            head = head.right;
        }
    }
}