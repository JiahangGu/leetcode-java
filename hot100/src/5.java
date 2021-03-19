class Solution {
    public String longestPalindrome(String s) {
        int ans = 0;
        int n = s.length();
        String res = "";
        for (int i = 0;i < 2 * n;i++) {
            if ((i & 1) == 0) {
                int l = i / 2, r = i / 2 + 1;
                while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                    l--;
                    r++;
                }
                if (r - l - 1 > ans) {
                    ans = r - l - 1;

                    res = s.substring(l + 1, r);
                }
            }
            else {
                int l = i / 2, r = i / 2;
                while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                    l--;
                    r++;
                }
                if (r - l - 1 > ans) {
                    ans = r - l - 1;
                    res = s.substring(l + 1, r);
                }
            }
        }
        return res;
    }

}