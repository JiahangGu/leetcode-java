/**
 * 在整理题解答案之前，先附上自己的做法，使用双指针维护当前能存水的窗口，即从第一个能存水的柱子开始（主要是左边界为0不能存），向
 * 右遍历找到一个大于等于左侧主子的柱子，则两柱子之间可以存水由左右两侧柱子最小值减去中间的高度之和。然后更新左柱子为当前右柱子，右
 * 柱子继续向右遍历。从左到右完成后，会停在最后一个最高的柱子处，该柱子右侧没有统计，所以需要同样的处理方法从右侧开始遍历到该最高点
 * 柱子出计算存水量并累加。
 */
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int n = height.length;
        if (n == 0) return 0;
        int idx = 0;
        int tmp = 0;
        while (idx < n && height[idx] >= tmp) {
            tmp = height[idx];
            idx++;
        }
        int l = idx - 1, r = idx;
        while (r < n) {
            while (r < n && height[l] > height[r]) {
                r++;
            }
            if (r == n) break;
            int minHeight = Math.min(height[l], height[r]);
            for (int i = l + 1;i < r;i++) {
                ans += minHeight - height[i];
            }
            l = r;
            r++;
        }
        int last = l;
        tmp = 0;
        idx = n - 1;
        while (idx >= 0 && height[idx] >= tmp) {
            tmp = height[idx];
            idx--;
        }
        r = idx + 1;
        l = idx;
        while (l >= last) {
            while (l >= last && height[l] < height[r]) {
                l--;
            }
            int minHeight = Math.min(height[l], height[r]);
            for (int i = l + 1;i < r;i++) {
                ans += minHeight - height[i];
            }
            r = l;
            l--;
        }
        return ans;
    }
}

/**
 * 蓄水池的蓄水量由两边柱子高度决定，对于某一点的蓄水量，可以看作是他两边最高的柱子中最小的一个减去该柱子高度，因为无论怎么选择，
 * 该柱子两边最高的柱子形成的矩形始终是最大的，将水放在里面不会流出。所以问题转换成寻找两边最高柱子。O(n^2)的解法对每一个柱子求
 * 解，但其实可以通过两次遍历找出当前位置遇到过的最高柱子的高度，这样两边能得到的最高柱子也记录下来可以在计算时直接使用。是O(n)空间
 * 换取O(n)时间的做法。
 */
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        int []leftMax = new int[n];
        int []rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1;i < n;i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2;i >= 0;i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int ans = 0;
        for (int i = 1;i < n - 1;i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}

/**
 * 还可以使用栈维护此前的柱子高度信息，并且蓄水池只有遇到右侧柱子大于等于左侧时才可以蓄水，所以栈用来存放递减的柱子序列，如果遇到一个
 * 高度大于栈顶的柱子，则栈中所有小于该柱子高度的柱子均可以取出来形成一个蓄水池，水池的宽度为柱子的距离，高度为两个柱子较小值，计算完成
 * 后将该柱子继续放入栈，仍保持栈中元素递减。
 */
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int cur = 0;
        while (cur < height.length) {
            while (!stack.empty() && height[cur] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.empty()) {
                    break;
                }
                int dis = cur - stack.peek() - 1;
                int minHeight = Math.min(height[cur], height[stack.peek()]) - height[top];
                ans += minHeight * dis;
            }
            stack.push(cur++);
        }
        return ans;
    }
}

/**
 * 另一种双指针的做法代码更为简洁，因为蓄水池的高度是由左右柱子中较小的一个决定，例如从左侧开始记录已有的最大高度，那么在向右遍历
 * 的过程中，如果当前高度小于左侧已有最大高度，说明该部分可以蓄水，否则更新最大高度，但可能出现右侧柱子不如当前柱子高导致无法蓄水的
 * 情况，所以在遍历时要判断左右柱子哪一个更矮，更矮的柱子是决定蓄水池高度的关键，也决定了遍历的方向。
 */
class Solution {
    public int trap(int[] height) {
        int ans = 0, leftMax = 0, rightMax = 0, l = 0, r = height.length - 1;
        while (l < r) {
            if (height[l] < height[r]) {
                if (leftMax < height[l]) {
                    leftMax = height[l];
                }
                else {
                    ans += leftMax - height[l];
                }
                l++;
            }
            else {
                if (rightMax < height[r]) {
                    rightMax = height[r];
                }
                else {
                    ans += rightMax - height[r];
                }
                r--;
            }
        }
        return ans;
    }
}