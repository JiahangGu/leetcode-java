class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set x = new HashSet();
        int j = 0, ans = 0;
        for (int i = -1; i < s.length(); i++) {
            if (i > -1) {
                x.remove(s.charAt(i));
            }
            while (j < s.length() && !x.contains(s.charAt(j))) {
                x.add(s.charAt(j));
                j++;
            }
            ans = Math.max(ans, j - i - 1);
        }
        return ans;
    }
}