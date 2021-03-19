/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//寻找路径，找到最后一个相同的节点作为答案
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> stackP = new ArrayList<>();
        List<TreeNode> stackQ = new ArrayList<>();
        dfs(root, p, stackP);
        dfs(root, q, stackQ);
        int i = 0;
        for (i = 0;i < stackP.size() && i < stackQ.size();i++) {
            if (stackP.get(i).val != stackQ.get(i).val) {
                return stackP.get(i - 1);
            }
        }
        return stackQ.get(i - 1);
    }

    private boolean dfs(TreeNode root, TreeNode node, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root.equals(node)) {
            return true;
        }
        if (dfs(root.left, node, path)) {
            return true;
        }
        if (dfs(root.right, node, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}

//递归解法，判断root左右子树是否包含pq，如果同时包含，或者一方包含但root本身就是p或q，记录root为解，返回值为root所在子树是否包含
//p或者q其中一个
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
    TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        if ((left && right) || ((root.equals(p) || root.equals(q)) && (left || right))) {
            ans = root;
        }
        return (left || right || root.equals(p) || root.equals(q));
    }
}

//递归解法，如果同时包含则直接返回root，如果其中一个子树不包含pq则返回另一子树的寻找结果.
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        else if (left == null) {
            return right;
        }
        else if (right == null) {
            return left;
        }
        else {
            return root;
        }
    }
}