/**
 * 由于要计算连续递增的序列长度，假设序列为x,...x+y，那么其中任意一项x+i会包含x+i到x+y的长度信息，在求x+i之前的数字开始的序列
 * 长度时，可以遍历到x+i时直接获得结果，从而确保数组中的数字都只访问一次，符合O(n)的复杂度要求
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            map.put(num, 1);
        }
        for (int num : nums) {
            if (map.get(num) != 1) {
                continue;
            }
            int idx = num + 1;
            int len = 1;
            while (map.containsKey(idx) && map.get(idx) == 1) {
                idx++;
                len++;
            }
            if (map.containsKey(idx)) {
                len += map.get(idx);
            }
            ans = Math.max(ans, len);
            int tmp = num;
            while (tmp < idx) {
                map.put(tmp++, len--);
            }
        }
        return ans;
    }
}

/**
 * 对于每一个连续的上升子串，唯一存在着一个起始元素，遍历数组元素num，如果num-1存在于数组中，则放弃查找交给最小的元素，如果不存在
 * 表示num是最小的元素，依次递增求最长递增序列的长度。
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int tmp = num + 1;
                while (set.contains(tmp)) {
                    tmp++;
                }
                ans = Math.max(ans, tmp - num);
            }
        }
        return ans;
    }
}

/*
还有并查集的做法，对于数组中出现的所有数字num,构造num和num+1属于同一集合，并且集合代表设为更大的一方，然后遍历数组，对于每一元素
num求出所在集合的代表，与num的差即为num为起始元素对应的递增序列长度。
 */