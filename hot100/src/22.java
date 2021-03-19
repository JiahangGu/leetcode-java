class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, 0, 0, new StringBuffer(), ans);
        return ans;
    }

    static void dfs(int n, int left, int right, StringBuffer s, List<String> ans) {
        if (left + right == 2 * n) {
            if (s.length() > 0) {
                ans.add(s.toString());
            }
            return;
        }
        if (left < n) {
            s.append("(");
            dfs(n, left + 1, right, s, ans);
            s.deleteCharAt(s.length() - 1);
        }
        if (left > right) {
            s.append(")");
            dfs(n, left, right + 1, s, ans);
            s.deleteCharAt(s.length() - 1);
        }
    }
}