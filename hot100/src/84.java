/**
 * 本题要求最大的连续矩形面积，一个很自然的想法是遍历宽度，在不同宽度内找到最小的高度计算矩形面积。这个需要通过两层循环实现。
 * 另一个想法是固定高度，即以height[i]为矩形的高度，向左右遍历寻找到第一个小于h[i]的位置，中间就是最大的连续矩形，实现方式是
 * 一层循环体内使用最近搜索（只要找到最近的一个即可），并且是最近的一个小于h[i]的，相比两层循环来说很明显具有更好的优化空间，
 * 只要提前记录距离最近的最小高度索引。（卡住我的地方就是思维陷在了对于当前高度，找出前面小于他的高度并求面积，但无法解决最小高度
 * 之前的矩形面积没有统计的问题。但如果固定当前高度来寻找宽度的话问题就迎刃而解）
 * 考虑到上述思路，对于每一个i首先应该知道最左侧中最近的一个小于h[i]的索引，使用场景和单调栈的作用一致，栈中元素始终保持单调，
 * 在本题是单调递增，则对于当前i，栈不断弹出直到栈顶元素小于h[i]，此时的栈顶元素即为左侧最近的位置，并且弹出的元素不影响后续
 * 结果，因为后续在寻找时遇到的h[i]会挡住所有前面高度大于h[i]的柱子，只要小于h[i]也必定小于前面弹出的元素。
 * 所以找左侧最近的小于h[i]的操作可以通过预处理变为O(1)，同理右侧也一样。时间复杂度优化为O(n)。
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int []left = new int[n];
        int []right = new int[n];
        for (int i = 0;i < n;i++) {
            while (!stack.empty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.empty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1;i >= 0;i--) {
            while (!stack.empty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.empty() ? n : stack.peek();
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0;i < n;i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}

/**
 * 基于上述思路，还可以对常熟进行优化，使用一次遍历确定左侧边界的同时也确定右侧边界。考虑到对于h[i]是寻找左侧最近的小于h[i]的
 * 位置，在单调栈弹出的时候，说明h[stack.peek()]>=h[i]，也就是说，如果要寻找stack.peek()这个位置对应的右侧最近的小于该
 * 高度的位置应该是i，因为在stack.peek()之前弹出的所有元素都是大于他的。但存在一个问题是可能存在等于的情况，假设一段连续的高度
 * 相等的子数组，在求面积时可能左侧无法求得正确面积，但时最右侧的位置一定可以得到最大面积，此时前面的面积对答案没有影响，仍可得到
 * 正确的解。
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int []left = new int[n];
        int []right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0;i < n;i++) {
            while (!stack.empty() && heights[i] <= heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.empty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1;i >= 0;i--) {
            while (!stack.empty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.empty() ? n : stack.peek();
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0;i < n;i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}

//一点感触，在思考的时候可能会陷入一种方式中去，如果有两种方式可以想到的话，想一下两种实现方式，如果存在一种看起来更好优化，那么
//通常时优化的方向。写下来可能更利于确定优化的方向。