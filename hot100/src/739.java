class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int []ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(n - 1);
        ans[n - 1] = 0;
        for (int i = n - 2;i >= 0;i--) {
            int idx = -1;
            while (!stack.empty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            if (stack.empty()) {
                ans[i] = 0;
            }
            else {
                idx = stack.peek();
                ans[i] = idx - i;
            }
            stack.push(i);
        }
        return ans;
    }
}