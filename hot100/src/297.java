/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
 关键在于保存数的结构，可以随意选取一种遍历方式，在逆序列话时恢复即可。难点在于叶子节点的保存（只有正确识别叶子节点才能恢复树的结构），
 对于叶子节点可以设定其子节点为null并特殊标记，在逆序列化时如果识别出null则构造空节点用于构造叶节点。
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializeFunc(root, "");
    }

    private String serializeFunc(TreeNode root, String str) {
        if (root == null) {
            str += "null,";
        }
        else {
            str += String.valueOf(root.val) + ",";
            str = serializeFunc(root.left, str);
            str = serializeFunc(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings = data.split(",");
        List<String> s = new ArrayList<String>(Arrays.asList(strings));
        return deserializeFunc(s);
    }

    private TreeNode deserializeFunc(List<String> strings) {
        if (strings.get(0).equals("null")) {
            strings.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(strings.get(0)));
        strings.remove(0);
        root.left = deserializeFunc(strings);
        root.right = deserializeFunc(strings);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));