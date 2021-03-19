import com.sun.org.apache.bcel.internal.generic.LNEG;

import java.util.*;

class Solution {
    int ans;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        ans = 0;
        dfs(root, sum, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode root, int target, List<Integer> path) {
        if (root == null) {
            ans += getNum(path, target);
            return;
        }
        path.add(root.val);
        dfs(root.left, target, path);
        dfs(root.right, target, path);
        path.remove(path.size() - 1);
    }

    private int getNum(List<Integer> path, int target) {
        System.out.println(path);
        int res = 0;
        int n = path.size();
        for (int i = 0;i < n;i++) {
            int sum = 0;
            for (int j = i;j < n;j++) {
                sum += path.get(j);
                if (sum == target) {
                    res++;
                }
            }
        }
        return res;
    }


    private void swap(int []nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        int []nums = {2,2,3,5};
        Solution s = new Solution();
        System.out.println(s.canPartition(nums));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Trie {

    class Node {
        private Node[] sons;
        private boolean isEnd;
        private final int R = 26;

        public Node() {
            sons = new Node[R];
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean getEnd() {
            return isEnd;
        }

        public boolean containKey(char v) {
            return sons[v - 'a'] != null;
        }

        public Node get(char v) {
            return sons[v - 'a'];
        }

        public void put(char v, Node node) {
            sons[v - 'a'] = node;
        }
    }

    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node head = root;
        for (int i = 0;i < word.length();i++) {
            if (head.containKey(word.charAt(i))) {
                head = head.get(word.charAt(i));
            }
            else {
                Node node = new Node();
                head.put(word.charAt(i), node);
                head = node;
            }
        }
        head.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node head = root;
        for (int i = 0;i < word.length();i++) {
            if (head.containKey(word.charAt(i))) {
                head = head.get(word.charAt(i));
            }
            else {
                return false;
            }
        }
        return head.getEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node head = root;
        for (int i = 0;i < prefix.length();i++) {
            if (head.containKey(prefix.charAt(i))) {
                head = head.get(prefix.charAt(i));
            }
            else {
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */