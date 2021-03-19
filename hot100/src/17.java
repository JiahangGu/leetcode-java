class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        StringBuffer path = new StringBuffer();
        String []dict = new String[8];
        dict[0] = "abc";
        dict[1] = "def";
        dict[2] = "ghi";
        dict[3] = "jkl";
        dict[4] = "mno";
        dict[5] = "pqrs";
        dict[6] = "tuv";
        dict[7] = "wxyz";
        dfs(digits, ans, dict, path);
        return ans;
    }

    static void dfs(String digits, List<String> ans, String []dict, StringBuffer path) {
        if (digits.length() == 0) {
            ans.add(path.toString());
            return;
        }
        int idx = digits.charAt(0) - '0' - 2;
        for (int i = 0;i < dict[idx].length();i++) {
            path.append(dict[idx].charAt(i));
            dfs(digits.substring(1), ans, dict, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}