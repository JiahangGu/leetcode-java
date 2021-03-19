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
/*
 这是我初始的做法，因为理解错题意（我的理解是求出所有联通分量得到的最大和），而题目要求是一条路径，即左右子树只能选一个作为
 */
class Solution {
    public int maxPathSum(TreeNode root) {
        int []ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        }
        int []ans = new int[2];
        int []left = dfs(root.left);
        int []right = dfs(root.right);
        ans[0] = root.val + Math.max(left[0], 0) + Math.max(right[0], 0);
        ans[1] = Math.max(Math.max(left[0], left[1]), Math.max(right[0], right[1]));
        return ans;
    }
}


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
/*
 明确题意后，上述想法有部分可取的地方，例如计算左右子树可以得到的最大和，并连接根节点作为结果。
 正确做法是，当前节点root可以作为结果路径所经过的根节点，此时应该加上左右子树能达到的最大和更新结果。而在向上返回时，由于只能有
 一条分支，所以从左右子树中选择较大的一方累加和并返回。
 */
class Solution {
    private int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        int rootMax = root.val + left + right;
        ans = Math.max(ans, rootMax);
        return root.val + Math.max(left, right);
    }
}