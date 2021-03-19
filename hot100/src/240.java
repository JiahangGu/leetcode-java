class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, right = n - 1;
        while (low < m && right >= 0) {
            if (matrix[low][right] == target) {
                return true;
            }
            else if (matrix[low][right] < target) {
                low++;
            }
            else {
                right--;
            }
        }
        return false;
    }
}