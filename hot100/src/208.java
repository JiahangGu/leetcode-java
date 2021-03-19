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