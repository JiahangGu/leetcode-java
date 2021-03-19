/**
 * 使用双端队列存储当前窗口的最大值，对于索引i来说，如果i之后的数字大于nums[i]，则在后续滑动窗口时nums[i]也不可能是最大值，所以
 * 弹出并更新为新的最大值，而如果小于nums[i]，则在nums[i]滑动到窗口左侧后可能成为新的最大值，所以放入队列尾部。在滑动时，如果队列
 * 的队首索引是当前索引值，则说明该最大值即将离开窗口，从队首移除。
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int []ans = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0;i < k - 1;i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int idx = 0;
        for (int i = k - 1;i < n;i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            ans[idx] = nums[deque.peekFirst()];
            if (idx == deque.peekFirst()) {
                deque.pollFirst();
            }
            idx++;
        }
        return ans;
    }
}