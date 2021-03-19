//O(n)做法
class Solution {
    public int maxSubArray(int[] nums) {
        long ans = -2147483648;
        int sum = 0;
        for (int i = 0;i < nums.length;i++) {
            sum += nums[i];
            ans = Math.max(ans, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return (int)ans;
    }
}

//分治法
class Solution {
    class Status{
        int lSum, rSum, iSum, mSum;

        Status(int lSum, int rSum, int iSum, int mSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.iSum = iSum;
            this.mSum = mSum;
        }
    }

    public int maxSubArray(int[] nums) {
        return merge(nums, 0, nums.length - 1).mSum;
    }

    private Status merge(int[] nums, int l, int r) {
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int mid = l + (r - l) / 2;
        Status left = merge(nums, l, mid);
        Status right = merge(nums, mid + 1, r);
        return pushUp(left, right);
    }

    private Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.iSum + r.lSum, l.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(l.mSum, Math.max(r.mSum, l.rSum + r.lSum));
        return new Status(lSum, rSum, iSum, mSum);
    }
}