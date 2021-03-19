class Solution {
    public int countSubstrings(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0;i < 2 * n;i++) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                ans++;
                l--;
                r++;
            }
        }
        return ans;
    }
}