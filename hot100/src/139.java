class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int n = s.length();
        boolean []dp = new boolean[n];
        for (String word : wordDict) {
            set.add(word);
        }
        for (int i = 0;i < n;i++) {
            for (int j = 0;j <= i;j++) {
//                System.out.println(s.substring(j, i + 1));
                if (set.contains(s.substring(j, i + 1)) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}