//使用集合保存出现数字，空间复杂度O(n)，时间O(n)

//交换数字到正确的位置，例如1在第一个位置，遍历一次如果nums[i]!=i+1则i+1不存在，时间O(n)，空间O(1)
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int idx = 0;
        int n = nums.length;
        if (n == 0) return ans;
        while (idx < n) {
            while (nums[idx] != idx + 1 && nums[idx] != nums[nums[idx] - 1]) {
                swap(nums, idx, nums[idx] - 1);
            }
            idx++;
        }
        for (int i = 0;i < n;i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    private void swap(int []nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}

//还有一种标记做法，一次遍历标记所有出现的数字对应的下标保存的数字为负数，第二次遍历找出所有元素为整数的索引即为未出现的