class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int totalLen = len1 + len2;
        if (totalLen % 2 == 1) {
            int midIndex = totalLen / 2 + 1;
            return getMedium(nums1, nums2, midIndex);
        }
        else {
            int midIndex1 = totalLen / 2 , midIndex2 = totalLen / 2 + 1;
            return (getMedium(nums1, nums2, midIndex1) + getMedium(nums1, nums2, midIndex2)) / 2.0;
        }
    }

    private double getMedium(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int idx1 = 0, idx2 = 0;
        while (true) {
            if (idx1 == len1) {
                return nums2[idx2 + k - 1];
            }
            if (idx2 == len2) {
                return nums1[idx1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[idx1], nums2[idx2]);
            }
            int newIdx1 = Math.min(idx1 + k / 2, len1) - 1;
            int newIdx2 = Math.min(idx2 + k / 2, len2) - 1;
            int pivot1 = nums1[newIdx1];
            int pivot2 = nums2[newIdx2];
            if (pivot1 <= pivot2) {
                k -= (newIdx1 - idx1 + 1);
                idx1 = newIdx1 + 1;
            }
            else {
                k -= (newIdx2 - idx2 + 1);
                idx2 = newIdx2 + 1;
            }
        }
    }
}