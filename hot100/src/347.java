class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int []> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int key = entry.getKey(), val = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < val) {
                    queue.poll();
                    queue.offer(new int[]{key, val});
                }
            }
            else {
                queue.offer(new int[]{key, val});
            }
        }
        int []ans = new int[k];
        for (int i = 0;i < k;i++) {
            ans[i] = queue.poll()[0];
        }
        return ans;
    }
}