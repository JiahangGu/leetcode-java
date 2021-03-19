class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int []par_freq = new int[26];
        int n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (n > s.length()) return ans;
        for (int i = 0;i < n;i++) {
            par_freq[p.charAt(i) - 'a']++;
        }
        int []cur_freq = new int[26];
        for (int i = 0;i < n - 1;i++) {
            cur_freq[s.charAt(i) - 'a']++;
        }
        for (int i = n - 1;i < s.length();i++) {
            cur_freq[s.charAt(i) - 'a']++;
            if (isSame(par_freq, cur_freq)) {
                ans.add(i - n + 1);
            }
            cur_freq[s.charAt(i - n + 1) - 'a']--;
        }
        return ans;
    }

    private boolean isSame(int []par, int []cur) {
        for (int i = 0;i < 26;i++) {
            if (par[i] != cur[i]) {
                return false;
            }
        }
        return true;
    }
}