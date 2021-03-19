//O(nlgn)解法，对数组进行排序，从左右开始分别比较，第一个不一样的数字即为边界位置

//O(n)解法，使用栈找边界，自己实现的解法。第一个栈保存递增的序列，如果出现更小的不断弹出直到栈顶更小，最后栈顶即为左边界，右边界同理。
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int idx = 0, l = -1, r = -1;
        boolean flag = true;
        while (idx < n) {
            if (flag && (stack.empty() || nums[idx] >= nums[stack.peek()])) {
                stack.push(idx);
            }
            while (!stack.empty() && nums[idx] < nums[stack.peek()]){
                stack.pop();
                flag = false;
            }
            if (stack.empty()) {
                break;
            }
            idx++;
        }
        if (stack.size() == n) {
            return 0;
        }
        l = stack.empty() ? 0 : stack.peek() + 1;
        stack.clear();
        idx = n - 1;
        flag = true;
        while (idx >= 0) {
            if (flag && (stack.empty() || nums[idx] <= nums[stack.peek()])) {
                stack.push(idx);
            }
            while (!stack.empty() && nums[idx] > nums[stack.peek()]){
                stack.pop();
                flag = false;
            }
            if (stack.empty()) {
                break;
            }
            idx--;
        }
        r = stack.empty() ? n - 1 : stack.peek() - 1;
        return r - l + 1;
    }
}

//上述解法逻辑有些乱，在这里参考题解进行整理。但是运行时间更长，应该是因为没有提前结束寻找边界的循环
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int l = n - 1, r = 0;
        for (int i = 0;i < n;i++) {
            while (!stack.empty() && nums[stack.peek()] > nums[i]) {
                l = Math.min(l, stack.pop());
            }
            stack.push(i);
        }
        for (int i = n - 1;i >= 0;i--) {
            while (!stack.empty() && nums[stack.peek()] < nums[i]) {
                r = Math.max(r, stack.pop());
            }
            stack.push(i);
        }
        return r > l ? r - l + 1 : 0;
    }
}

//空间复杂度O(1)解法，同理，也寻找左右边界，但不使用栈，而是通过标记当前是否出现过不满足递增要求来寻找左边界，如果出现过不满足的情况
//记录数组中的最小值，并通过第二次遍历找到第一个大于该数字的索引为左边界。
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        int n = nums.length;;
        for (int i = 1;i < n;i++) {
            //出现了不满足递增的情况，开始统计
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }
        flag = false;
        for (int i = n - 2;i >= 0;i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }
        int l, r;
        for (l = 0;l < n;l++) {
            if (nums[l] > min) {
                break;
            }
        }
        for (r = n - 1;r >= 0;r--) {
            if (nums[r] < max) {
                break;
            }
        }
        return r > l ? r - l + 1 : 0;
    }
}