/*
 做这道题时有想到记录左侧连续1的个数作为当前宽度，但是看到提示的动态规划就卡在了状态转移阶段（什么破提示）
 而题解在求得matrix[i][j]位置处左侧连续1的个数作为宽度之后，对于每一个位置，向上遍历找最小宽度并同时得到高度，求得最大面积。
 在遍历的时候发现，如果已有记录每一点的向上及向下的最小宽度的位置就可以节省O(m)的时间，而这个方法类似于题目84固定高度向左右
 遍历找更小的柱子的场景，所以可以使用单调栈来解决。使用单调栈记录每一个点向上直到小于该宽度的点和向下小于该宽度的点，在遍历时
 可以O(1)地得到最大高度求得面积。
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int [][]left = new int[m][n];
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = j == 0 ? 1 : left[i][j - 1] + 1;
                }
            }
        }
        int ans = 0;
        for (int j = 0;j < n;j++) {
            int []up = new int[m];
            int []down = new int[m];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0;i < m;i++) {
                while (!stack.empty() && left[i][j] <= left[stack.peek()][j]) {
                    stack.pop();
                }
                up[i] = stack.empty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1;i >= 0;i--) {
                while (!stack.empty() && left[i][j] <= left[stack.peek()][j]) {
                    stack.pop();
                }
                down[i] = stack.empty() ? m : stack.peek();
                stack.push(i);
            }
            for (int i = 0;i < m;i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }
}