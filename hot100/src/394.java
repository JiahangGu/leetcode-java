class Solution {
    int idx;
    public String decodeString(String s) {
        idx = 0;
        return dfs(s);
    }

    private String dfs(String s) {
        if (idx >= s.length()) {
            return "";
        }
        int num = 0;
        StringBuffer sb = new StringBuffer();
        while (idx < s.length()) {
            char c = s.charAt(idx);
            idx++;
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
            else if (c == '[') {
                String res = dfs(s);
                for (int i = 0;i < num;i++) {
                    sb.append(res);
                }
                num = 0;
            }
            else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb.append(c);
            }
            else {
                return sb.toString();
            }
        }
        return sb.toString();
    }
}