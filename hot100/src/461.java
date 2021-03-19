class Solution {
    public int hammingDistance(int x, int y) {
        int ans = 0;
        int res = x ^ y;
        while (res > 0) {
            res &= (res - 1);
            ans++;
        }
        return ans;
    }
}