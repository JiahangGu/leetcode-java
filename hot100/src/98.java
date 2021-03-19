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
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = root;
        long pre = -2147483649l;
        while (!stack.empty() || head != null) {
            while (head != null) {
                stack.add(head);
                head = head.left;
            }
            head = stack.pop();
            if (pre >= head.val) {
                return false;
            }
            pre = head.val;
            head = head.right;
        }
        return true;
    }
}