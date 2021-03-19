//对暴力解法的优化，一方面是用集合记录出现的前缀和pre的次数，这样pre-k代表当前数字前有多少个连续子数组和为k，
//另一方面是一边更新一边记录，可以保证pre-k是统计当前数字之前的子数组，避免了重复计数的问题，这里也是一开始没想到的地方
class Solution {
    public int subarraySum(int[] nums, int k) {
        int pre = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                ans += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return ans;
    }
}