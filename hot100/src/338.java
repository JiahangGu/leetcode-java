class Solution {
    public int[] countBits(int num) {
        int []ans = new int[num + 1];
        ans[0] = 0;
        if (num == 0) {
            return ans;
        }
        ans[1] = 1;
        if (num == 1) {
            return ans;
        }
        int i = 2;
        while (i <= num) {
            int x = i;
            for (int j = 0;j < i && i + j <= num;j++) {
                ans[x++] = ans[j] + 1;
            }
            i <<= 1;
        }
        return ans;
    }
}