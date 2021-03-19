//解法1，二分查找，统计左侧小于该数字的个数，复杂度O(nlgn)，且空间复杂度为1，不修改数组
class Solution {
    public int findDuplicate(int[] nums) {
        int l = 1, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cnt = 0;
            for (int i = 0;i < nums.length;i++) {
                if (nums[i] <= mid) {
                    cnt += 1;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }
}

//解法2，二进制，对于每一位求是1的次数，如果nums统计得到的1的个数大于[1,n]的个数，则对应位为1
class Solution {
    public int findDuplicate(int[] nums) {
        int bit = 32;
        int ans = 0;
        for (int i = 0;i < bit;i++) {
            int x = 0, y = 0;
            for (int j = 0;j < nums.length;j++) {
                if ((nums[j] & (1 << i)) != 0) {
                    x++;
                }
                if (j >= 1 && (j & (1 << i)) != 0) {
                    y++;
                }
            }
            if (x > y) {
                ans = ans | (1 << i);
            }
        }
        return ans;
    }
}

//解法3，使用快慢指针找环，复杂度O(n)
class Solution {
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);
        fast = slow;
        slow = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}