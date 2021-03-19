/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//迭代法
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        int midIdx = 0;
        for (int i = 1;i < preorder.length;i++) {
            TreeNode head = stack.peek();
            if (inorder[midIdx] != head.val) {
                TreeNode left = new TreeNode(preorder[i]);
                head.left = left;
                stack.push(left);
            } else {
                while (!stack.empty() && inorder[midIdx] == stack.peek().val) {
                    head = stack.pop();
                    midIdx++;
                }
                TreeNode right = new TreeNode(preorder[i]);
                stack.push(right);
                head.right = right;
            }
        }
        return root;
    }
}

//递归法
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        int rootVal = preorder[0];
        int idx = 0;
        while (idx < inorder.length && inorder[idx] != rootVal) {
            idx++;
        }
        TreeNode left = buildTree(Arrays.copyOfRange(preorder, 1, idx + 1), Arrays.copyOfRange(inorder, 0, idx));
        TreeNode right = buildTree(Arrays.copyOfRange(preorder, idx + 1, preorder.length), Arrays.copyOfRange(inorder, idx + 1, inorder.length));
        TreeNode root = new TreeNode(rootVal, left, right);
        return root;
    }
}